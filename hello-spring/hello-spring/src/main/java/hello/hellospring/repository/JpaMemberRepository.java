package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

//  jpa를 사용할려면 EntityManager를 주입 받아야 한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // em.find(조회 타입, 식별자);
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
//      em.createQuery("실행할 쿼리", 반환할 타입)
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
//              파라미터값 선언
                .setParameter("name", name)
//              리스트 형태로 반환
                .getResultList();
//      Optional로 처리하기 때문에 아래와 같이 반환 stream().findAny(): 첫번째 찾은 데이터를 반환(순서가 중요하지 않을때 사용)
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
//      em.createQuery("실행할 쿼리", 반환할 타입)
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }

    @Override
    public void clearStore() {

    }
}
