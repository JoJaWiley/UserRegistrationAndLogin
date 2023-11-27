package com.cakefactory.user;

import lombok.Data;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Address {
    private final String line1;
    private final String line2;
    private final String postcode;
    private final String address = Stream.of(line1, line2, postcode).collect(Collectors.joining(", "));
}