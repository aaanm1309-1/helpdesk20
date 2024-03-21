package br.com.adrianomenezes.models.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Array;
import java.util.Arrays;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public enum OrderStatusEnum {
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    CLOSED("Closed"),
    CANCELED("Canceled");

    @Getter
    private final String description;

    public static OrderStatusEnum toEnum(final String description) {
        return Arrays.stream(OrderStatusEnum.values())
                .filter(orderStatusEnum -> orderStatusEnum.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid description: " + description));
    }

}
