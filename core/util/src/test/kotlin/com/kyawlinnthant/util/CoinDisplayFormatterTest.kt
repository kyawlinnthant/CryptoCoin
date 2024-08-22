package com.kyawlinnthant.util

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CoinDisplayFormatterTest {
    @DisplayName("coin change - two decimal ceiling - positive or negative")
    @Test
    fun `format coin change check`() =
        runTest {
            // for negative
            val actualNegativeResult1 = CoinDisplayFormatter.formatCoinChange("-6.17")
            val expectedNegativeResult1 = CoinChange.Red("6.17")
            assertThat(expectedNegativeResult1).isEqualTo(actualNegativeResult1)

            val actualNegativeResult2 = CoinDisplayFormatter.formatCoinChange("-60.171276")
            val expectedNegativeResult2 = CoinChange.Red("60.17")
            assertThat(expectedNegativeResult2).isEqualTo(actualNegativeResult2)

            // for positive
            val actualPositiveResult1 = CoinDisplayFormatter.formatCoinChange("6.17")
            val expectedPositiveResult1 = CoinChange.Green("6.17")
            assertThat(expectedPositiveResult1).isEqualTo(actualPositiveResult1)

            val actualPositiveResult2 = CoinDisplayFormatter.formatCoinChange("698.176473")
            val expectedPositiveResult2 = CoinChange.Green("698.18")
            assertThat(expectedPositiveResult2).isEqualTo(actualPositiveResult2)
        }

    @DisplayName("coin change with input format wrong - Default Empty String")
    @Test
    fun `format coin change default`() =
        runTest {
            val actualResult = CoinDisplayFormatter.formatCoinChange("no number string")
            val expectedResult = CoinChange.Default
            assertThat(expectedResult).isEqualTo(actualResult)
        }

    @DisplayName("coin maker cap - two decimal with suffix")
    @ParameterizedTest
    @CsvSource(
        "1000000, $ 1.00 million",
        "1000000000, $ 1.00 billion",
        "1000000000000, $ 1.00 trillion",
    )
    fun `format market correct`(
        input: String,
        expected: String,
    ) = runTest {
        val actualResults = CoinDisplayFormatter.formatCoinMarketCap(input)
        assertThat(expected).isEqualTo(actualResults)
    }

    @DisplayName("coin marker cap - below million 6 digit returns input")
    @Test
    fun `format market below million`() =
        runTest {
            val actualResult = CoinDisplayFormatter.formatCoinMarketCap("100")
            assertThat(actualResult).isEqualTo("$ 100")
        }

    @DisplayName("coin marker cap - wrong input returns empty")
    @Test
    fun `format market wrong`() =
        runTest {
            val actualResult = CoinDisplayFormatter.formatCoinMarketCap("linn")
            assertThat(actualResult).isEmpty()
        }

    @DisplayName("coin price - item - 5 decimal")
    @Test
    fun `format coin price - item - 5`() =
        runTest {
            val actual1 =
                CoinDisplayFormatter.formatCoinPrice(
                    input = "12343449.945292531337",
                    desiredDecimalPlace = CoinPriceDecimalPlace.ForItem,
                )
            assertThat(actual1).isEqualTo("$ 12,343,449.94529")

            val actual2 =
                CoinDisplayFormatter.formatCoinPrice(
                    input = "12.945292531337",
                    desiredDecimalPlace = CoinPriceDecimalPlace.ForItem,
                )
            assertThat(actual2).isEqualTo("$ 12.94529")
        }

    @DisplayName("coin price - detail - 2 decimal")
    @Test
    fun `format coin price - item - 2`() =
        runTest {
            val actual1 =
                CoinDisplayFormatter.formatCoinPrice(
                    input = "12343449.945292531337",
                    desiredDecimalPlace = CoinPriceDecimalPlace.ForDetail,
                )
            assertThat(actual1).isEqualTo("$ 12,343,449.94")

            val actual2 =
                CoinDisplayFormatter.formatCoinPrice(
                    input = "12.945292531337",
                    desiredDecimalPlace = CoinPriceDecimalPlace.ForDetail,
                )
            assertThat(actual2).isEqualTo("$ 12.94")
        }

    @DisplayName("coin price - incorrect format")
    @Test
    fun `format coin price - incorrect`() =
        runTest {
            val actual =
                CoinDisplayFormatter.formatCoinPrice(
                    input = "linn thant",
                )
            assertThat(actual).isEmpty()
        }

    // 5, 10, 20, 40, 80, 160, 320, ...
    @DisplayName("check should show invite friend")
    @ParameterizedTest
    @CsvSource(
        "5, true",
        "320, true",
        "7, false",
    )
    fun `show invite friend`(
        input: Int,
        expected: Boolean,
    ) = runTest {
        val actualResults = CoinDisplayFormatter.shouldShowInviteFriend(input)
        assertThat(expected).isEqualTo(actualResults)
    }
}
