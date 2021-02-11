package api.hdjunction.domain;

import javax.persistence.*;

@Entity
public class Patient {
    @Id @GeneratedValue
    private Long id;
    // 환자명
    @Column(length=45)
    private String name;
    // 환자등록번호
    @Column(length=13)
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

    public Patient(String name, String seq, String genderCode, String birthday, String cellphone, Hospital hospital) {
        this.name = name;
        this.seq = seq;
        this.genderCode = genderCode;
        this.birthday = birthday;
        this.cellphone = cellphone;
        this.hospital = hospital;
    }
}
