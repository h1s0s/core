package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(
        /* 이 두개를 지정하지 않으면, AutoAppConfig를 기준으로 탐색함
        그렇기 때문에 컴포넌트 스캔 설정정보 클래스를 프로젝트 최상단에 두는걸 권장함
        basePackages = "hello.core.member", //어디서부터 탐색을 시작할지 지정
        basePackageClasses = AutoAppConfig.class, //지정한 클래스를 탐색위치로 지정
        */
        excludeFilters =  @Filter(type = FilterType.ANNOTATION,
        classes = Configuration.class))
public class AutoAppConfig {
    //컴포넌트 스캔이라는 기능을 사용하면, 스프링 설정정보가 없어도 자동으로 스트링 빈을 등록함
    //컴포넌트스캔은 컴포넌트라는 어노테이션을 등록한 빈을 자동으로 찾아서 등록해줌
    //excludeFilters는 자동으로 등록되지 않게 빼주는 기능
    //보통 설정정보를 실무에서 제외하진 않음
    //또 의존관계 주입도 자동으로 하는 @Autowired라는 기능도 제공한다


    //자동 빈 등록 vs 자동 빈 등록 = 충돌
    //자동 빈 등록 vs 수동 빈 등록 = 수동 빈 등록이 우선
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
