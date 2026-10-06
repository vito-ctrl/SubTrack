# 💳 SubTrack

A console application written in **Java** to manage your subscriptions (Netflix, Spotify, gym, etc.), track their payments and generate simple financial reports.

SubTrack lets you create subscriptions with or without a commitment period, generate a monthly payment schedule, record payments, detect overdue ones, and see how much you have paid.

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Type](https://img.shields.io/badge/type-Console%20App-lightgrey)
![Status](https://img.shields.io/badge/status-done-green)
![License](https://img.shields.io/badge/license-MIT-green)

---

## 📑 Table of Contents

- [Features](#-features)
- [Project Structure](#-project-structure)
- [Design and Concepts](#-design-and-concepts)
- [Getting Started](#-getting-started)
- [Usage](#-usage)
- [Project Status](#-project-status)
- [Roadmap](#-roadmap)
- [What I Learned](#-what-i-learned)
- [Author](#-author)
- [License](#-license)

---

## ✨ Features

### Subscriptions
- Create a subscription (service name, monthly amount, start and end dates, status)
- Two types: **with commitment** (fixed duration in months) and **without commitment**
- Modify, delete and list subscriptions
- Statuses: `Active`, `Suspended`, `Terminated`
- Automatic **payment schedule** generation (one payment per month between start and end dates)

### Payments
- Record a payment for a subscription (paid now or unpaid)
- Modify and delete payments
- Show all payments of a subscription
- Show unpaid payments and the **total still owed** for committed subscriptions
- Show the **total paid** for a subscription
- Automatic detection of **overdue** payments
- Show the last 5 payments

### Reports
- Monthly report (total per month)
- Annual report (total per year)
- Unpaid payments report (count per subscription)

### Input validation
- Dates must use the `dd/MM/yyyy` format
- Numbers and enum values (status) are validated and re-asked until valid

---

## 📂 Project Structure

```
SubTrack/
├── LICENSE
├── README.md
└── src/
    ├── main/
    │   └── Main.java                          # Entry point
    ├── ui/
    │   └── Menu.java                          # Console menu and user interaction
    ├── model/
    │   ├── Subscription.java                  # Abstract base class
    │   ├── SubscriptionWithCommitment.java    # Subscription with a duration in months
    │   ├── SubscriptionWithoutCommitment.java # Subscription without commitment
    │   └── Payment.java                       # Payment (due date, paid date, status)
    ├── services/
    │   ├── SubscriptionService.java           # Subscription business logic
    │   └── PaymentService.java                # Payment logic, totals and reports
    ├── DAO/
    │   ├── SubscriptionDAO.java               # In-memory storage for subscriptions
    │   └── PaymentDAO.java                    # In-memory storage for payments
    └── util/
        ├── ValidateInput.java                 # Safe reading of dates, numbers and enums
        └── DateUtils.java                     # Date formatting helper (to be finished)
```

---

## 🧠 Design and Concepts

The project uses a layered architecture:

| Layer | Classes | Role |
|-------|---------|------|
| **UI** | `Menu` | Displays menus and reads user input |
| **Service** | `SubscriptionService`, `PaymentService` | Business rules, totals, reports |
| **DAO** | `SubscriptionDAO`, `PaymentDAO` | Data access (currently in memory with `HashMap`) |
| **Model** | `Subscription`, `Payment`, ... | The data objects |
| **Util** | `ValidateInput`, `DateUtils` | Reusable helpers |

Java concepts used:
- **Inheritance and abstraction:** `Subscription` is abstract, with two subclasses
- **Encapsulation:** private fields with getters and setters
- **Enums:** `Subscription.Status` and `Payment.Status`
- **Collections:** `HashMap`, `List`, `Optional`
- **Streams and lambdas:** filtering, sorting and grouping (`Collectors.groupingBy`, `summingDouble`)
- **Generics:** `readEnum` in `ValidateInput`
- **Java Time API:** `LocalDate`
- **UUIDs** for unique IDs

---

## 🚀 Getting Started

### Prerequisites

- [JDK 17](https://adoptium.net/) or newer (check with `java -version`)
- Git

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/vito-ctrl/SubTrack.git
cd SubTrack

# 2. Compile
mkdir out
javac -d out $(find src -name "*.java")

# 3. Run
java -cp out Main
```

You can also open the project in **IntelliJ IDEA** or **VS Code** and run `src/main/Main.java`.

---

## 💻 Usage

When the app starts, it shows this menu:

```text
======================================
       SUBSCRIPTION MANAGEMENT
======================================

--- SUBSCRIPTIONS ---
1. Create subscription
2. Modify subscription
3. Delete subscription
4. List subscriptions

--- PAYMENTS ---
5. Show subscription payments
6. Record payment
7. Modify payment
8. Delete payment
9. Show unpaid payments
10. Show total paid

--- REPORTS ---
11. Show last 5 payments
12. Financial reports

0. Exit
```

**Typical workflow**
1. Choose `1` to create a subscription. At the end, answer `y` to generate its payment schedule.
2. Choose `4` to list subscriptions and copy the subscription ID.
3. Choose `5` to see its payments, `6` to record a new one, or `7` to mark one as paid.
4. Choose `9` to check unpaid payments, or `12` for financial reports.

---

## 🚧 Project Status

SubTrack is **work in progress**. A few things are not finished yet:

- `SubscriptionService.java` is mid-edit: it needs a `PaymentDAO`, the missing imports, and the unfinished `generateSchedule(String id)` method removed.
- `SubscriptionWithCommitment` and `SubscriptionWithoutCommitment` need `import java.time.LocalDate;`.
- `Payment.Status` is `private`, but other classes use it, so it must be made `public`.
- The `DAO` folder is named in uppercase while the package is `dao`. Rename the folder to `dao` for consistency.
- `DateUtils.java` is fully commented out.
- Data is stored **in memory only**, so everything is lost when the app closes.

---

## 🗺 Roadmap

- [ ] Fix the compile issues listed above
- [ ] Save data to files (JSON or CSV) or a database (SQLite / MySQL via JDBC)
- [ ] Replace `scanner.nextInt()` in the menus with `ValidateInput.readInt` so letters don't crash the app
- [ ] Set a default status when creating a `Payment`
- [ ] Rename `findByAbonnement` methods to `findBySubscription` for consistent English naming
- [ ] Use each payment's own amount in reports instead of the current monthly amount
- [ ] Finish `DateUtils` and display dates in a nice format
- [ ] Add a `terminate subscription` option to the menu
- [ ] Add unit tests with JUnit
- [ ] Export reports to CSV or PDF

---

## 📚 What I Learned

- Structuring a Java application in layers (UI, services, DAO, models)
- Using inheritance, abstract classes and enums
- Manipulating collections with the Streams API
- Validating user input with loops and exceptions
- Working with dates using `java.time`

---

## 👤 Author

**Aymane El Khadraoui**

- GitHub: [@vito-ctrl](https://github.com/vito-ctrl)
- Email: aymane.elkhadraoui1@gmail.com

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
