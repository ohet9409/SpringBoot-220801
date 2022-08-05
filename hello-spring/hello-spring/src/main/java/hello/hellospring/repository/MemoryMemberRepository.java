package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 저장을 하기 전에 데이터를 메모리 어딘가에 저장해두기 위해 hashmap 사용
    private static Map<Long, Member> store = new HashMap<>();
    // 1씩 증가
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);;
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 결과가 null일 경우 클라이언트쪽에서 처리를 할 수 있게 해준다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다식: member.getName이 매게변수 name이랑 같은지 찾는다
        // findAny: 하나라도 찾는다.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();

    }

    @Override
    public List<Member> findAll() {
        // member 데이터들을 List로 반환
        return new ArrayList<>(store.values());
    }
}
