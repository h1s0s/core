package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    //psvm치고 엔터
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();//memberServiceImpl을 만들어 줌
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findeMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findeMember.getName());
    }
}
