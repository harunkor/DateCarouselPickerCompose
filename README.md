# DateCarouselPickerCompose

A lightweight, Material 3–friendly **horizontal date picker (carousel)** for Jetpack Compose.  
Smooth spring animations, optional haptic feedback, and accessible semantics built in. Fully customizable (colors, typography, sizes, shapes) with simple state handling via `rememberSaveable`.

> 📷 **Screenshot**  
> <img width="270" height="585" alt="Screenshot_20250917_165752" src="https://github.com/user-attachments/assets/303139ad-7bf2-409d-98b0-6e7eefd6b49e" />

> 🎬 **Demo / Video**  

https://github.com/user-attachments/assets/7bb2214c-a2fa-45ab-8cd4-fcf034bc628b


---

## ✨ Features
- **Composable API:** `DateCarouselPicker(items, onSelect)`
- **Customizable UI:** item width/height, spacing, colors, typography, `Shape`
- **Shapes:** `CustomShape`
- **Month label:** 3-letter month (`OCT` / `EKİ`) via `SimpleDateFormat("MMM", Locale.getDefault())` or localizable `string-array`
- **Animations & haptics:** spring color/elevation transitions + tactile feedback on select
- **Accessibility:** content descriptions for screen readers
- **State persistence:** `rememberSaveable` (index, `@Parcelize`, or custom `Saver`)
- **API 21+**, Compose BOM, Material 3

---

## 🚀 Installation (JitPack)

**settings.gradle (root)**
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```

**build.gradle.kts  (module)**
```kotlin
build.gradle.kts 
dependencies {
    implementation("com.github.harunkor:DateCarouselPickerCompose:v0.1.0") // ← use your latest tag
}
```

🎛️ Customization
```kotlin
DateCarouselPicker(
    items = items,
    selectedIndex = selectedIndex,        // or pass selectedItem
    onSelect = { item, index -> /* ... */ },

    itemWidth = 76.dp,
    itemHeight = 96.dp,
    spacing = 12.dp,

    selectedColor = Color(0xFFFF5A1F),
    unselectedFill = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f),
    unselectedContent = MaterialTheme.colorScheme.onSurfaceVariant,

    shape = RoundedCornerShape(42.dp),    // pass any Shape you like

    dayTextStyle = MaterialTheme.typography.headlineSmall.copy(
        fontSize = 24.sp, fontWeight = FontWeight.ExtraBold
    ),
    weekdayTextStyle = MaterialTheme.typography.labelMedium.copy(
        fontSize = 12.sp, fontWeight = FontWeight.Medium, letterSpacing = 0.1.sp
    )
)
```

⚙️ Requirements

minSdk 21
Compose BOM, Material 3
Kotlin/JVM target 11+
For Java 8+ time APIs on API < 26, enable core library desugaring:
```kotlin
android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
}
dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")
}

```

📄 License
MIT License

Copyright (c) 2025 Harun Kor

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.




