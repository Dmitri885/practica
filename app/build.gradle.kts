plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.practica"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.practica"
        minSdk = 33
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Существующие зависимости (оставляем как есть)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // !!! ДОБАВЬТЕ ЭТУ СТРОКУ - иконки Material !!!
    implementation("androidx.compose.material:material-icons-core:1.6.8")

    // ViewModel для Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Supabase (аутентификация и база данных)
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.5.0") // Обновим версию
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.5.0") // Обновим версию

    // Ktor (движок для сетевых запросов, нужен для Supabase)
    implementation("io.ktor:ktor-client-android:2.3.12") // Обновим версию
    implementation("io.ktor:ktor-client-cio:2.3.12") // Обновим версию
    implementation("io.ktor:ktor-client-core:2.3.12")

    // Coroutines (для асинхронной работы)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1") // Обновим версию
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

    // Тестовые зависимости
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}