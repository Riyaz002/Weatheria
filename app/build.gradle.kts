import java.io.FileInputStream
import java.util.Locale
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

    val exclusions = listOf(
        "**/R.class",
        "**/R\$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*"
    )

    applicationVariants.all {
        val variantName = this.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }

        val unitTest = "test${variantName}UnitTest"
        val androidTest = "connected${variantName}AndroidTest"

        tasks.register<JacocoReport>("Jacoco${variantName}CodeCoverage") {
            dependsOn(listOf(unitTest, androidTest))
            // Set task grouping and description
            group = "Reporting"
            description = "Execute UI and unit tests, generate and combine Jacoco coverage report"

            reports {
                xml.required.set(true)
                html.required.set(true)
            }

            sourceDirectories.setFrom(layout.projectDirectory.dir("src/main"))

            classDirectories.setFrom(
                files(
                    fileTree(layout.buildDirectory.dir("intermediates/javac/")) { exclude(exclusions) },
                    fileTree(layout.buildDirectory.dir("intermediates/javac/")) { exclude(exclusions) }
                )
            )

            executionData.setFrom(files(
                fileTree(layout.buildDirectory) { include(listOf("**/*.exec", "**/*.ec")) }
            ))
        }
    }

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
        resValue("string", "GOOGLE_API_KEY", properties.getProperty("GOOGLE_API_KEY"))
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
            enableAndroidTestCoverage = true
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

tasks.withType(Test::class){
    configure<JacocoTaskExtension>{
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.rules)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(libs.androidx.ui.test)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.ui.test.manifest)


    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(libs.retrofit)

    //hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
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