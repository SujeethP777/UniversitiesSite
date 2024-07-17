plugins {
    id(Plugin.androidLibrary)
    id(Plugin.androidKotlin)
    kotlin(Plugin.kapt)
    id(Plugin.hilt)
}
android {
    namespace = "com.dqitech.ifamer.domain"
    compileSdk = App.compileSdk

    defaultConfig {
        minSdk = App.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }

    kotlinOptions {
        jvmTarget = Compiler.jvmTarget
    }
}

dependencies {
    implementation(Dependency.coreKtx)
    implementation(project(Dependency.common))
    implementation(Dependency.compose)
    implementation(Dependency.composeNavigation)
    implementation(platform(Dependency.composeBom))
    implementation(Dependency.composeUI)
    implementation(Dependency.composeUIGraphics)
    implementation(Dependency.composePreview)
    implementation(Dependency.material3)
    implementation(Dependency.jsonSerialization)
    implementation(Dependency.retrofitGsonConverter)

    implementation(project(Dependency.common))

    implementation(Dependency.hilt)
    kapt(Dependency.hiltDagger)
    implementation(Dependency.hiltNav)

    testImplementation(Dependency.junit)
    androidTestImplementation(Dependency.junitAndroidEx)
    androidTestImplementation(Dependency.espresso)
    androidTestImplementation(Dependency.testRunner)
    androidTestImplementation(Dependency.testRules)
}
