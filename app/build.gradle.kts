plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "diplomado.practice2"
    compileSdk = 35

    defaultConfig {
        applicationId = "diplomado.practice2"
        minSdk = 24
        targetSdk = 35
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
        viewBinding = true
    }
}

dependencies {
    //Para retrofit y Gson
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

//Adicional para el interceptor
    implementation(libs.logging.interceptor)

//Glide y Picasso
    implementation(libs.glide)
    implementation(libs.picasso)

//Para las corrutinas con alcance lifecycle (opcional)
    implementation(libs.androidx.lifecycle.runtime.ktx)

//Imágenes con bordes redondeados
    implementation(libs.roundedimageview)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}