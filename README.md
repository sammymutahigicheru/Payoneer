[![Android CI](https://github.com/sammymutahigicheru/Payoneer/actions/workflows/main.yml/badge.svg)](https://github.com/sammymutahigicheru/Payoneer/actions/workflows/main.yml)

## Run Locally

Clone the project from GitHub and open it using Android Studio. This project was developed using the latests Android Studio

## Running tests

The tests in this code are UI tests.Use the default run configuration from Android Studio and you should see them passing.

## Tech Stack

**Language:** 100% Java

**MVVM Design Pattern:** As recommended by Google including tools like ViewModels, LiveData

**Dependency Injection:** Using the newly released Dagger Hilt library from Google

**Testing:** Unit tests

**Threading:** RxJava









## Documentation

The project is relatively straightforward. The app gets the data from the provided API using `RxJava2`, which also handles setting the UI states of the app. The state and data are handled by a helper `Resource` class and `enums`, which is then exposed to the UI as an observable `LiveData`. Using a simple switch statement, we then update the UI accordingly.

  
