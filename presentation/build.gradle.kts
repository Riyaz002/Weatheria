plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.riyaz.presetation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "com.riyaz.presetation.HiltTestRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

//    // compose testing
    androidTestImplementation(libs.androidx.ui.test.junit4)

    //hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    // For instrumented tests.
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)

    //retrofit
    implementation(libs.retrofit)

    //hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    //moshi
    implementation(libs.converter.moshi)

    //chucker
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    //room
    implementation(libs.androidx.room.runtime)

    androidTestImplementation(project(":app"))
    androidTestImplementation(project(":di"))
    implementation(project(":domain"))
    implementation(project(":data"))
}