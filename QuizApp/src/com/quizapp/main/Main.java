package com.quizapp.main;

import com.quizapp.logic.Quiz;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Quiz App...");
        Quiz quiz = new Quiz();
        quiz.start();
        System.out.println("Quiz Completed. Thanks for playing!");
    }
}