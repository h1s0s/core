package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //AppConfig 는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성
    //생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입함

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
        //memberServiceImpl은 필드에서 new를 하지 않고, 생성자를 통해서 할당이 됨
        //-> 생성자 주입
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
