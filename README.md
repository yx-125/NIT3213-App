# NIT3213 App
An Android application built for NIT3213 coursework.  
The app includes:
* **Login Page**: Authenticate with campus, username and student ID.
* **Dashboard**: Displays the list of entities retrieved from the vu-nit3213-api API.
* **Details Page**: Shows all details about a selected entity.


## Requirements
* Android Studio: Ladybug (2024.2.1) or newer
* JDK: 11
* Gradle: 8+
* Kotlin: 2.x
* Android SDK:
  * minSdk: 24 (Android 7.0)
  * targetSdk: 36 (Android 15)
  * compileSdk: 36


## Dependencies
This app uses the following libraries:
#### Core Android
* AndroidX Core KTX (androidx.core:core-ktx) – Kotlin extensions for Android APIs
* AppCompat (androidx.appcompat:appcompat) – Support for modern Android features
* ConstraintLayout (androidx.constraintlayout:constraintlayout) – Flexible UI layouts
* Material Components (com.google.android.material:material) – Material Design 3 widgets

#### Navigation
* Navigation Fragment KTX (androidx.navigation:navigation-fragment-ktx)
* Navigation UI KTX (androidx.navigation:navigation-ui-ktx)

#### Dependency Injection
* Hilt Android (com.google.dagger:hilt-android)
* Hilt Compiler (kapt) (com.google.dagger:hilt-compiler)

#### Networking
* Retrofit (com.squareup.retrofit2:retrofit) – REST client
* Moshi (com.squareup.moshi:moshi) – JSON parser
* Moshi Kotlin (com.squareup.moshi:moshi-kotlin)
* OkHttp (com.squareup.okhttp3:okhttp) – Networking
* Logging Interceptor (com.squareup.okhttp3:logging-interceptor) – Network logging
* Converter Moshi (com.squareup.retrofit2:converter-moshi) – Retrofit JSON converter
* Parcelize plugin for parcelable models

#### Testing
* JUnit 4 (junit:junit) – Unit testing framework
* MockK (io.mockk:mockk) – Mocking library
* Kotlin Coroutines Test (org.jetbrains.kotlinx:kotlinx-coroutines-test) – Testing coroutines
* AndroidX JUnit (androidx.test.ext:junit) – Instrumented tests

## Setup Instructions
1. **Clone this repository**:
   ```bash
   git clone https://github.com/yx-125/NIT3213-App.git
   cd NIT3213-App
2. **Open in Android Studio**:
   * Open **Android Studio**
   * Select **Open an Existing Project**
   * Choose the **NIT3213-App** folder
3. **Sync Gradle**
4. **Run the App**
   * Choose an emulator or connect a real device
   * Press Run ▶ in Android Studio
5. **Login Credentials**
   * Campus: footscray, sydney or br
   * Username: your **first name**
   * Password: your **student ID** (without s)
