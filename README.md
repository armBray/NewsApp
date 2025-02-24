![image](https://github.com/user-attachments/assets/6f00077f-e0b7-4d31-9287-156cecc7183f)# NewsApp Project

Welcome to the NewsApp! This application allows users to view the latest news articles. To get started, you'll need to set up an API key to fetch the news data from NewsAPI.

## Android App Preview

Here is a preview of the Android app:

![App Screenshot Auth](assets/newsApp_auth.png)
![App Screenshot News Screen](assets/newsApp_newsScreen.png)
![App Screenshot News Detail Screen](assets/newsApp_newsDetailScreen.png)


## Prerequisites

Before you can run the project, make sure you have the following:

- Android Studio installed.
- An active [NewsAPI](https://newsapi.org/) account to obtain your API key.

## Setting up the Project

Follow these steps to set up the project and use the correct API key:

### 1. Obtain Your NewsAPI Key

- Go to [NewsAPI](https://newsapi.org/) and create an account if you don't already have one.
- After logging in, you'll be able to generate an API key.
- Copy the API key for later use.

### 2. Configure the API Key in the Project

To securely store and use the API key in your app, follow these steps:

1. Open your project in Android Studio.
2. Navigate to `com/example/newsapp/utils/Constants.kt`.
3. Add your API key to the file like this:

   ```properties
   <API_KEY_newsapi>=your_actual_api_key_here


## Fingerprint Authentication
This app uses fingerprint authentication to securely verify users and provide access to sensitive content, such as personal preferences or account settings. To use fingerprint authentication, you will need to have fingerprint recognition enabled on your device.

### How to Enable Fingerprint Authentication
1. Enable Fingerprint on Your Android Device
Follow these steps to enable fingerprint authentication on your Android device:

- Open Settings on your Android device.
- Scroll down and tap on Security or Biometrics and Security (this may vary depending on the device).
- Tap on Fingerprint or Fingerprints under the "Biometric" section.
- Follow the on-screen instructions to register your fingerprint. You will be asked to place your finger on the sensor multiple times to ensure it’s properly registered.
- Once your fingerprint is registered, make sure to enable the option to use it for authentication.

2. Enable Fingerprint Authentication in the App
Once you have enabled fingerprint recognition on your device, the app will automatically detect it when you attempt to log in. Follow the steps below in the app:
- On the login screen, you will see an option to enable fingerprint authentication.
- Tap on the option to enable it.
- The app will prompt you to scan your fingerprint on your device.
- Once the fingerprint is verified, you will be logged in automatically for subsequent app uses without needing to enter a password.
