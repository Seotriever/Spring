package hello.hello_spring.controller;
import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller            //Component 방식이지만, Controller는 어쩔 수 없다.
public class MemberController {

    //    private final MemberService memberService = new MemberService();            // controller 사용하는방법
    //    @Autowired private MemberService memberService;   // 필드주입 권장하지 않음 변경힘듬

    //    private MemberService memberService;
    //@Autowired              // setter injection 방식 public 이어야함 (예상치못하게 변경가능성)
    //    public void setMemberService(MemberService memberService) {
    //        this.memberService = memberService;
    //}
    private final MemberService memberService;

    @Autowired      //config 가 @Configuration 등록이 되어 있으면 비활성화가 되어있어도 @Autowired 동작x
    public MemberController(MemberService memberService) {      // 왜 불꺼짐?
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String List(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
