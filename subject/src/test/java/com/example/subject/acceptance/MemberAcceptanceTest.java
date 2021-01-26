package com.example.subject.acceptance;

import com.example.subject.domain.Gender;
import com.example.subject.dto.MemberCreateRequest;
import com.example.subject.dto.MemberResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    @DisplayName("회원을 관리할 수 있어야 한다.")
    @Test
    void manageMember() throws JsonProcessingException {
        //when: 회원 가입을 한다
        Long memberId = join("name", "nickname", "password", "phoneNumber", "email", Gender.MALE);
        //then: 회원이 추가된다
        MemberResponse savedMember = find(memberId);
        assertThat(savedMember.getId()).isEqualTo(memberId);

        //given: 회원이 존재한다
        //when: 존재하는 회원이 로그인 한다
        //then: 로그인이 된다

        //given: 회원이 존재한다
        //when: 로그인 한 회원이 로그아웃 한다
        //then: 로그아웃이 된다
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

    private MemberResponse find(Long memberId) {
        //@formatter:off
        return
                given()
                .when()
                        .get("/members/" + memberId)
                .then()
                        .log().all()
                        .statusCode(HttpStatus.OK.value())
                        .extract().as(MemberResponse.class);
        //@formatter:on
    }
}
