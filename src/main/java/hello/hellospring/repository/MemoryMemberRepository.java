package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 가입을 하려는 회원에게 번호 부여
        store.put(member.getId(), member); // 부여된 번호와 회원 정보 매핑
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // ofNullable -> null 일 수도?
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store.values -> 값 꺼내고,
                .filter(member -> member.getName().equals(name)) // 값 비교
                .findAny(); // 일치하는 회원을 찾으변 반환 없으면 null
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}


