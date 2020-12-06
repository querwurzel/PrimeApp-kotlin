plugins {
    apply {
        id("com.android.application")
        id("dagger.hilt.android.plugin")
        kotlin("android")
        kotlin("kapt")
    }
}

android {

    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId("com.wilke.android.prime")
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode(1)
        versionName("1.0")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        val release = get("release")
        release.minifyEnabled(false)
        release.proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }

    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.name

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        val main = get("main")
        main.java.srcDirs("src/main/java", "src/main/kotlin")

        val test = get("test")
        test.java.srcDirs("src/test/kotlin")

        val androidTest = get("androidTest")
        androidTest.java.srcDirs("src/androidTest/kotlin")
    }

}

dependencies {

    implementation("com.google.dagger:hilt-android:2.30.1-alpha")
    kapt("com.google.dagger:hilt-android-compiler:2.30.1-alpha")

    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.9.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.20")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("com.google.android.material:material:1.2.1")

    testImplementation("junit:junit:4.13.1")

    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}
