package com.mobilebackend.util;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionUtility {

    public static <T> List<T> filter(
            List<T> list,
            Predicate<T> condition) {

        return list.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }
}