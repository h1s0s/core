package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        //VIP면 1000원 할인
        if(member.getGrade() == Grade.VIP){//이넘타입은 ==
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
