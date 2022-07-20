package org.lastminute.salestaxes.util;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class MathUtilsUnitTest {

    @Test
    public void test_roundUpTax_ShouldReturnCorrectValue() {
        //given
        BigDecimal amount = new BigDecimal("1.499");

        //when
        BigDecimal result = MathUtils.roundUpTax(amount);

        //then
        assertThat(result).isEqualTo(new BigDecimal("1.50"));
    }

    @Test
    public void test_roundUpTax_ShouldReturnCorrectValue2() {
        //given
        BigDecimal amount = new BigDecimal("2.0111111111119");

        //when
        BigDecimal result = MathUtils.roundUpTax(amount);

        //then27.99
        assertThat(result).isEqualTo(new BigDecimal("2.05"));
    }

    @Test
    public void test_roundUpTax_ShouldReturnCorrectValue3() {
        //given
        BigDecimal amount = new BigDecimal("2.0899999999");

        //when
        BigDecimal result = MathUtils.roundUpTax(amount);

        //then27.99
        assertThat(result).isEqualTo(new BigDecimal("2.10"));
    }

    @Test
    public void test_roundUpTax_ShouldReturnCorrectValue4() {
        //given
        BigDecimal amount = new BigDecimal("2.07");

        //when
        BigDecimal result = MathUtils.roundUpTax(amount);

        //then27.99
        assertThat(result).isEqualTo(new BigDecimal("2.10"));
    }

    @Test
    public void test_roundUpTax_ShouldReturnCorrectValue5() {
        //given
        BigDecimal amount = new BigDecimal("1.05");

        //when
        BigDecimal result = MathUtils.roundUpTax(amount);

        //then27.99
        assertThat(result).isEqualTo(new BigDecimal("1.05"));
    }
}
