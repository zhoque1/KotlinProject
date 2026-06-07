# Setup & Installation Guide

## Prerequisites

Before setting up the project, ensure you have the following installed:

### Required Tools

1. **Java Development Kit (JDK)**
   - Version: 17 or higher
   - Download: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.java.net/)

2. **Git**
   - Version: 2.30 or higher
   - Download: [git-scm.com](https://git-scm.com/)

### Platform-Specific Requirements

#### For Android Development
- **Android Studio** (Latest)
  - Download: [Android Studio](https://developer.android.com/studio)
  - Includes Android SDK, emulator, and build tools
  - Configure with Android SDK 35+ installed

#### For iOS Development
- **macOS** (required for iOS development)
- **Xcode** (Latest)
  - Install via: `xcode-select --install` or App Store
  - Verify: `xcode-select -p` (should show `/Applications/Xcode.app/...`)
- **iOS Simulator** (included with Xcode)

#### For Both Platforms
- **Kotlin IDE** (IntelliJ IDEA Community/Ultimate or Android Studio)
  - Latest version with Kotlin plugin

## Initial Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd KotlinProject
```

### 2. Verify System Configuration

```bash
# Check Java version
java -version

# Check Git version
git --version

# Check Xcode (on macOS)
xcode-select -p

# Check CocoaPods (iOS dependency manager)
pod --version
```

### 3. Configure local.properties

Create or update `local.properties` file in the project root:

```properties
sdk.dir=/path/to/Android/SDK
```

**Example on macOS:**
```properties
sdk.dir=/Users/USERNAME/Library/Android/sdk
```

### 4. Gradle Wrapper

The project uses Gradle Wrapper, so no separate Gradle installation is needed:

```bash
# Make gradlew executable
chmod +x ./gradlew

# Verify Gradle
./gradlew -v
```

## Project Setup

### 1. Download Dependencies

```bash
./gradlew build
```

This will download all dependencies defined in `gradle/libs.versions.toml`.

### 2. Sync Project

**In Android Studio/IntelliJ:**
- File → Sync Now
- Or: `./gradlew sync`

### 3. Generate Resources

```bash
./gradlew kspCommonMainKotlinMetadata
```

This generates code using KSP (Kotlin Symbol Processing).

## Android Setup

### Emulator Configuration

1. **Open Android Virtual Device Manager**
   ```bash
   $ANDROID_HOME/emulator/emulator -list-avds
   ```

2. **Create or use existing AVD**
   - Recommended: Android 14+ (API level 34+)
   - Device: Pixel 6 or higher

3. **Launch Emulator**
   ```bash
   $ANDROID_HOME/emulator/emulator -avd <AVD_NAME>
   ```

### Build & Run Android App

```bash
# Build
./gradlew assembleDebug

# Run on emulator/device
./gradlew installDebug

# Or run directly
./gradlew run
```

## iOS Setup

### 1. Install CocoaPods Dependencies

```bash
cd iosApp
pod install
cd ..
```

### 2. Open iOS Project in Xcode

```bash
open iosApp/iosApp.xcodeproj
```

**Note**: Always use the `.xcodeproj` file, not the `.xcworkspace` if you haven't installed pods.

### 3. Configure Signing

In Xcode:
- Select **iosApp** → Target
- Go to **Signing & Capabilities**
- Set Team ID (sign with Apple ID or use automatic provisioning)

### 4. Build for iOS Simulator

```bash
# Via Gradle
./gradlew buildForSimulator

# Via Xcode
xcodebuild -scheme iosApp -configuration Debug -sdk iphonesimulator -derivedDataPath build/xcode
```

### 5. Run on iOS Simulator

```bash
# Start simulator
xcrun simctl boot "iPhone 15"  # or your preferred iPhone

# Run app
./gradlew iosRun
```

## API Configuration

### Adding API Keys

1. Create a `local.properties` file (if not exists):
   ```properties
   MAPS_API_KEY=your_google_maps_api_key_here
   ```

2. The Secrets Gradle Plugin will read these automatically

### Alternative: Environment Variables

```bash
export MAPS_API_KEY="your_key_here"
```

## Verification

### Verify Installation

```bash
# Build both platforms
./gradlew clean build

# Check for errors
./gradlew lint
```

### First Run

**Android:**
```bash
./gradlew run
```

**iOS:**
```bash
./gradlew iosRun
```

## IDE Setup

### Android Studio / IntelliJ IDEA

1. Open project folder
2. File → Open... → Select project root
3. Wait for indexing to complete
4. File → Project Structure → Verify SDK paths
5. Run → Edit Configurations → Create run configuration

### VS Code (Alternative)

1. Install Kotlin Language Server extension
2. Install KSP support extension
3. Install Gradle for Java extension

## Troubleshooting Common Issues

### Gradle Sync Fails
```bash
./gradlew clean
./gradlew build --refresh-dependencies
```

### Pod Installation Issues (iOS)
```bash
cd iosApp
rm -rf Pods Podfile.lock
pod install
```

### Java Heap Space Error
```bash
export GRADLE_OPTS="-Xmx4g"
./gradlew build
```

### Xcode Command Line Tools Not Found
```bash
sudo xcode-select --reset
xcode-select --install
```

## Environment Variables

Add to your shell profile (`.zshrc`, `.bashrc`, etc.):

```bash
# Java
export JAVA_HOME=$(dirname $(dirname $(readlink $(which java))))

# Android SDK
export ANDROID_HOME="$HOME/Library/Android/sdk"
export PATH="$ANDROID_HOME/platform-tools:$PATH"
export PATH="$ANDROID_HOME/emulator:$PATH"

# Gradle
export GRADLE_OPTS="-Xmx4g -XX:MaxPermSize=512m"
```

Then reload:
```bash
source ~/.zshrc  # or ~/.bashrc
```

## Next Steps

1. Read [DEVELOPMENT.md](./DEVELOPMENT.md) for development workflow
2. Check [ARCHITECTURE.md](./ARCHITECTURE.md) for project structure
3. Review [CONTRIBUTING.md](./CONTRIBUTING.md) for coding standards
4. Explore [TROUBLESHOOTING.md](./TROUBLESHOOTING.md) for common issues

