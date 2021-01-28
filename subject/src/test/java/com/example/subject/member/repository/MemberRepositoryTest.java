package com.example.subject.member.repository;

import com.example.subject.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static com.example.subject.fixture.MemberFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("이메일로 회원을 조회할 수 있어야 한다")
    @Test
    void findByEmailTest() {
        memberRepository.save(new Member("name1", TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, "a@gmail.com", TEST_GENDER));
        memberRepository.save(new Member("name2", TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, "b@gmail.com", TEST_GENDER));
        memberRepository.save(new Member("name3", TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, "c@gmail.com", TEST_GENDER));

        Optional<Member> email = memberRepository.findByEmail("b@gmail.com");

        assertAll(
                () -> assertThat(email.isPresent()).isTrue(),
                () -> assertThat(email.get().getEmail()).isEqualTo("b@gmail.com")
        );
    }

    @DisplayName("이름 또는 이메일로 조회할 수 있어야 한다")
    @Test
    void findByNameOrEmailTest() {
        memberRepository.save(new Member("name1", TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, "a@gmail.com", TEST_GENDER));
        memberRepository.save(new Member("name2", TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, "b@gmail.com", TEST_GENDER));
        memberRepository.save(new Member("name3", TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, "c@gmail.com", TEST_GENDER));

        Page<Member> members = memberRepository.findByNameContainingOrEmailContaining("name1", "b@gmail.com", PageRequest.of(0, 3));

        assertThat(members.getTotalElements()).isEqualTo(2);
    }
}