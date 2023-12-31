plugins {
    id("com.android.application")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("C:\\Users\\john\\android.jks")
            storePassword = "Wordpass22!!"
            keyAlias = "rep"
            keyPassword = "Wordpass22!!"
        }
    }
    namespace = "com.sativa.beeralator_v2"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.sativa.beeralator_v2"
        minSdk = 28
        targetSdk = 34

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildToolsVersion = "34.0.0"
    ndkVersion = "25.2.9519653"
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}