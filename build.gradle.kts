import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jlleitschuh.gradle.ktlint.KtlintExtension

private val ktlint = libs.plugins.ktlint.get().pluginId
private val detekt = libs.plugins.detekt.get().pluginId
private val ruleKtlint = libs.rule.ktlint.get()
private val ruleDetekt = libs.rule.detekt.get()
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.google.hilt) apply false
    alias(libs.plugins.secrets) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
}
buildscript {
    dependencies {
        classpath(libs.secrets.gradle.plugin)
    }
}
subprojects {
    apply(plugin = ktlint)
    configure<KtlintExtension> {
        debug.set(true)
        verbose.set(true)
        android.set(true)
    }
    apply(plugin = detekt)
    configure<DetektExtension> {
        parallel = true
        allRules = true
        autoCorrect = true
        buildUponDefaultConfig = true
        config.setFrom(file("${rootProject.rootDir}/config/detekt/detekt.yml"))
    }
}
