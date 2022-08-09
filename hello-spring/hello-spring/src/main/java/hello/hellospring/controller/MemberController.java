package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    // 컨트롤러마다 서비스를 생성해서 사용할 필요가 없음 -> 하나만 생성해서 공유하여 사용
//    private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    // 생성자에 @Autowired 어노테이션이 선언되어있으면 필요한 객체를 스프링 컨테이너(been 등록되어있는)에서 찾아서 연결 시켜준다. -> DI
    // 생성자는 자동으로 처음 실행된다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


}
