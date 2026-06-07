# Development Guide

## Development Workflow

### 1. Getting Started

```bash
# Clone repository
git clone <repository-url>
cd KotlinProject

# Setup environment
chmod +x ./gradlew
./gradlew build

# Open in IDE
open . -a "Android Studio"
```

## Build Commands

### Common Gradle Commands

```bash
# Clean build
./gradlew clean build

# Build without tests
./gradlew build -x test

# Build specific platform
./gradlew assembleDebug              # Android Debug APK
./gradlew buildForSimulator          # iOS Simulator

# Run tests
./gradlew test
./gradlew testDebugUnitTest          # Android unit tests

# Check code quality
./gradlew lint

# Generate KSP code
./gradlew kspCommonMainKotlinMetadata
```

## Running the App

### Android

#### On Emulator
```bash
# Start emulator first
$ANDROID_HOME/emulator/emulator -avd <AVD_NAME>

# Run app
./gradlew installDebug
./gradlew run
```

#### On Physical Device
```bash
# Enable USB debugging on device
# Connect device via USB

# Verify connection
adb devices

# Run app
./gradlew installDebug
adb shell am start -n org.demo.project/.MainActivity
```

#### Direct Build & Run
```bash
./gradlew run
```

### iOS

#### On Simulator
```bash
# List available simulators
xcrun simctl list devices

# Boot simulator (if needed)
xcrun simctl boot "iPhone 15"

# Run via Gradle
./gradlew iosRun

# Or via Xcode
xcodebuild -scheme iosApp -configuration Debug -sdk iphonesimulator -derivedDataPath build/xcode | xcpretty
```

#### On Physical Device
```bash
# Connect device via USB
# In Xcode: Select device from device menu
# Build for Running: Cmd + R

# Or via command line:
./gradlew iosInstall
```

## Code Structure

### Feature Module Template

When creating a new feature, follow this structure:

```
composeApp/src/commonMain/kotlin/org/demo/project/features/[feature_name]/
├── data/
│   ├── database/
│   │   └── [EntityName]Entity.kt
│   ├── remote/
│   │   ├── [FeatureName]ApiService.kt
│   │   └── dto/
│   │       └── [ResponseDto].kt
│   └── repository/
│       └── [FeatureName]RepositoryImpl.kt
├── domain/
│   ├── models/
│   │   └── [DomainModel].kt
│   ├── repositories/
│   │   └── [FeatureName]Repository.kt
│   └── usecases/
│       └── [UseCaseName]UseCase.kt
└── presentation/
    ├── viewmodels/
    │   └── [FeatureName]ViewModel.kt
    ├── screens/
    │   └── [FeatureScreen].kt
    └── components/
        └── [CustomComponent].kt
```

### Naming Conventions

| Element | Convention | Example |
|---------|-----------|---------|
| Packages | lowercase | `org.demo.project.features.books` |
| Classes | PascalCase | `BookViewModel`, `BookRepository` |
| Functions | camelCase | `getBooks()`, `fetchFromApi()` |
| Constants | UPPER_SNAKE_CASE | `MAX_PAGE_SIZE`, `DEFAULT_TIMEOUT` |
| Variables | camelCase | `bookList`, `isLoading` |
| XML Resources | snake_case | `activity_main.xml`, `string_app_name` |
| Database Tables | lowercase_with_underscore | `favorite_books`, `book_reviews` |

## Composable Development

### Basic Composable Pattern

```kotlin
@Composable
fun FeatureScreen(
    modifier: Modifier = Modifier,
    viewModel: FeatureViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    when (uiState) {
        is UiState.Loading -> LoadingIndicator()
        is UiState.Success -> SuccessContent(data = (uiState as UiState.Success).data)
        is UiState.Error -> ErrorContent(message = (uiState as UiState.Error).message)
    }
}
```

### State Management in Composables

```kotlin
@Composable
fun MyComponent() {
    var count by remember { mutableStateOf(0) }
    
    Button(onClick = { count++ }) {
        Text("Count: $count")
    }
}
```

### ViewModel Integration

```kotlin
class MyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    
    fun loadData() {
        viewModelScope.launch {
            try {
                val data = repository.getData()
                _uiState.value = UiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
```

## Testing

### Unit Tests

Location: `commonTest/kotlin/`

```kotlin
class BookRepositoryTest {
    @Test
    fun testGetBooks() = runTest {
        val mockApiService = mockk<BookApiService>()
        coEvery { mockApiService.getBooks() } returns listOf(
            BookDto(id = 1, title = "Kotlin Programming")
        )
        
        val repository = BookRepositoryImpl(mockApiService)
        val result = repository.getBooks()
        
        assertEquals(1, result.size)
        assertEquals("Kotlin Programming", result[0].title)
    }
}
```

### Running Tests

```bash
# All tests
./gradlew test

# Specific test class
./gradlew test --tests BookRepositoryTest

# With coverage
./gradlew testDebugUnitTest --coverage
```

## Dependency Injection with Koin

### Module Definition

