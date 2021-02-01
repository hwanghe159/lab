package com.example.springdataredis.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Student")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student implements Serializable {
    private String id;
    private String name;
    private Gender gender;
    private int grade;
}