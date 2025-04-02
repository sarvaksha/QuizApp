# 📚 QuizApp

A **Java-based Console Quiz Application** that connects to a MySQL database, presents random quiz questions, evaluates responses, and maintains a leaderboard.

## 🚀 Features
- Connects to a **MySQL database** to fetch and store quiz data.
- Randomly selects **5 questions** from the database.
- Uses **color-coded console output** for better user experience.
- Stores user scores in the **leaderboard**.
- Displays a **ranked leaderboard** at the end.

## 📂 Project Structure
```
QuizApp
│── src
│   ├── com.quizapp.db        --> Database connection (DBConnection.java)
│   ├── com.quizapp.logic     --> Quiz logic (Quiz.java)
│   ├── com.quizapp.main      --> Entry point (Main.java)
│── Referenced Libraries
│   ├── mysql-connector-j-9.2.0.jar
```

## 🔧 Setup & Installation
### Prerequisites
- **Java (JDK 8+)**
- **MySQL (installed & running)**
- **MySQL Connector for Java** (Already included in `Referenced Libraries`)

### Steps
1️⃣ Clone the repository:
```sh
git clone https://github.com/sarvaksha/QuizApp.git
cd QuizApp
```
2️⃣ Create the MySQL Database:
```sql
CREATE DATABASE QuizApp;
USE QuizApp;
```
3️⃣ Create `questions` table:
```sql
CREATE TABLE questions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    question TEXT NOT NULL,
    option1 VARCHAR(255) NOT NULL,
    option2 VARCHAR(255) NOT NULL,
    option3 VARCHAR(255) NOT NULL,
    option4 VARCHAR(255) NOT NULL,
    answer VARCHAR(255) NOT NULL
);
```
4️⃣ Insert Sample Questions:
```sql
INSERT INTO questions (question, option1, option2, option3, option4, answer) VALUES
("What is the capital of France?", "Paris", "Berlin", "Madrid", "Rome", "Paris"),
("Which data type is used to store integers in Java?", "String", "int", "float", "boolean", "int");
```
5️⃣ Create `leaderboard` table:
```sql
CREATE TABLE leaderboard (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    score INT NOT NULL
);
```
6️⃣ Open the project in **Eclipse** or **IntelliJ IDEA**.
7️⃣ Run `Main.java` and start playing!

## 🎮 How to Play
1. Run the application.
2. Enter your **username**.
3. Answer **5 random quiz questions**.
4. Get **instant feedback** on correct/wrong answers.
5. See your **final score** and compare it with the leaderboard.

## ⚙️ Technologies Used
- **Java** (JDK 8+)
- **MySQL** (Database for questions & leaderboard)
- **JDBC** (Java Database Connectivity)

## 💡 Future Enhancements
✔ GUI-based Quiz using **JavaFX**
✔ Multiplayer mode
✔ Difficulty levels
✔ More question categories

## 🤝 Contributing
Contributions are welcome! Feel free to **fork** the repo and submit a **pull request**.

## 👨‍💻 Author
**Sarvaksha** - [GitHub Profile](https://github.com/sarvaksha)

