package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(
        excludeFilters =  @Filter(type = FilterType.ANNOTATION,
        classes = Configuration.class))
public class AutoAppConfig {
    //컴포넌트 스캔이라는 기능을 사용하면, 스프링 설정정보가 없어도 자동으로 스트링 빈을 등록함
    //컴포넌트스캔은 컴포넌트라는 어노테이션을 등록한 빈을 자동으로 찾아서 등록해줌
    //excludeFilters는 자동으로 등록되지 않게 빼주는 기능
    //보통 설정정보를 실무에서 제외하진 않음
    //또 의존관계 주입도 자동으로 하는 @Autowired라는 기능도 제공한다
}
