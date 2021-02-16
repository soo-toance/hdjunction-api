package api.hdjunction.domain;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Hospital {
    @Id @GeneratedValue
    private Long id;
    // 병원명
    @Column(length=45)
    private String name;
    // 요양기관번호
    @Column(length=20)
    private String tel;
    // 병원장명
    @Column(length=10)
    private String directorName;

    public Hospital() {
    }

    public Hospital(String name, String tel, String directorName) {
        this.name = name;
        this.tel = tel;
        this.directorName = directorName;
    }

    public Hospital(Long id, String name, String tel, String directorName) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.directorName = directorName;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
}
