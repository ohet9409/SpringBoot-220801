package hello.hellospring.domain;

import javax.persistence.*;

// @Entity: jpa가 관리하는 객체라고 선언
@Entity
public class Member {

//  @Id: PK 맵핑 / @GeneratedValue: ID가 자동으로 생성되도록 설정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//  테이블의 name 컬럼 맵핑 설정
    @Column(name = "name")
    private String name;

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
}
