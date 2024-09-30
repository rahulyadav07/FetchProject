# Fetch Project

This project is an Android application that demonstrates the use of **Kotlin Coroutines** for asynchronous threading, **Flow** for reactive programming, and **Dagger** for dependency injection. It also uses **Retrofit** to fetch data from a network and **Gson** to parse JSON responses.

## Features

- **Coroutines**: Used for asynchronous background operations like network calls.
- **Flow**: Manages reactive streams of data.
- **Dagger**: Handles dependency injection across the project.
- **Retrofit**: Simplifies network requests.
- **Gson**: Parses JSON data into Kotlin objects.

## Libraries Used

- **Kotlin Coroutines**: To simplify async operations.
- **Flow**: For handling data streams.
- **Dagger**: For injecting dependencies efficiently.
- **Retrofit**: For performing API requests.
- **Gson**: For JSON serialization and deserialization.

## Project Structure
The project follows the **MVVM** (Model-View-ViewModel) architecture pattern:
- data
  - api
  - model
  - repository
- di
   - component
   - module
   - scope
   - Qualifiers
- ui
  - activities
  - viewmodel
  - adapters
- utils


## How It Works

1. **Retrofit** makes API calls to fetch data.
2. **Coroutines** handle asynchronous tasks, ensuring non-blocking operations.
3. **Flow** streams data from the repository to the ViewModel.
4. **Dagger** injects dependencies such as `RetrofitService` and `FetchRepository`.
5. **Gson** deserializes JSON responses from the API.

 ## Expected Behaviour video

https://github.com/user-attachments/assets/031bc46b-0099-4c59-9219-1a9b1095174d
## How to Build the Project

1. Clone the repository in Android Studio.
2. Build the project.

If you encounter the error **DaggerApplicationComponent not found**, please rebuild the project

```kotlin
// here you can phase that issue
private fun injectDependencies() {
    applicationComponent = DaggerApplicationComponent
        .builder()
        .applicationModule(ApplicationModule(this))
        .build()
    applicationComponent.inject(this)
}``





