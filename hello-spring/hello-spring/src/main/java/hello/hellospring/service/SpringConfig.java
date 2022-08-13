package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바코드로 직접 스프링 빈 등록하는 방법
// 스프링 실행할때 자동 실행
@Configuration
public class SpringConfig {

//  스프링 빈 등록
    @Bean
    public MemberService memberService() {
        // @Autowired와 같이 동작
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // 구현체를 리턴해줌
        return new MemoryMemberRepository();
    }
}
