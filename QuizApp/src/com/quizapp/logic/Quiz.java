package com.quizapp.logic; 

import java.sql.*;
import java.util.Scanner;
import com.quizapp.db.DBConnection;

public class Quiz {

    // ANSI color codes for styling
    public static final String RESET = "\u001B[0m"; 
    public static final String CYAN = "\u001B[36m"; 
    public static final String GREEN = "\u001B[32m"; 
    public static final String RED = "\u001B[31m"; 
    public static final String YELLOW = "\u001B[33m"; 
    public static final String BLUE = "\u001B[34m"; 
    public static final String BOLD = "\u001B[1m"; 

    public void start() {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println(RED + "❌ Failed to connect to database." + RESET);
            return;
        }

        try (Statement stmt = conn.createStatement();
             Scanner scanner = new Scanner(System.in)) {

            // Display a welcome banner
            System.out.println(BLUE + "================================");
            System.out.println("🎯  WELCOME TO THE QUIZ APP  🎯");
            System.out.println("================================" + RESET);

            // Ask for the username at the beginning
            System.out.print(YELLOW + "🔹 Enter your username: " + RESET);
            String username = scanner.nextLine();

            ResultSet rs = stmt.executeQuery("SELECT * FROM questions ORDER BY RAND() LIMIT 5");
            int score = 0;

            while (rs.next()) {
                System.out.println(CYAN + "\n📌 " + rs.getString("question") + RESET);
                System.out.println(BOLD + "A." + RESET + " " + rs.getString("option1"));
                System.out.println(BOLD + "B." + RESET + " " + rs.getString("option2"));
                System.out.println(BOLD + "C." + RESET + " " + rs.getString("option3"));
                System.out.println(BOLD + "D." + RESET + " " + rs.getString("option4"));

                System.out.print(YELLOW + "\n💡 Your answer: " + RESET);
                String userInput = scanner.nextLine().toUpperCase();

                // Map user input to the actual answer
                String mappedAnswer = switch (userInput) {
                    case "A" -> rs.getString("option1");
                    case "B" -> rs.getString("option2");
                    case "C" -> rs.getString("option3");
                    case "D" -> rs.getString("option4");
                    default -> null;
                };

                if (mappedAnswer == null) {
                    System.out.println(RED + "⚠️ Invalid option. Skipping..." + RESET);
                    continue;
                }

                String correctAnswer = rs.getString("answer");

                if (mappedAnswer.equalsIgnoreCase(correctAnswer)) {
                    System.out.println(GREEN + "✅ Correct!" + RESET);
                    score++;
                } else {
                    System.out.println(RED + "❌ Wrong! Correct answer: " + correctAnswer + RESET);
                }
            }

            System.out.println(BLUE + "\n🎯 Your final score: " + BOLD + score + RESET);

            // Securely insert username & score using PreparedStatement
            String insertSQL = "INSERT INTO leaderboard (username, score) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, username);
                pstmt.setInt(2, score);
                pstmt.executeUpdate();
                System.out.println(GREEN + "🏆 Score saved to leaderboard!" + RESET);
            }

            // Display leaderboard
            displayLeaderboard(stmt);
        } catch (SQLException e) {
            System.err.println(RED + "❌ Database error: " + e.getMessage() + RESET);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(RED + "⚠️ Error closing connection: " + e.getMessage() + RESET);
            }
        }
    }

    // Display the leaderboard with improved formatting
    public void displayLeaderboard(Statement stmt) {
        System.out.println(BLUE + "\n🏆 --- LEADERBOARD --- 🏆" + RESET);
        try (ResultSet rs = stmt.executeQuery(
                "SELECT username, MAX(score) AS highest_score FROM leaderboard GROUP BY username ORDER BY highest_score DESC LIMIT 10")) {
            int rank = 1;
            while (rs.next()) {
                System.out.printf("%s#%d  %s - %d points%s%n", BOLD, rank++, rs.getString("username"), rs.getInt("highest_score"), RESET);
            }
        } catch (SQLException e) {
            System.err.println(RED + "⚠️ Error fetching leaderboard: " + e.getMessage() + RESET);
        }
    }
} 