package com.kyawlinnthant.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val lineManTypo =
    Typography(
        headlineLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                lineHeight = 40.sp,
                fontSize = 32.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 0.sp,
            ),
        headlineMedium =
            TextStyle(
                fontFamily = FontFamily.Default,
                lineHeight = 36.sp,
                fontSize = 28.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 0.sp,
            ),
        headlineSmall =
            TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                letterSpacing = 0.sp,
            ),
        titleLarge =
            TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp,
            ),
        titleMedium =
            TextStyle(
                fontFamily = FontFamily.Default,
                lineHeight = 24.sp,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.15.sp,
            ),
        titleSmall =
            TextStyle(
                fontFamily = FontFamily.Default,
                lineHeight = 20.sp,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.1.sp,
            ),
        bodyLarge =
            TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
            ),
        bodyMedium =
            TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
            ),
        bodySmall =
            TextStyle(
                fontFamily = FontFamily.Default,
                lineHeight = 16.sp,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 0.4.sp,
            ),
        labelLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                lineHeight = 20.sp,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.1.sp,
            ),
        labelMedium =
            TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
            ),
        labelSmall =
            TextStyle(
                fontFamily = FontFamily.Default,
                lineHeight = 16.sp,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp,
            ),
        displayLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W400,
                letterSpacing = (-0.25).sp,
                lineHeight = 64.sp,
                fontSize = 57.sp,
            ),
        displayMedium =
            TextStyle(
                fontFamily = FontFamily.Default,
                lineHeight = 52.sp,
                fontSize = 45.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 0.sp,
            ),
        displaySmall =
            TextStyle(
                fontFamily = FontFamily.Default,
                lineHeight = 44.sp,
                fontSize = 36.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 0.sp,
            ),
    )
