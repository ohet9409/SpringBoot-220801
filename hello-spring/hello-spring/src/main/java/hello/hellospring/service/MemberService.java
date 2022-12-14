package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 같은 repository로 진행하기 위한 설정
    // 외부에서 넣어주도록 설정을 변경
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

    }

    /*
    * 회원 가입
    * */
    public Long join(Member member) {
//      메소드 수행시간을 구한다는 과정에서의 코드
        long start = System.currentTimeMillis();

        try {
            // 중복회원 검증
            validateDuplicateMember(member);

            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long times = finish - start;

            System.out.println("join = " + times + "ms");
        }

    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // result가 만약 값이 있을경우(ifPresent) -> optional로 반환되기 때문에 가능
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // 위에 코드를 권장되는 코드로 바꾼 결과
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /*
     전체 회원 조회
    */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

//  회원 ID 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
