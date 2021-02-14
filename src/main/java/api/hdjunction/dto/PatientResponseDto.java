package api.hdjunction.dto;

import api.hdjunction.domain.Patient;

public class PatientResponseDto {
    private Long id;

    public PatientResponseDto() {
    }

    public PatientResponseDto(Patient patient) {
        this.id = patient.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
