package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 테스트는 한글로 바꾸어도 된다.
// 빌드될때 테스트코드는 포함되지 않는다.
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

//  컨테이너로 부터 주입받는다.
//  테스트를 진행할 때는 편하게 @Autowired 사용하면 된다. -> 필드 주입 방법(DI)
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
//  @Commit: 실제로 데이터가 저장되도록 선언
//    @Commit
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring1");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // member2를 join 할 때 IllegalStateException이 발생하것을 예상하는 코드 
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        assertThrows(NullPointerException.class,() -> memberService.join(member2));

       /* try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존해하는 회원입니다.12333");
        }*/
        // then
    }

}