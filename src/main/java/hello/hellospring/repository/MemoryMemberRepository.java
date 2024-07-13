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
        return store.values().stream() // .values() 저장된 모든 값을 Collection<V>타입으로 반환,
                                    // .stream() 배열의 요소들을 일련의 연산으로 처리할 수 있는 기능 제공
                .filter(member -> member.getName().equals(name))
                .findAny(); // 일치하는 회원을 찾으면 반환 없으면 null
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}


