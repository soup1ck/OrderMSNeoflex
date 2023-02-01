package ru.neoflex.conveyor.service.scoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

class ScoringServiceTest {

    private final ScoringService scoringService = new ScoringService();

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(scoringService, "baseRate", BigDecimal.ONE);
    }

    @Test
    @DisplayName(value = "Вычисление ставки")
    void calculateRate() {
        BigDecimal calculatedRateTrue = scoringService.calculateRate(true, true);
        Assertions.assertEquals(new BigDecimal("0.5"), calculatedRateTrue);
        BigDecimal calculatedRateFalse = scoringService.calculateRate(false, false);
        Assertions.assertEquals(new BigDecimal("1.5"), calculatedRateFalse);
        BigDecimal calculatedRateFalseTrue = scoringService.calculateRate(false, true);
        Assertions.assertEquals(new BigDecimal("0.75"), calculatedRateFalseTrue);
    }

    @Test
    @DisplayName(value = "Вычисление итогового платежа")
    void calculateTotalAmount() {

        BigDecimal requestedAmount = new BigDecimal("10000");
        BigDecimal calculatedTotalAmount = scoringService.calculateTotalAmount(requestedAmount, true, true);
        Assertions.assertEquals(new BigDecimal("13000"), calculatedTotalAmount);
    }
}