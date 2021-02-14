package api.hdjunction.service;

import api.hdjunction.domain.Patient;
import api.hdjunction.domain.Seqno;
import api.hdjunction.persistence.HospitalRepository;
import api.hdjunction.persistence.PatientRepository;
import api.hdjunction.persistence.SeqnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private SeqnoRepository seqnoRepo;

    public List<Patient> getPatientList() {
        return (List<Patient>) patientRepo.findAll();
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

    public void updatePatient(Patient patient) {
        if (getPatient(patient.getId()) == null) {
            return;
        }

        patientRepo.save(patient);
    }

    public Patient deletePatient(Long id) {
        Patient exist = getPatient(id);
        if (exist == null) {
            return null;
        }

        patientRepo.deleteById(id);
        return exist;
    }
}
