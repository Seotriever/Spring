package hello.hello_spring.service;
import hello.hello_spring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration      //spring이 관리 해주기 때문에 jdbc autowired 어노테이션을 쓰면 잡게된다
public class SpringConfig {

    //    private DataSource dataSource;          //  jdbc, jdbcTemplate 을 사용하기 위해서 선언~
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;       //  ~/
//    }
    private EntityManager em;
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean               // 직접 bean 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }


    @Bean               // 직접 bean 등록
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();        //MemoryMemberRepository는 구현체
//        return new JdbcMemberRepository(dataSource);      //jdbc로 변경 (간단)
//        return new JdbcTemplateMemberRepository(dataSource);      //JdbcTemp
        return new JpaMemberRepository(em);        //   JPA 방식
    }
}
