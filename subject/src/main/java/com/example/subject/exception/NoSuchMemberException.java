package com.example.subject.exception;

public class NoSuchMemberException extends RuntimeException {
    public NoSuchMemberException(Long memberId) {
        super(String.format("%d에 해당하는 회원이 존재하지 않습니다.", memberId));
    }

    public NoSuchMemberException(String email) {
        super(String.format("%s에 해당하는 회원이 존재하지 않습니다.", email));
    }
}
