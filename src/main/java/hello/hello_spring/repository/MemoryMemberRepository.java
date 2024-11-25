package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository            //Component 방식  (Component Scan)
public class MemoryMemberRepository implements MemberRepository{

    //실무에서는 동시성 문제가 있을 수 있어서 공유되는 변수일때는 concurrent HashMap을 써야 한다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;      //실무에서는 동시성을 고려, atomicLong 등을 사용해야한다.
    @Override
    public Member save(Member member) {
        member.setId(++sequence);       //id 세팅
        store.put(member.getId(), member);      //store (맵)에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
//        return store.get(id);           //과거는 그대로 값으로 짰음
        return Optional.ofNullable(store.get(id));        //최근엔 null이 반환될 가능성이 있으면 optional로 감싸주는게 요즘메타?
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    //getName이 파라미터로 넘어온 값이랑 같은지 확인
                .findAny();     //하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
