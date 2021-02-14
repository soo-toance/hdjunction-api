package api.hdjunction.persistence;


import api.hdjunction.domain.Hospital;
import api.hdjunction.domain.Patient;
import api.hdjunction.dto.PatientSaveDto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
