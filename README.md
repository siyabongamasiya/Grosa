# 🛒 Grosa – Grocery List App

Grosa is a simple and intuitive Android application that helps users create grocery lists and estimate how much they’ll spend on their next shopping trip. It’s designed to make budgeting and shopping more organized and stress-free.

---

## 📱 Features

- 🧾 **Create and manage grocery lists**  
  Add, edit, or remove items from your grocery list easily.

- 💰 **Automatic total cost calculation**  
  The app calculates the total price of all items in your list.

- 🧮 **Quantity and price input**  
  Specify the price and quantity for each item to get accurate totals.

- 🎨 **Modern UI built with Jetpack Compose**  
  Enjoy a clean, responsive, and user-friendly design.

- 💾 **Local data storage**  
  Your grocery lists are stored locally for offline access.

---

## ⚙️ Tech Stack

- **Language:** Kotlin  
- **Framework:** Jetpack Compose  
- **Architecture:** MVVM (Model-View-ViewModel)  
- **Storage:** Room Database
- **Build Tool:** Gradle  
- **IDE:** Android Studio  

---

## 📂 Project Structure
```bash
app/
├── java/com/example/grosa/
│ ├── App/Grosa.kt # Application class
│ ├── MainActivity.kt # Main activity
│ ├── ui/ # Jetpack Compose UI components
│ ├── data/ # Data models and database
│ └── viewmodel/ # ViewModels and logic
├── res/
│ ├── layout/ # Layouts and UI resources
│ ├── values/ # Strings, themes, and styles
│ └── mipmap/ # App icons
└── AndroidManifest.xml
```

## 🚀 Getting Started

### 1️ Clone the Repository
```bash
git clone https://github.com/siyabongamasiya/grosa.git
cd grosa
```

### 2 Open in Android Studio

Launch Android Studio

Select File → Open

Choose the cloned grosa folder

### 3 Run the App

Connect an Android device or start an emulator

Click Run ▶️ to build and launch the app

### How It Works

Open the app and create a new grocery list.

Add items with their quantities and prices.

The total amount updates automatically.

Save your list and reuse it for your next shopping trip.
