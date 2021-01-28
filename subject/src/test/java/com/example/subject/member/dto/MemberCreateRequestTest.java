package com.example.subject.member.dto;

import com.example.subject.member.domain.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;

class MemberCreateRequestTest {

    private static final String VALID_NAME = "황준호";
    private static final String VALID_NICKNAME = "junho";
    private static final String VALID_PASSWORD = "AAaa!!1234";
    private static final String VALID_PHONE_NUMBER = "1234";
    private static final String VALID_EMAIL = "email@gmail.com";
    private static final Gender VALID_GENDER = Gender.MALE;

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeEach
    void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    void tearDown() {
        validatorFactory.close();
    }

    @ParameterizedTest
    @CsvSource({"황준호,true", "Junho,true", "hwang준호,true", "!@#,false", "황준호황준호황준호황준호황준호황준호황준호,false"})
    void nameConstraintTest(String name, boolean expected) {
        boolean isValidInput = isValid(new MemberCreateRequest(name, VALID_NICKNAME, VALID_PASSWORD, VALID_PHONE_NUMBER, VALID_EMAIL, VALID_GENDER));

        assertThat(isValidInput).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"junho,true", "'junho ',false", "nick name,false", "Nickname,false", "nickname!,false", "nicknamenicknamenicknamenickname,false"})
    void nicknameConstraintTest(String nickname, boolean expected) {
        boolean isValidInput = isValid(new MemberCreateRequest(VALID_NAME, nickname, VALID_PASSWORD, VALID_PHONE_NUMBER, VALID_EMAIL, Gender.MALE));

        assertThat(isValidInput).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"AAaa!!1234,true", "AAaa!!12,false", "AAAA!!1234,false", "AAAAAAAAAA,false"})
    void passwordConstraintTest(String password, boolean expected) {
        boolean isValidInput = isValid(new MemberCreateRequest(VALID_NAME, VALID_NICKNAME, password, VALID_PHONE_NUMBER, VALID_EMAIL, VALID_GENDER));

        assertThat(isValidInput).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"01012345678,true", "119,true", "010 1234 5678,false", "123456789012345678901,false"})
    void phoneNumberConstraintTest(String phoneNumber, boolean expected) {
        boolean isValidInput = isValid(new MemberCreateRequest(VALID_NAME, VALID_NICKNAME, VALID_PASSWORD, phoneNumber, VALID_EMAIL, VALID_GENDER));

        assertThat(isValidInput).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"email@gmail.com,true", "email.gmail.com,false", "email,false", "email@gmail@com,false"})
    void emailConstraintTest(String email, boolean expected) {
        boolean isValidInput = isValid(new MemberCreateRequest(VALID_NAME, VALID_NICKNAME, VALID_PASSWORD, VALID_PHONE_NUMBER, email, VALID_GENDER));

        assertThat(isValidInput).isEqualTo(expected);
    }

    private boolean isValid(MemberCreateRequest request) {
        return validator.validate(request).isEmpty();
    }
}