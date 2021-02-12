package api.hdjunction.controller;

import api.hdjunction.domain.Hospital;
import api.hdjunction.domain.Patient;
import api.hdjunction.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    // TODO : Hospital 연동
    @ModelAttribute("hospital")
    public Hospital setHospital() {
        return new Hospital((long)1, "name", "tel", "directorName");
    }

    // 환자 목록 조회
    @GetMapping("")
    public List<Patient> getPatientList(@ModelAttribute("hospital") Hospital hospital) {
        if (hospital.getId() == null) {
            return null;
        }

        List<Patient> patientList = patientService.getPatientList();
        return patientList;
    }

    // 환자 정보 추가
    @PostMapping("")
    public Patient insertPatient(@ModelAttribute("hospital") Hospital hospital, @RequestBody Map<String, String> body) {
        if (hospital.getId() == null) {
            return null;
        }

        Patient patient = new Patient(body.get("name"), body.get("seq"), body.get("genderCode"), body.get("birthday"), body.get("cellphone"), hospital);
        patientService.insertPatient(patient);

        return patient;
    }

    // 환자 정보 조회
    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Long id) {
        if (id == null) {
            return null;
        }

        Patient patient = patientService.getPatient(id);
        return patient;
    }

    // 환자 정보 수정
    @PutMapping("/{id}")
    public Patient updatePatient(@ModelAttribute("hospital") Hospital hospital, @PathVariable Long id, @RequestBody Map<String, String> body) {
        if (hospital.getId() == null) {
            return null;
        }

        if (patientService.getPatient(id) == null) {
            return null;
        }

        Patient patient = new Patient(body.get("name"), body.get("seq"), body.get("genderCode"), body.get("birthday"), body.get("cellphone"), hospital);
        patient.setId(id);

        patientService.updatePatient(patient);
        return patient;
    }

    // 환자 정보 삭제
    @DeleteMapping("/{id}")
    public Patient deletePatient(@ModelAttribute("hospital") Hospital hospital, @PathVariable Long id) {
        if (hospital.getId() == null) {
            return null;
        }

        return patientService.deletePatient(id);
    }
}
