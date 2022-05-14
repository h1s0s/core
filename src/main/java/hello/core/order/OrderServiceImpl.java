package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;

    //기존 코드1 : private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //기존 코드2 : private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //할인정책이 변경됨에 따라 FixDiscountPolicy에서 RateDiscountPolicy로 할인정책 클래스를 변경한다.
    //역할과 구현을 충실하게 분리했다 -> O
    //다형성도 활용하고, 인터페이스 구현 객체를 분리했다 -> O
    //OCP, DIP 같은 객체지향 설계 원칙을 충실히 준수했다 - X
    //DIP: 주문서비스 클라이언트는 추상 인터페이스(DiscountPolicy) 뿐만 아니라 구현 클래스(FixDiscountPolycy, RateDiscountPolicy)에도 의존하고 있다.
    //=> DIP(의존관계 역전:객체에 의존 하지말고 인터페이스에 의존해라) 위반,
    // 문제: 할인정책을 변경하는 순간, OrderServiceImpl의 소스도 함께 변경해야함. OCP 문제
    //이 문제를 해결하기 위해서는, 클라이언트 코드인 'OrderServiceImpl'은 'DiscountPlicy'의 인터페이스 뿐만 아니라 구체 클래스도 함께 의존하고 있다.
    //DIP를 위반하지 않도록 인터페이스에만 의존하도록 의존관계를 변경하면 된다
    //변경 코드
    private DiscountPolicy discountPolicy;
    //구현 클래스는 이제, 'memberRepository'라는 인터페이스를 의존한다. DIP 성립
    //'MemberServiceImpl'은 생성자를 통해 어떤 구현 객체가 들어올지 알 수 없다.
    //생성자를 통해 어떤 구현 객체를 주입할지는 오직 외부('AppConfig')에서 결정된다.
    //이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다.

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
