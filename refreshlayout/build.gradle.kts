import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.vanik.android.maven.publish)
}

android {
    namespace = "io.github.frankieshao.refreshlayout"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.lottie)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

mavenPublishing {
    coordinates("io.github.frankieshao.refreshlayout", "refreshlayout", "1.0.0")
    pom {
        name.set("RefreshLayout")
        description.set("A simple and customizable PulltoRefresh library for Jetpack Compose, supporting custom loading indicators.")
        url.set("https://github.com/FrankieShao/PullToRefresh-Compose/")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/license/mit/")
                distribution.set("https://opensource.org/license/mit/")
            }
        }
        developers {
            developer {
                id.set("FrankieShao")
                name.set("Frank Shao")
                url.set("https://github.com/FrankieShao")
            }
        }
        scm {
            url.set("https://github.com/FrankieShao/PullToRefresh-Compose")
            connection.set("scm:git:github.com/FrankieShao/PullToRefresh-Compose.git")
            developerConnection.set("scm:git:ssh://git@github.com/FrankieShao/PullToRefresh-Compose.git")
        }
    }
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}