# Architecture Overview

## Project Structure

This is a **Kotlin Multiplatform (KMP)** project targeting both **Android** and **iOS** platforms using **Compose Multiplatform** for the UI layer.

### Directory Layout

```
KotlinProject/
├── composeApp/                 # Shared multiplatform code
│   ├── src/
│   │   ├── commonMain/        # Shared code for all platforms
│   │   ├── androidMain/       # Android-specific implementations
│   │   ├── iosMain/          # iOS-specific implementations
│   │   └── CommonTest/        # Shared tests
│   └── build.gradle.kts       # Compose App build configuration
├── iosApp/                     # Native iOS app wrapper
│   ├── iosApp/
│   │   ├── iOSApp.swift       # App entry point
│   │   ├── ContentView.swift  # SwiftUI content
│   │   └── Assets.xcassets/   # iOS assets
│   └── iosApp.xcodeproj/      # Xcode project
└── gradle/                     # Gradle configuration
    └── libs.versions.toml      # Dependency versions
```

## Architecture Layers

### 1. **Presentation Layer**
- Location: `commonMain/kotlin/org/demo/project/features/*/presentation/`
- **Composables**: UI components built with Compose Multiplatform
- **ViewModels**: UI state management using MVVM pattern
- **State Management**: Leverages Kotlin Coroutines and Koin for DI

### 2. **Domain Layer**
- Location: `commonMain/kotlin/org/demo/project/features/*/domain/`
- **Use Cases**: Business logic encapsulation
- **Repositories (Interfaces)**: Data access contracts
- **Models**: Domain entities and DTOs

### 3. **Data Layer**
- Location: `commonMain/kotlin/org/demo/project/features/*/data/`
- **Repositories (Implementation)**: Concrete data access
- **Local DataSource**: Room Database
- **Remote DataSource**: Ktor HTTP client

## Key Technologies

### Multiplatform Libraries
- **Compose Multiplatform** (1.8.1): Cross-platform UI
- **Kotlin** (2.2.0-RC): Language
- **Kotlinx Coroutines** (1.9.0): Async programming
- **Kotlinx Serialization** (1.7.3): JSON serialization
- **Kotlinx DateTime** (0.6.0): Date/time handling

### Dependency Injection
- **Koin** (4.0.0): Service locator & DI container

### Networking
- **Ktor Client** (3.1.2): HTTP client
  - OkHttp engine for Android
  - Darwin engine for iOS
  - Content negotiation & logging support

### Database
- **Room** (2.7.0-alpha11): Local persistence
- **SQLite** (2.5.0-alpha11): Database engine

### Image Loading
- **Coil** (3.0.0-rc02): Image caching & loading
- **Kamel** (1.0.3): Additional media support

### Navigation
- **Jetbrains Navigation Compose** (2.8.0-alpha10): Navigation management

### Maps
- **Google Maps Compose** (4.3.3): Maps integration

### Additional Tools
- **KSP** (2.1.21-2.0.1): Kotlin Symbol Processing
- **Material3**: Material Design 3 components
- **Paging Compose** (3.3.0-alpha02-0.4.0): Pagination support

## Build Configuration

### Gradle Setup
- **AGP**: Android Gradle Plugin 8.9.1
- **Android Min SDK**: 24
- **Android Target SDK**: 35
- **Android Compile SDK**: 35

### Secrets Management
- **Secrets Gradle Plugin** (2.0.1): For API keys and sensitive data

## Feature Modules

Each feature follows the Clean Architecture pattern:

```
features/
├── books/
│   ├── data/
│   │   ├── database/
│   │   ├── remote/
│   │   └── repository/
│   ├── domain/
│   │   ├── models/
│   │   ├── repositories/
│   │   └── usecases/
│   └── presentation/
│       ├── viewmodels/
│       └── screens/
└── map/
    ├── data/
    ├── domain/
    └── presentation/
```

## Platform-Specific Implementations

### Android
- **Location**: `androidMain/`
- Uses Android-specific APIs and services
- Material 3 components
- Android Context management

### iOS
- **Location**: `iosMain/`
- Native iOS frameworks integration
- Swift interoperability
- iOS-specific permissions handling

## Design Patterns

1. **MVVM** (Model-View-ViewModel)
   - ViewModels manage UI state
   - Composables are dumb UI presenters

2. **Repository Pattern**
   - Abstract data sources
   - Single responsibility for data access

3. **Dependency Injection**
   - Koin modules for feature configuration
   - Constructor injection preferred

4. **Clean Architecture**
   - Separation of concerns
   - Independent layers
   - Easy to test

## State Management

- **ViewModel**: Holds UI state and business logic
- **Coroutines**: Handles async operations
- **Compose State**: Manages local UI state
- **Koin**: Provides dependencies

## Testing Strategy

- **Location**: `CommonTest/`
- **Unit Tests**: Business logic testing
- **Integration Tests**: Repository and API testing
- **UI Tests**: Composable behavior testing

## Build & Deployment

### Debug Build
```bash
./gradlew build
```

### Release Build
```bash
./gradlew build -Prelease
```

### Android Deployment
- Builds to APK/AAB for Google Play

### iOS Deployment
- Xcode integration via Gradle build
- Builds to IPA or direct device installation

