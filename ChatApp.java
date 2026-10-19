package com.chatapp;

import java.util.Scanner;

/**
 * ChatApp - Console application entry point.
 * Handles user registration and login.
 */
public class ChatApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("========================================");
        System.out.println("     WELCOME TO THE CHAT APP");
        System.out.println("========================================");

        // ==================== REGISTRATION ====================
        System.out.println("\n--- REGISTRATION ---");

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter a username (must contain '_' and be <=5 chars): ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter a password (>=8 chars, 1 capital, 1 number, 1 special): ");
        String password = scanner.nextLine().trim();

        System.out.print("Enter your SA cell number (e.g., +27838968976): ");
        String cellPhone = scanner.nextLine().trim();

        String registrationResult = login.registerUser(
                firstName, lastName, username, password, cellPhone);

        System.out.println("\n--- REGISTRATION RESULT ---");
        System.out.println(registrationResult);

        boolean registrationSuccess = login.getStoredUsername() != null
                && login.getStoredUsername().equals(username)
                && login.getStoredPassword() != null
                && login.getStoredPassword().equals(password);

        if (!registrationSuccess) {
            System.out.println("\nRegistration failed. Please fix the errors and try again.");
            scanner.close();
            return;
        }

        System.out.println("\nRegistration successful!");

        // ==================== LOGIN ====================
        System.out.println("\n--- LOGIN ---");

        System.out.print("Enter your username: ");
        String loginUsername = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String loginPassword = scanner.nextLine().trim();

        String loginStatus = login.returnLoginStatus(loginUsername, loginPassword);
        System.out.println("\n--- LOGIN RESULT ---");
        System.out.println(loginStatus);

        scanner.close();
    }
}