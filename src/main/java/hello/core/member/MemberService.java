package hello.core.member;

import org.springframework.stereotype.Component;


public interface MemberService {

    void join(Member member);//가입

    Member findMember(Long memberId);//회원 조회
}
