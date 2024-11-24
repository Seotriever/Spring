package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach() {          // ***di
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test                                                                   //다시 해보기
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long savedId = memberService.join(member);
        //then
        Member fondMember = memberService.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(fondMember.getName());
    }                                                               //이것만 하면 반쪽자리 테스트 => 예외 처리의 부족함

    @Test
    public void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();     //실패해야 정상
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");     //지정한 예외가 발생한 메시지가 다르면 테스트 실패
//        }
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void 전체회원_조회() {
    }

    @Test
    void findOne() {
    }
}