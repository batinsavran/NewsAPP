# NewsApp

NewsApp is a news application developed using Kotlin in Android Studio. This application utilizes an API to display the latest news to users and allows them to filter news by category.

## Features

- Displaying the latest news
- Filtering news by categories (e.g., sports, technology, health)
- Viewing news details
- Adding to favorites
- Searching among news

## Technologies Used

- Kotlin
- Android Studio
- Retrofit (for API requests)
- RecyclerView (for displaying lists)
- Glide (for loading images)

## Installation

1. Clone or download this project.
2. Open Android Studio.
3. Select "Open an existing project" from the File menu, and choose the downloaded project folder.
4. Build and run the project.

## Usage

Upon opening the application, the main screen displays the latest news. You can filter news by category by clicking on the categories in the top menu. Clicking on a news item opens a screen displaying the details of the news. Additionally, you can search among news and add them to favorites.

## Contributing

Contributions are welcome! If you find any bugs or have suggestions for new features, please open an issue or submit a pull request.

## API Usage

This application uses the NewsAPI, a free news API. To use the API, you need to obtain your API key and add it to the `API_KEY` variable in the `ApiService.kt` file.

```kotlin
const val API_KEY = "YOUR_API_KEY" 
```

