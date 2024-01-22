package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberSerivce;

    
    @Autowired // 생성자
    public MemberController(MemberService memberService) {
        this.memberSerivce = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "/members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();

        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberSerivce.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberSerivce.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
