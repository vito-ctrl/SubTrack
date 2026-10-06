# 📝 CLI Todo App

A simple command-line to-do list application written in **Java**. Tasks are saved in a plain text file, so they are still there the next time you run the app.

This project was built as part of my journey **learning Java**: object-oriented design, packages, file I/O and user input handling.

![Java](https://img.shields.io/badge/Java-21-orange)
![Status](https://img.shields.io/badge/status-done-green)
![Type](https://img.shields.io/badge/type-CLI-lightgrey)

---

## 📑 Table of Contents

- [Features](#-features)
- [Demo](#-demo)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [Usage](#-usage)
- [Data Format](#-data-format)
- [What I Learned](#-what-i-learned)
- [Roadmap](#-roadmap)
- [Known Limitations](#-known-limitations)
- [Author](#-author)

---

## ✨ Features

**Available now**
- ➕ Add a new task with a title
- 📋 List all saved tasks
- 💾 Persistent storage in a text file
- 🕒 Automatic creation date for every task
- 🔢 Auto-incremented task ID

**Planned** (already in the menu, not implemented yet)
- 🗑️ Delete a task
- ✅ Mark a task as completed

---

## 🎬 Demo

```text
welcom to the To do App CLI
1 . add task 
2 . list tasks 
3 . delete task 
4 . mark completed 
:: 1
add ur task ;) 
task title : 
Buy groceries
```

```text
:: 2
----------- task list -----------
2   task 1   false   2026-05-24
3   task 2   false   2026-05-24
4   Buy groceries   false   2026-05-25
```

---

## 📂 Project Structure

```
CLI-Todo-App/
└── src/
    ├── Main.java                  # Entry point
    ├── ui/
    │   └── Menu.java              # Displays the menu and reads the user's choice
    ├── services/
    │   ├── TaskService.java       # Business logic (add and list tasks)
    │   └── FileService.java       # Reads and writes tasks.txt
    ├── models/
    │   └── Task.java              # Task model (title, isComplete, createdAt)
    ├── utils/
    │   └── InputHelper.java       # Input helpers (empty for now)
    └── data/
        └── tasks.txt              # Saved tasks
```

The code is split into layers, each with one job:

| Layer | Class | Responsibility |
|-------|-------|----------------|
| UI | `Menu` | Shows the menu, reads the choice, calls the service |
| Service | `TaskService` | Handles what happens when you add or list tasks |
| Service | `FileService` | Saves and loads data from the file |
| Model | `Task` | Represents one task |

---

## 🚀 Getting Started

### Prerequisites

- [JDK 21](https://adoptium.net/) or newer (check with `java -version`)
- Git

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/vito-ctrl/CLI-Todo-App.git
cd CLI-Todo-App

# 2. Compile the project
mkdir out
javac -d out $(find src -name "*.java")

# 3. Run it
java -cp out Main
```

> ⚠️ **Before running:** the path to `tasks.txt` is currently hardcoded in `FileService.getFilePath()`. Change it to the location of `src/data/tasks.txt` on your machine, or the app won't find the file. See [Known Limitations](#-known-limitations).

You can also open the project in **IntelliJ IDEA** and run `Main.java` directly.

---

## 💻 Usage

When the app starts, it shows a menu. Type the number of the action you want and press **Enter**.

| Option | Action | Status |
|--------|--------|--------|
| `1` | Add a task | ✅ Working |
| `2` | List tasks | ✅ Working |
| `3` | Delete a task | 🚧 Coming soon |
| `4` | Mark a task as completed | 🚧 Coming soon |

---

## 🗃 Data Format

Tasks are stored in `src/data/tasks.txt`, one per line, with fields separated by three spaces:

```
<id>   <title>   <isComplete>   <createdAt>
```

Example:

```
2   task 1   false   2026-05-24
```

---

## 📚 What I Learned

- Organizing a Java project into **packages** (`ui`, `services`, `models`, `utils`)
- **Object-oriented programming**: classes, encapsulation (private fields, getters and setters)
- Reading user input with `Scanner`
- File handling with `java.nio.file` (`Files`, `Path`, `Paths`, `StandardOpenOption`)
- Working with dates using `LocalDate`
- Handling exceptions with `try / catch` and try-with-resources

---

## 🗺 Roadmap

- [ ] Implement **delete task**
- [ ] Implement **mark as completed**
- [ ] Keep the menu running in a loop until the user chooses to quit
- [ ] Add an **Exit** option
- [ ] Validate user input (handle letters instead of numbers, empty titles)
- [ ] Use a relative path for `tasks.txt` so it works on any machine
- [ ] Fix the ID generation so it works for IDs with more than one digit
- [ ] Implement `InputHelper` to centralize input handling
- [ ] Show completed and pending tasks differently
- [ ] Add edit task and search features
- [ ] Add unit tests (JUnit)

---

## ⚠️ Known Limitations

This is a learning project, so some things are still rough:

- The path to `tasks.txt` is hardcoded in `FileService.getFilePath()`.
- The app runs one action and exits. It does not loop back to the menu.
- Task IDs are computed from the first character of the last line, so IDs of 10 or more will not work correctly.
- Options 3 and 4 appear in the menu but are not implemented yet.
- There is no input validation yet.

---

## 👤 Author

**Aymane El Khadraoui**

- GitHub: [@vito-ctrl](https://github.com/vito-ctrl)
- Email: aymane.elkhadraoui1@gmail.com

---

⭐ If you found this project helpful or interesting, feel free to give it a star!
