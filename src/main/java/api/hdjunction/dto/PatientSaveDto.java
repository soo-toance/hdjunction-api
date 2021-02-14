package api.hdjunction.dto;

import api.hdjunction.domain.Hospital;
import api.hdjunction.domain.Patient;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class PatientSaveDto {
    @NotBlank(message = "환자명은 필수값 입니다.")
    private String name;
    @NotBlank(message = "생년월일은 필수값 입니다.")
    private String birthday;
    @NotBlank(message = "성별코드는 필수값 입니다.")
    private String genderCode;
    @NotBlank(message = "휴대전화번호는 필수값 입니다.")
    private String cellphone;
    @NotNull(message = "병원 ID는 필수값 입니다.")
    private Long hospitalId;

    public PatientSaveDto() {
    }

    public Patient toEntity() {
        Patient patient = new Patient();
        Hospital hospital = new Hospital();

        patient.setName(this.getName());
        patient.setGenderCode(this.getGenderCode());
        patient.setBirthday(this.getBirthday());
        patient.setCellphone(this.getCellphone());

        hospital.setId(this.getHospitalId());
        patient.setHospital(hospital);

        return patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }
}
