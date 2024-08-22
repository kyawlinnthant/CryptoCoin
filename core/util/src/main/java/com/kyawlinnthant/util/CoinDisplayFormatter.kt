package com.kyawlinnthant.util

import java.util.Locale
import kotlin.math.abs

object CoinDisplayFormatter {
    const val INVITE_URL = "https://careers.lmwn.com/"
    private const val DIVIDER = 5
    private const val TRILLION_LENGTH = 12
    private const val BILLION_LENGTH = 9
    private const val MILLION_LENGTH = 6

    private const val TRILLION = 1_000_000_000_000.0 // 12
    private const val BILLION = 1_000_000_000.0 // 9
    private const val MILLION = 1_000_000.0 // 6

    private fun generateArray(size: Int = 50_000): IntArray {
        val array = IntArray(size)
        array[0] = DIVIDER
        for (i in 1 until size) {
            array[i] = array[i - 1] * 2
        }
        return array
    }

    fun shouldShowInviteFriend(rank: Int): Boolean {
        val rules = generateArray()
        return rules.contains(rank)
    }

    fun formatCoinChange(input: String): CoinChange {
        val double = input.toDoubleOrNull()
        double?.let {
            val twoDecimal = String.format(locale = Locale.getDefault(), "%.2f", abs(it))
            if (it >= 0) {
                return CoinChange.Green(twoDecimal)
            } else {
                return CoinChange.Red(twoDecimal)
            }
        } ?: run {
            return CoinChange.Default
        }
    }

    fun formatCoinPrice(
        input: String,
        desiredDecimalPlace: CoinPriceDecimalPlace = CoinPriceDecimalPlace.ForItem,
    ): String {
        input.toDoubleOrNull() ?: return ""

        val parts = input.split(".")
        val actualValue = parts[0]
        val decimalValue = parts[1]
        val stringBuilder = StringBuilder(actualValue.reversed())
        for (i in 3 until stringBuilder.length step 4) {
            stringBuilder.insert(i, ',')
        }
        val expectedActual = stringBuilder.toString().reversed()
        if (decimalValue.length >= desiredDecimalPlace.place) {
            val expectedDecimal =
                decimalValue.substring(startIndex = 0, endIndex = desiredDecimalPlace.place)
            return "$ $expectedActual.$expectedDecimal"
        } else {
            return "$ $expectedActual.$decimalValue"
        }
    }

    fun formatCoinMarketCap(input: String): String {
        val double = input.toDoubleOrNull()
        double?.let {
            val length = input.length
            val formattedNumber =
                when {
                    length >= TRILLION_LENGTH -> {
                        val trillionDecimal = it / TRILLION
                        val output =
                            String.format(locale = Locale.getDefault(), "%.2f", trillionDecimal)
                        "$output trillion"
                    }

                    length >= BILLION_LENGTH -> {
                        val billionDecimal = it / BILLION
                        val output = String.format(locale = Locale.getDefault(), "%.2f", billionDecimal)
                        "$output billion"
                    }

                    length >= MILLION_LENGTH -> {
                        val millionDecimal = it / MILLION
                        val output = String.format(locale = Locale.getDefault(), "%.2f", millionDecimal)
                        "$output million"
                    }

                    else -> {
                        input
                    }
                }
            return "$ $formattedNumber"
        } ?: run {
            return ""
        }
    }
}

sealed class CoinChange(val value: String) {
    data class Green(val twoDecimal: String) : CoinChange(value = twoDecimal)

    data class Red(val twoDecimal: String) : CoinChange(value = twoDecimal)

    data object Default : CoinChange(value = "")
}

sealed class CoinPriceDecimalPlace(val place: Int) {
    data object ForItem : CoinPriceDecimalPlace(place = 5)

    data object ForDetail : CoinPriceDecimalPlace(place = 2)
}
