package api.hdjunction.service;

import api.hdjunction.domain.Patient;
import api.hdjunction.persistence.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepo;

    public List<Patient> getPatientList() {
        return (List<Patient>) patientRepo.findAll();
    }

    public void insertPatient(Patient patient) {
        patientRepo.save(patient);
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
