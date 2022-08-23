package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 항상 모든 테스트 코드가 끝나고 아래 기능이 동작하도록 어노테이션 설정 = 콜백 메서드
    @AfterEach
    public void afterEach() {
        // 데이터 클리어
        repository.clearStore();
    }
    
    @Test
    @Commit
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        System.out.println(result);
        // member가 result 데이터와 일치 하는지 확인
        assertThat(result).isEqualTo(member);
    }
    
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        
        Member result = repository.findByName("spring1").get();

        // result가 member1이 맞는지 확인
        Assertions.assertThat(result).isEqualTo(member1);

    }
    
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        // 총 데이터가 2개 나오는지 확인
        assertThat(result.size()).isEqualTo(2);
        
    }

    
}
