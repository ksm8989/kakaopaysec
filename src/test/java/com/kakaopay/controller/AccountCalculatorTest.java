package com.kakaopay.controller;

import com.kakaopay.service.account.AccountCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountCalculatorTest {
    @Autowired
    private AccountCalculator accountCalculator;

    @Test
    void sumByYear() {
        List<Map<String, Long>> actual = accountCalculator.sumByYear(Arrays.asList("2018"));

        assertEquals(actual.size(), 1);
        Map<String, Long> first = actual.get(0);
        assertEquals(first.get("year"), 2018L);
        assertEquals(first.get("11111112"), 4009700L);
    }
}