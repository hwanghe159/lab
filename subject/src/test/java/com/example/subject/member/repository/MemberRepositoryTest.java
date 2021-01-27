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
    void findAll() {
        for (int i = 1; i <= 20; i++) {
            memberRepository.save(new Member(TEST_MEMBER_NAME, TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, TEST_EMAIL + i, TEST_GENDER));
        }

        Page<Member> members = memberRepository.findAll(PageRequest.of(2, 5));
        assertThat(members.getSize()).isEqualTo(5);
    }
}