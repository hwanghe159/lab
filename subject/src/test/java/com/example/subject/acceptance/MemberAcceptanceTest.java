package com.example.subject.acceptance;

import com.example.subject.auth.dto.LoginRequest;
import com.example.subject.auth.dto.LogoutRequest;
import com.example.subject.auth.dto.TokenResponse;
import com.example.subject.member.domain.Gender;
import com.example.subject.member.dto.MemberCreateRequest;
import com.example.subject.member.dto.MemberDetailResponse;
import com.example.subject.member.dto.MemberResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.example.subject.fixture.MemberFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/truncate.sql")
public class MemberAcceptanceTest {

    @LocalServerPort
    int port;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        objectMapper = new ObjectMapper();
    }

    public static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    @DisplayName("회원에 대한 작업을 수행할 수 있어야 한다.")
    @Test
    void manageMember() throws JsonProcessingException {
        //when: 회원 가입을 한다
        Long memberId = join(TEST_MEMBER_NAME, TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, TEST_EMAIL, TEST_GENDER);
        //then: 회원이 추가된다
        MemberDetailResponse savedMember = find(memberId);
        assertThat(savedMember.getId()).isEqualTo(memberId);

        //given: 회원이 존재한다
        //when: 존재하는 회원이 로그인 한다
        TokenResponse token = login(TEST_EMAIL, TEST_PASSWORD);
        //then: 로그인이 된다
        assertAll(
                () -> assertThat(token.getTokenType()).isEqualTo("Bearer"),
                () -> assertThat(token.getAccessToken()).isNotEmpty()
        );

        //given: 회원이 20명 존재한다
        for (int i = 1; i <= 20; i++) {
            join(TEST_MEMBER_NAME, TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, i + TEST_EMAIL, TEST_GENDER);
        }
        //when: 이름, 이메일, 페이징을 이용하여 회원을 검색한다
        List<MemberResponse> members = search(TEST_MEMBER_NAME, TEST_EMAIL, PageRequest.of(2, 5));
        //then: 해당하는 회원이 조회된다
        assertAll(
                () -> assertThat(members).hasSize(5)
        );

        //given: 회원이 존재한다
        //when: 로그인 한 회원이 로그아웃 한다
        //then: 로그아웃이 된다
        logout(token.getAccessToken(), token.getTokenType());
    }

    private List<MemberResponse> search(String name, String email, PageRequest pageRequest) {
        //@formatter:off
        return
                given()
                        .queryParam("page", pageRequest.getPageNumber())
                        .queryParam("size", pageRequest.getPageSize())
                        .queryParam("name", name)
                        .queryParam("email", email)
                .when()
                        .get("/members")
                .then()
                        .log().all()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .jsonPath()
                        .getList(".", MemberResponse.class);
        //@formatter:on
    }

    private Long join(String name, String nickname, String password, String phoneNumber, String email, Gender gender) throws JsonProcessingException {
        MemberCreateRequest request = new MemberCreateRequest(name, nickname, password, phoneNumber, email, gender);
        String inputJson = objectMapper.writeValueAsString(request);

        //@formatter:off
        return
                given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(inputJson)
                .when()
                        .post("/members")
                .then()
                        .log().all()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract().as(Long.class);
        //@formatter:on
    }

    private MemberDetailResponse find(Long memberId) {
        //@formatter:off
        return
                given()
                .when()
                        .get("/members/" + memberId)
                .then()
                        .log().all()
                        .statusCode(HttpStatus.OK.value())
                        .extract().as(MemberDetailResponse.class);
        //@formatter:on
    }

    private TokenResponse login(String email, String password) throws JsonProcessingException {
        LoginRequest request = new LoginRequest(email, password);
        String inputJson = objectMapper.writeValueAsString(request);
        //@formatter:off
        return
                given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(inputJson)
                .when()
                        .post("/login")
                .then()
                        .log().all()
                        .statusCode(HttpStatus.OK.value())
                        .extract().as(TokenResponse.class);
        //@formatter:on
    }

    private void logout(String accessToken, String tokenType) throws JsonProcessingException {
        LogoutRequest request = new LogoutRequest(accessToken, tokenType);
        String inputJson = objectMapper.writeValueAsString(request);
        //@formatter:off
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(inputJson)
        .when()
                .post("/logout")
        .then()
                .log().all()
                .statusCode(HttpStatus.OK.value());
        //@formatter:on
    }
}
