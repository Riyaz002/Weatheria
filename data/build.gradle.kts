import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
}

val properties = Properties()
properties.load(FileInputStream("secret.properties"))

android {
    namespace = "com.riyaz.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "GOOGLE_API_KEY", properties.getProperty("GOOGLE_API_KEY"))
        buildConfigField("String", "IP_GEOLOCATION_API_KEY", properties.getProperty("IP_GEOLOCATION_API_KEY"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    // To use Kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.room.compiler)

    implementation(libs.gson)

    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.moshi.kotlin)
    implementation(libs.converter.moshi)

    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    // For instrumented tests.
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)

    //location
    implementation(libs.play.services.location)

    //import domain
    implementation(project(path = ":domain"))
}