```kotlin
object FeatureModule {
    fun module() = module {
        // Repositories
        single<BookRepository> {
            BookRepositoryImpl(
                apiService = get(),
                database = get()
            )
        }
        
        // ViewModels
        viewModel {
            BookViewModel(repository = get())
        }
    }
}
```

### Module Registration

In your main app/initialization code:

```kotlin
startKoin {
    modules(
        featureModule,
        networkModule,
        databaseModule
    )
}
```

### Using in Composables

```kotlin
@Composable
fun MyScreen() {
    val viewModel: MyViewModel = koinViewModel()
    // Use viewModel...
}
```

## Database Operations

### Room Entity

```kotlin
@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val author: String,
    @ColumnInfo(name = "created_at")
    val createdAt: LocalDateTime
)
```

### Database Access

```kotlin
@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<BookEntity>
    
    @Insert
    suspend fun insertBook(book: BookEntity)
    
    @Update
    suspend fun updateBook(book: BookEntity)
    
    @Delete
    suspend fun deleteBook(book: BookEntity)
}
```

## Network Requests

### API Service

```kotlin
interface BookApiService {
    @GET("/api/books")
    suspend fun getBooks(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): List<BookDto>
}
```

### Repository Implementation

```kotlin
class BookRepositoryImpl(
    private val apiService: BookApiService,
    private val database: AppDatabase
) : BookRepository {
    override suspend fun getBooks(): List<Book> {
        return try {
            val dtos = apiService.getBooks()
            val entities = dtos.map { it.toEntity() }
            database.bookDao().insertBooks(entities)
            entities.map { it.toDomain() }
        } catch (e: Exception) {
            database.bookDao().getAllBooks().map { it.toDomain() }
        }
    }
}
```

## Platform-Specific Code

### Expect/Actual Pattern

**Common:**
```kotlin
// commonMain/kotlin/.../MapView.kt
@Composable
expect fun MapView(modifier: Modifier)
```

**Android:**
```kotlin
// androidMain/kotlin/.../MapView.kt
@Composable
actual fun MapView(modifier: Modifier) {
    GoogleMapView(modifier = modifier)
}
```

**iOS:**
```kotlin
// iosMain/kotlin/.../MapView.kt
@Composable
actual fun MapView(modifier: Modifier) {
    AppleMapView(modifier = modifier)
}
```

## Debugging

### Logging

```kotlin
// Use expect/actual for platform-specific logging
expect fun log(message: String, throwable: Throwable? = null)

// Implementation
import android.util.Log as AndroidLog
actual fun log(message: String, throwable: Throwable?) {
    AndroidLog.d("AppDebug", message, throwable)
}
```

### Debugging on Android

```bash
# Connect debugger
./gradlew run --debug

# View logs
adb logcat
```

### Debugging on iOS

- Use Xcode's debugger: Product → Run (with breakpoints)
- Or: Product → Scheme → Edit Scheme → Run → Diagnostics

## Performance Tips

1. **Avoid Recomposition**
   ```kotlin
   // Bad: will recompose on every parent recomposition
   val viewModel = myViewModel()
   
   // Good: cached
   val viewModel = koinViewModel()
   ```

2. **Use `key()` for Lists**
   ```kotlin
   LazyColumn {
       items(books, key = { it.id }) { book ->
           BookItem(book)
       }
   }
   ```

3. **Lazy Initialization**
   ```kotlin
   val expensiveObject by lazy { ExpensiveClass() }
   ```

## IDE Tips

### Android Studio Shortcuts
- `Cmd + K` - Commit changes
- `Cmd + Shift + K` - Delete line
- `Cmd + Opt + L` - Format code
- `Cmd + /` - Comment/uncomment
- `Cmd + N` - New file/class
- `Cmd + O` - Go to class
- `Cmd + U` - Go to super
- `Cmd + B` - Go to definition

### Useful Plugins
- Kotlin
- Gradle
- Git Toolbox
- Tabnine (AI autocomplete)
- Rainbow Brackets

## Git Workflow

### Branch Naming
```
feature/feature-name
bugfix/bug-description
hotfix/issue-description
refactor/refactoring-description
```

### Commit Message Format
```
[TYPE] Brief description

Detailed explanation if needed.

- Bullet point 1
- Bullet point 2
```

**Types:**
- `[FEATURE]` - New feature
- `[BUGFIX]` - Bug fix
- `[REFACTOR]` - Code refactoring
- `[DOCS]` - Documentation
- `[TEST]` - Test additions/fixes
- `[CHORE]` - Build, CI/CD, dependencies

### Example Workflow
```bash
# Create feature branch
git checkout -b feature/new-book-details

# Make changes and commit
git add .
git commit -m "[FEATURE] Add book details page"

# Push to remote
git push origin feature/new-book-details

# Create Pull Request
# Request review, make changes if needed
# Merge to main
```

## Next Steps

- Review [CONTRIBUTING.md](./CONTRIBUTING.md) for contribution guidelines
- Check [TROUBLESHOOTING.md](./TROUBLESHOOTING.md) for common issues
- Explore the [ARCHITECTURE.md](./ARCHITECTURE.md) for system design

