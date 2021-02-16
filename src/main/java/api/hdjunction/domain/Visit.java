package api.hdjunction.domain;

import api.hdjunction.domain.Hospital;
import api.hdjunction.domain.Patient;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Visit {
    @Id @GeneratedValue
    private Long id;
    // 접수일시
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date reserveDate;
    // 방문상태코드
    @Column(length=10)
    private String statusCode;

    @ManyToOne
    @JoinColumn(name="hospitalId")
    private Hospital hospital;
    @ManyToOne
    @JoinColumn(name="patientId")
    private Patient patient;

    public Visit() {
    }

    public Visit(Date reserveDate, String statusCode, Hospital hospital, Patient patient) {
        this.reserveDate = reserveDate;
        this.statusCode = statusCode;
        this.hospital = hospital;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
