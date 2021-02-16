package api.hdjunction.domain;

import antlr.ANTLRParser;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Patient {
    @Id @GeneratedValue
    private Long id;
    // 환자명
    @Column(length=45)
    private String name;
    // 환자등록번호
    @Column(length=13, unique=true)
    private String seq;
    // 성별코드
    @Column(length=10)
    private String genderCode;
    // 생년월일
    @Column(length=10)
    private String birthday;
    // 휴대전화번호
    @Column(length=20)
    private String cellphone;

    @ManyToOne
    @JoinColumn(name="hospitalId")
    private Hospital hospital;

    public Patient() {
    }

    public Patient(Long id, String name, String seq, String genderCode, String birthday, String cellphone) {
        this.id = id;
        this.name = name;
        this.seq = seq;
        this.genderCode = genderCode;
        this.birthday = birthday;
        this.cellphone = cellphone;
    }

    public Patient(String name, String genderCode, String birthday, String cellphone) {
        this.name = name;
        this.genderCode = genderCode;
        this.birthday = birthday;
        this.cellphone = cellphone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
