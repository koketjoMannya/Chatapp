<<<<<<< HEAD
package com.chatapp;

import java.util.ArrayList;
import java.util.Scanner;

public class ChatApp {
    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<Message> disregardedMessages = new ArrayList<>();
    public static ArrayList<Message> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();

    public static void addMessage(Message msg) {
        messageIDs.add(msg.getMessageID());
        messageHashes.add(msg.getMessageHash());

        if (msg.getFlag().equalsIgnoreCase("Sent")) {
            sentMessages.add(msg);
        } else if (msg.getFlag().equalsIgnoreCase("Stored")) {
            storedMessages.add(msg);
        } else if (msg.getFlag().equalsIgnoreCase("Disregard")) {
            disregardedMessages.add(msg);
        }
    }

    public static void main(String[] args) {
        // 1. POPULATE TEST DATA (From the assignment images)
        addMessage(new Message(1, "+27834557896", "Did you get the cake?", "Sent"));
        addMessage(new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored"));
        addMessage(new Message(3, "+27834484567", "Yohoooo, I am at your gate.", "Disregard"));
        addMessage(new Message(4, "0838884567", "It is dinner time!", "Sent"));
        addMessage(new Message(5, "+27838884567", "Ok, I am leaving without you.", "Stored"));

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Chat App Menu ---");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages (Part 3 Features)");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter recipient (e.g., +2783...):");
                    String rec = scanner.nextLine();
                    System.out.println("Enter message:");
                    String txt = scanner.nextLine();
                    System.out.println("Enter flag (Sent/Stored/Disregard):");
                    String flag = scanner.nextLine();
                    int newNum = sentMessages.size() + storedMessages.size() + disregardedMessages.size() + 1;
                    addMessage(new Message(newNum, rec, txt, flag));
                    System.out.println("Message processed.");
                    break;
                case "2":
                    System.out.println("Coming Soon.");
                    break;
                case "3":
                    exit = true;
                    System.out.println("Goodbye.");
                    break;
                case "4":
                    storedMessagesMenu(scanner);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void storedMessagesMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Stored Messages Menu ---");
            System.out.println("a. Display sender and recipient of all stored messages");
            System.out.println("b. Display the longest stored message");
            System.out.println("c. Search for a message ID and display recipient and message");
            System.out.println("d. Search for all messages stored for a particular recipient");
            System.out.println("e. Delete a message using the message hash");
            System.out.println("f. Display a report of all stored messages");
            System.out.println("g. Back to Main Menu");
            System.out.print("Choice: ");
            
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "a":
                    for (Message m : storedMessages) {
                        System.out.println("Recipient: " + m.getRecipient() + " | Message: " + m.getMessageText());
                    }
                    break;
                case "b":
                    Message longest = null;
                    for (Message m : storedMessages) {
                        if (longest == null || m.getMessageText().length() > longest.getMessageText().length()) {
                            longest = m;
                        }
                    }
                    System.out.println(longest != null ? "Longest: " + longest.getMessageText() : "No stored messages.");
                    break;
                case "c":
                    System.out.print("Enter Message ID: ");
                    String searchID = scanner.nextLine();
                    boolean foundID = false;
                    for (Message m : storedMessages) {
                        if (m.getMessageID().equals(searchID)) {
                            System.out.println("Recipient: " + m.getRecipient() + "\nMessage: " + m.getMessageText());
                            foundID = true;
                        }
                    }
                    if (!foundID) System.out.println("Message ID not found.");
                    break;
                case "d":
                    System.out.print("Enter Recipient Number: ");
                    String searchRec = scanner.nextLine();
                    boolean foundRec = false;
                    for (Message m : storedMessages) {
                        if (m.getRecipient().equals(searchRec)) {
                            System.out.println(m.getMessageText());
                            foundRec = true;
                        }
                    }
                    if (!foundRec) System.out.println("No messages for this recipient.");
                    break;
                case "e":
                    System.out.print("Enter Message Hash to delete: ");
                    String hash = scanner.nextLine();
                    boolean deleted = storedMessages.removeIf(m -> m.getMessageHash().equals(hash));
                    if (deleted) {
                        System.out.println("Message successfully deleted.");
                    } else {
                        System.out.println("Hash not found.");
                    }
                    break;
                case "f":
                    for (Message m : storedMessages) {
                        System.out.println(m.toString());
                    }
                    break;
                case "g":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid.");
            }
        }
    }
=======
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
>>>>>>> 71497bf3b3dc637d47af196fa169514240cc9bb4
}