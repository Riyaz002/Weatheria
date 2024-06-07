import com.android.tools.build.jetifier.core.utils.Log
import dagger.hilt.android.plugin.util.getAndroidComponentsExtension
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.googleService)
    jacoco
}

val properties = Properties()
properties.load(FileInputStream("secret.properties"))

android {
    namespace = "com.riyaz.weatheria"
    compileSdk = 34

    testCoverage {
        version = "8.5.11"
    }


    defaultConfig {
        applicationId = "com.riyaz.weatheria"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.riyaz.weatheria.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        resValue("string", "GOOGLE_API_KEY", properties.getProperty("GOOGLE_API_KEY")?:"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            enableUnitTestCoverage = true
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

jacoco {
    toolVersion = "0.8.4"
    reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}



tasks.create("testCover", JacocoReport::class,) {
    dependsOn("test")

    reports {
        xml.required = false
        csv.required = false
        html.outputLocation.dir("${project.buildDir}/build/jacocoreport")
    }
}

tasks.create("jacocoTestReport", JacocoReport::class) {

    dependsOn("testDebugUnitTest", "createDebugCoverageReport")

    reports {
        xml.required = true
        html.required = true
        Log.e("Output location", html.outputLocation.get().toString())
    }

    val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*", "android/**/*.*")
    val debugTree = fileTree("${buildDir}/intermediates/classes/debug").exclude(fileFilter)
    val mainSrc = "${project.projectDir}/src/main/java"


    sourceDirectories.setFrom(files(mainSrc))
    classDirectories.setFrom(files(debugTree))
    executionData.setFrom(fileTree("$buildDir").include(listOf("jacoco/testDebugUnitTest.exec", "outputs/code-coverage/connected/*coverage.ec")))
}

tasks.assembleUnitTest{
    finalizedBy(tasks.get("testCover"))
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(libs.androidx.ui.test)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.retrofit)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    // For instrumented tests.
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)

    implementation(libs.converter.moshi)

    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    implementation(libs.accompanist.permissions)

    implementation(libs.androidx.room.runtime)

    implementation(project(path = ":data"))
    implementation(project(path = ":domain"))
    implementation(project(path = ":di"))
    implementation(project(path = ":presentation"))
}