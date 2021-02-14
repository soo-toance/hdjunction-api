package api.hdjunction.service;

import api.hdjunction.domain.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getPatientList();

    Patient insertPatient(Patient patient);

    Patient getPatient(Long id);

    void updatePatient(Patient patient);

    Patient deletePatient(Long id);

}
