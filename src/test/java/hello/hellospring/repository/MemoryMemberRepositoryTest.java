package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save(){
        Member member = new Member();
        member.setName("jimin");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        /*junit.jupiter Assertions.assertEquals(result, member);*/
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName(){
        Member student = new Member();
        student.setName("jimin");
        repository.save(student);

        Member teacher = new Member();
        teacher.setName("inkyeom");
        repository.save(teacher);

        Member result = repository.findByName("jimin").get();

        assertThat(result).isEqualTo(student);
    }
}
