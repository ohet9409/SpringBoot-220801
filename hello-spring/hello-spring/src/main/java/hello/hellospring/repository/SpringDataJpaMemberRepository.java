package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// 인터페이스가 인터페이스를 상속받을 때는 extends
// JpaRepository<Entity 클래스, PK 타입>
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository{

//  select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);

}
