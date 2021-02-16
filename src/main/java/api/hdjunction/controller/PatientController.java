package api.hdjunction.controller;

import api.hdjunction.domain.Hospital;
import api.hdjunction.domain.Patient;
import api.hdjunction.dto.PatientResponseDto;
import api.hdjunction.dto.PatientSaveDto;
import api.hdjunction.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Patient> getPatientList(
            @RequestParam(value="pageNo", required=false, defaultValue="1") int pageNo
            ,@RequestParam(value="pageSize", required=false, defaultValue="10") int pageSize
            ,@RequestParam(value="name", required=false) String name
            ,@RequestParam(value="seq", required=false) String seq
            ,@RequestParam(value="birthday", required=false) String birthday) {
        List<Patient> patientList = patientService.getPatientList(pageNo - 1, pageSize, name, seq, birthday);
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
    public ResponseEntity deletePatient(@PathVariable Long id) {
        Patient patient = patientService.deletePatient(id);

        PatientResponseDto response = new PatientResponseDto(patient);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
