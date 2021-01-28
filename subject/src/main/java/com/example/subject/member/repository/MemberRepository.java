package com.example.subject.member.repository;

import com.example.subject.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Page<Member> findByName(String name, Pageable pageable);

    Page<Member> findByEmail(String email, Pageable pageable);

    List<Member> findAllByNameOrEmail(String name, String email);

    Page<Member> findByNameOrEmail(String name, String email, Pageable pageable);
}
