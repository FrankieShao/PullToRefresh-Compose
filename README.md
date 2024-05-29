
# PullToRefresh Library for Jetpack Compose

[![Maven Central](https://img.shields.io/maven-central/v/com.yourcompany/pulltorefresh.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.yourcompany%22%20AND%20a:%22pulltorefresh%22)
[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

A simple and customizable "Pull to Refresh" library for Jetpack Compose, supporting custom loading indicators.

## Features

- Easy to integrate "Pull to Refresh" functionality.
- Support for custom loading indicators.
- Lightweight and highly customizable.
- Compatible with `LazyColumn`, `LazyRow`, and other composable lists.

## Installation

Add the following dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.yourcompany:pulltorefresh:1.0.0")
}
```
## Usage
### Basic Setup
Wrap your content composable (e.g., `LazyColumn, LazyRow`, etc.) with PullToRefresh in your Composable function:

    PullToRefresh(
        isRefreshing = isRefreshing,
        onRefresh = { loadData() }
    ) {
        LazyColumn {
            items(100) { index ->
                Text("Item #$index")
            }
        }
    }

#### Default Indicator:
<img src="https://github.com/MambaSJY/RefreshLayout/blob/main/default_indicator.gif" alt="Default Indicator" width="220"/>

### Custom Loading Indicator
To use a custom loading indicator, simply provide a composable function for the `indicator` parameter:


    PullToRefresh(
        isRefreshing = isRefreshing,
        onRefresh = { loadData() },
        loadingIndicator = { CustomLoadingIndicator() }
    ) {}

    @Composable
    fun CustomIndicator(
        progress: Float,
        isRefreshing: Boolean,
        modifier: Modifier = Modifier
    ) {
        val rowHeight = if (isRefreshing) { 60.dp } else { (progress * 60).roundToInt().dp }
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
               .fillMaxWidth()
               .height(rowHeight)
               .padding(top = 16.dp)
        ) {
            AnimationLoader(
               modifier = Modifier.height(50.dp).width(50.dp),
               isPlaying = isRefreshing,
               resId = R.raw.lottie_anim
            )
        }
    }

#### Custom Indicator:
<img src="https://github.com/MambaSJY/RefreshLayout/blob/main/custome_indicator.gif" alt="Default Indicator" width="220"/>


## License
This library is licensed under the Apache License 2.0. See the LICENSE file for more details.

For any queries or support, please open an issue on the GitHub repository.