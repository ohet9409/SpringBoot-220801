package hello.hellospring.service;

import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

// 자바코드로 직접 스프링 빈 등록하는 방법
// 스프링 실행할때 자동 실행
@Configuration
public class SpringConfig {

//    JDBC 사용을 위해 DataSource을 주입 받는다.
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }

//  JPA사용을 위해 EntityManager을 주입 받는다.
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    //  스프링 빈 등록
    @Bean
    public MemberService memberService() {
        // @Autowired와 같이 동작
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // 구현체를 리턴해줌
//        return new MemoryMemberRepository();
//      JDBC 연결로 변경경
//       return new JdbcMemberRepository(dataSource);
//      JDBCTemplate 연결로 변경
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
