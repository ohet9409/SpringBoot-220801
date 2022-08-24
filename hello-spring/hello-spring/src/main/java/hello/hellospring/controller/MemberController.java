package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // 컨트롤러마다 서비스를 생성해서 사용할 필요가 없음 -> 하나만 생성해서 공유하여 사용
//    private final MemberService memberService = new MemberService();

    private  MemberService memberService;
//  DI의 필드 주입방법 별로 좋지 않음: 스프링 뜰때만 실행되고 중간에 수정하기 어렵다.
//    @Autowired private  MemberService memberService;

    // 생성자에 @Autowired 어노테이션이 선언되어있으면 필요한 객체를 스프링 컨테이너(been 등록되어있는)에서 찾아서 연결 시켜준다. -> DI
    // 생성자는 자동으로 처음 실행된다.
    //  DI의 생성자 주입방법
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
//      AOP 프록시 클래스 생성 확인
//      memberService: class hello.hellospring.service.MemberService$$EnhancerBySpringCGLIB$$c70ac3a1
        System.out.println("memberService: " + memberService.getClass());
    }

    //  DI의 setter 주입방법: Controller에 setter가 퍼블릭으로 공객되고 만약 잘못 수정된다면 문제가 발생될 수 있다.
//    @Autowired
//    public void setMemberService(MemberService memberService){
//        this.memberService = memberService;
//    }

//  입력 화면 호출
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

//  입력 화면 데이터 전달 받은 후 처음 화면 호줄
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    //  조회 화면
    @GetMapping("/members")
    public String List(Model model) {
        List<Member> members = memberService.findMembers();
        // 화면으로 전달될 데이터 key, value
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
