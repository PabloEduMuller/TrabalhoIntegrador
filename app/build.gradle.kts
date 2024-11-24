plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.trabalhointegrador"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.trabalhointegrador"
        minSdk = 28
        targetSdk = 34
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("androidx.appcompat:appcompat:1.3.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation ("com.google.android.material:material:1.3.0")
    implementation ("com.google.firebase:firebase-database:19.7.0") // Para banco de dados
    implementation ("com.google.firebase:firebase-auth:20.0.3")
    implementation("com.google.firebase:firebase-firestore")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("com.google.firebase:firebase-firestore:24.5.0")

}