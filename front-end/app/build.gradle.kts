@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.plogger"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.plogger"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Retrofit2
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // OkHttp
    // https://github.com/square/okhttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // ViewModel
    implementation(libs.lifecycle.viewmodel.ktx)
    // Lifecyle scope dependency
    implementation(libs.lifecycle.runtime.ktx)
    // Framework ktx dependency
    implementation(libs.fragment.ktx)
    implementation(libs.activity.ktx)

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    // RX
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
}