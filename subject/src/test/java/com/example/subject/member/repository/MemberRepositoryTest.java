package com.example.subject.member.repository;

import com.example.subject.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static com.example.subject.fixture.MemberFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("페이지네이션으로 회원을 조회할 수 있어야 한다")
    @Test
    void findAllTest() {
        for (int i = 1; i <= 20; i++) {
            memberRepository.save(new Member());
        }

        Page<Member> members = memberRepository.findAll(PageRequest.of(2, 5));
        assertThat(members.getSize()).isEqualTo(5);
    }

    @DisplayName("이름 또는 이메일로 조회할 수 있어야 한다")
    @Test
    void findByNameOrEmailTest() {
        memberRepository.save(new Member("name1", TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, "a@gmail.com", TEST_GENDER));
        memberRepository.save(new Member("name2", TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, "b@gmail.com", TEST_GENDER));
        memberRepository.save(new Member("name3", TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, "c@gmail.com", TEST_GENDER));

        Page<Member> members = memberRepository.findByNameOrEmail("name1", "b@gmail.com", PageRequest.of(0, 3));

        assertThat(members.getTotalElements()).isEqualTo(2);
    }
}