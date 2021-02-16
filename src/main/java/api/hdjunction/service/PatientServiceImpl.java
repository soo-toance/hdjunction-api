package api.hdjunction.service;

import api.hdjunction.domain.Patient;
import api.hdjunction.domain.Seqno;
import api.hdjunction.persistence.PatientRepository;
import api.hdjunction.persistence.SeqnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private SeqnoRepository seqnoRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Patient> getPatientList(int pageNo, int pageSize, String name, String seq, String birthday) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Patient> criteriaQuery = criteriaBuilder.createQuery(Patient.class);

        Root<Patient> root = criteriaQuery.from(Patient.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (name != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }

        if (seq != null) {
            predicates.add(criteriaBuilder.like(root.get("seq"), "%" + seq + "%"));
        }

        if (birthday != null) {
            predicates.add(criteriaBuilder.like(root.get("birthday"), "%" + birthday + "%"));
        }

        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Patient> typedQuery = entityManager.createQuery(criteriaQuery).setFirstResult(pageNo * pageSize).setMaxResults(pageSize);
        List<Patient> patientList = typedQuery.getResultList();

        return patientList;
    }

    @Transactional
    public Patient insertPatient(Patient patient) {
        // 환자 번호 부여
        String prefix = "M" + patient.getHospital().getId();
        Seqno curSeqno = seqnoRepo.findByPrefix(prefix);
        Long curValue = (curSeqno == null ? 0 : curSeqno.getValue());
        patient.setSeq(prefix + String.format("%05d", curValue));

        // 환자 번호 신규 규칙 적용
        Seqno seqno = new Seqno();
        seqno.setPrefix(prefix);
        seqno.setValue(curValue + 1);
        seqnoRepo.save(seqno);

        return patientRepo.save(patient);
    }

    public Patient getPatient(Long id) {
        return patientRepo.findById(id).orElse(null);
    }

    @Transactional
    public void updatePatient(Patient patient) {
        if (getPatient(patient.getId()) == null) {
            throw new NullPointerException("환자 정보가 존재하지 않습니다.");
        }

        patientRepo.save(patient);
    }

    @Transactional
    public Patient deletePatient(Long id) {
        Patient exist = getPatient(id);
        if (exist == null) {
            throw new NullPointerException("환자 정보가 존재하지 않습니다.");
        }

        patientRepo.deleteById(id);
        return exist;
    }
}
