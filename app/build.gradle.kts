plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.bda.mynewsapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.bda.mynewsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "NEWS_API_KEY", "\"fac7ee39e0be4e709decc8accca70676\"")
        buildConfigField("String", "NEWS_API_BASE_URL", "\"https://newsapi.org/v2/\"")
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.kotlin.compiler.ext.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.profileinstaller)
    kapt(libs.dagger.hilt.compiler)

    debugImplementation(libs.okhttp.logging.interceptor)

    implementation(project(":news-data"))
    implementation(project(":newsapi"))
    implementation(project(":features:news-main"))
    implementation(project(":database"))
    implementation(project(":news-common"))
    implementation(project(":news-uikit"))
}