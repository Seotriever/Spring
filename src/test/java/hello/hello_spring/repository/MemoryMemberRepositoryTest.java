package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {


//  MemberRepository repository = new MemoryMemberRepository();       //MemoryMemberRepository 만 확인 하기위해
    MemoryMemberRepository repository = new MemoryMemberRepository();      //MemoryMemberRepository 로 변경해서 확인

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }



    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member));       // t,f같은 값으로만 확인 할 수 없으니
//        Assertions.assertEquals(result, member);        // 정상 작동 유무
        Assertions.assertThat(member).isEqualTo(result);        // 요즘 편하게 쓰는 테스트 , static으로 표현가능

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);

    }


}
