package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service            //Component 방식  (Component Scan)

@Transactional      //JPA를 사용하려면 꼮 써야함
public class MemberService {

    //    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 필드 주입은 Controller 클래스
    private final MemberRepository memberRepository;        //생성자 매개변수 (구조)
    @Autowired      //*** di 생성자 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;               // 다시 해보기
    }


    /*
     * 회원가입
     */
    public Long     join(Member member) {
        //같은 이름이 있는 중복 회원
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(member1 -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // 코드 리펙터링1
//        memberRepository.findByName(member.getName())
//                .ifPresent(member1 -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });

        // 코드 리펙터링2
        validateDuplicateMember(member);


        // 유효성 검사후 리턴
        memberRepository.save(member);
        return member.getId();
    }

    /*
        전체회원 조회*/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {

        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

}
