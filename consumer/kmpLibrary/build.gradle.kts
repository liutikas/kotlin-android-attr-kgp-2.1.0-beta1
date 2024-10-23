plugins {
    alias(libs.plugins.androidLibrary)
}

dependencies {
    implementation("org.example.kmplibrary:kmpLibrary:1.0")
}

android {
    namespace = "org.example.consumer"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
