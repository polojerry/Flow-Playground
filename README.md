# Flow-Playground
A sample app to demonstrate different use cases of Kotlin Flow. The App Uses [The Movie Database (TMDB)](https://www.themoviedb.org/) API, [Room Database](https://developer.android.com/training/data-storage/room), and [Firebase Firestore](https://firebase.google.com/docs/firestore/)

## Development Environment
* Android Studio Arctic Fox | 2020.3.1 Patch 2
* Language: Kotlin
* Build System: Gradle

## Features
* Clean Architecture + Model View Model Model Pattern + Repository Pattern.
* Jetpack Libraries and Architecture Component
* Github Action for CI
* Kotlin Gradle DSL

## Architecture
Clean architecture helps organizing the project into different layers so that it's easy to understand, scale and debug when need arises.
There are [Multiple ways of defining Clean Architecture layers](https://proandroiddev.com/multiple-ways-of-defining-clean-architecture-layers-bbb70afa5d4a).

In this application i have used Clean Architecture, having 3 packages (presentation, domain, data) inside the app module.


### Domain
This is the layer that contains all the business logic. It contains all the abstract definition and the inner most.
In this project the domain layers holds the definitions of use-cases/iteractors, domain data models and repository interfaces. 

```usecases/ interactors```  they are the business logic executors that converts and passes user actions like fetch data from data source either remote or local and gives it back to the requester, in this case its the presenter (app).
the act as mediators between the repository and data layer

```repository interface``` interface that must be implemented by data layer

### Data
The data module consists of network models, mappers, API services, and repository implementations. This is the layer that repository patter is implemented. Repository pattern is used to abstract 
away concrete implementation of data source

```mappers```  they are used to convert/maps one data type to the other eg network model to domain model

In this project, i have only implemented the ```network data source``` where data is only fetched from the internet.

### Presentation
This layer contains the implementation of the three inner layers. This layers contains  User Interface, mainly Android Stuff like Activities, Fragments, ViewModel, 

#Flow Use Case
## Callback Based APIs: Firebase Firestore

## Room Datbase

## Retrofit: Rest API

# Libraries
* [Android Jetpack](https://developer.android.com/jetpack)
   * [Data Binding](https://developer.android.com/topic/libraries/data-binding/) The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
   * [Live Data](https://developer.android.com/topic/libraries/architecture/livedata) LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components updating app component observers that are in an active lifecycle state.
   * [Navigation](https://developer.android.com/guide/navigation/) Android Jetpack's Navigation component helps you implement effective navigation.
   * [Hilt](https://dagger.dev/hilt/) Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
* [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) Executing code asynchronously.
* [Retrofit](https://square.github.io/retrofit/) Type-safe HTTP client for Android and Java and Kotlin by Square, Inc. 
* [Moshi](https://github.com/square/moshi) Moshi is a modern JSON library for Android and Java. It makes it easy to parse JSON into Java objects:
* [OkHttp interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) Logs HTTP requests and responses
* [Material Design](https://material.io/develop/android/) Build beautiful, usable products using Material Components for Android
* [JUnit4](https://junit.org/junit4/) Unit Testing
* [Truth](https://truth.dev/) Fluent assertions
* [Espresso](https://developer.android.com/training/testing/espresso) Automated testing UI test
* [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) A scriptable web server for testing HTTP clients
