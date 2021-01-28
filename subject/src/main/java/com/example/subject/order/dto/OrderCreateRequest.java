package com.example.subject.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class OrderCreateRequest {
    @NotNull
    @Length(max = 12)
    @Pattern(regexp = "[A-Z0-9]+")
    private final String orderNumber;

    @NotNull
    @Length(max = 100)
    private final String productName;

    @NotNull
    private final Long memberId;
}
