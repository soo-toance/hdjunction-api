package api.hdjunction.controller;

import api.hdjunction.domain.Hospital;
import api.hdjunction.domain.Patient;
import api.hdjunction.dto.PatientResponseDto;
import api.hdjunction.dto.PatientSaveDto;
import api.hdjunction.service.HospitalService;
import api.hdjunction.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<Patient> getPatientList() {
        List<Patient> patientList = patientService.getPatientList();
        return patientList;
    }

    // 환자 정보 추가
    @PostMapping("")
    public ResponseEntity insertPatient(@RequestBody @Valid PatientSaveDto dto) {
        Patient patient = patientService.insertPatient(dto.toEntity());
        PatientResponseDto response = new PatientResponseDto(patient);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
    public ResponseEntity updatePatient(@PathVariable Long id, @RequestBody @Valid PatientSaveDto dto) {
        Patient patient = dto.toEntity();
        patient.setId(id);

        patientService.updatePatient(patient);

        PatientResponseDto response = new PatientResponseDto(patient);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
