package api.hdjunction.controller;

import api.hdjunction.domain.Hospital;
import api.hdjunction.domain.Patient;
import api.hdjunction.domain.Visit;
import api.hdjunction.service.PatientService;
import api.hdjunction.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/visit")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @Autowired
    private PatientService patientService;

    // TODO : Hospital 연동
    @ModelAttribute("hospital")
    public Hospital setHospital() {
        return new Hospital((long)1, "name", "tel", "directorName");
    }

    // 환자 방문 목록 조회
    @GetMapping("")
    public List<Visit> getVisitList() {
        List<Visit> visitList = visitService.getVisitList();
        return visitList;
    }

    // 환자 방문 정보 추가
    @PostMapping("")
    public Visit insertVisit(@ModelAttribute("hospital") Hospital hospital, @RequestBody Map<String, String> body) {
        if (body.get("patientId") == null) {
            return null;
        }

        Visit visit = null;

        try {
            Patient patient = patientService.getPatient(Long.valueOf(body.get("patientId")));
            visit = new Visit(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(body.get("reserveDate")), body.get("statusCode"), hospital, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        visitService.insertVisit(visit);

        return visit;
    }

    // 환자 방문 정보 조회
    @GetMapping("/{id}")
    public Visit getVisit(@PathVariable Long id) {
        if (id == null) {
            return null;
        }

        Visit visit = visitService.getVisit(id);
        return visit;
    }

    // 환자 방문 정보 수정
    @PutMapping("/{id}")
    public Visit updateVisit(@ModelAttribute("hospital") Hospital hospital, @PathVariable Long id, @RequestBody Map<String, String> body) {
        if (hospital.getId() == null) {
            return null;
        }

        if (visitService.getVisit(id) == null) {
            return null;
        }

        Visit visit = null;

        try {
            Patient patient = patientService.getPatient(Long.valueOf(body.get("patientId")));
            visit = new Visit(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(body.get("reserveDate")), body.get("statusCode"), hospital, patient);
            visit.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        visitService.updateVisit(visit);
        return visit;
    }

    // 환자 방문 정보 삭제
    @DeleteMapping("/{id}")
    public Visit deleteVisit(@ModelAttribute("hospital") Hospital hospital, @PathVariable Long id) {
        if (hospital.getId() == null) {
            return null;
        }

        return visitService.deleteVisit(id);
    }
}
