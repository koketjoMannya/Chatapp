package com.chatapp;

import java.util.regex.Pattern;

/**
 * Login class handles user registration and authentication.
 *
 * Reference for cell phone regex:
 * Oracle. (2023). Pattern (Java Platform SE 8).
 * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 *
 * Regex adapted from:
 * Stack Overflow. (2013). Regex for South African phone numbers.
 * https://stackoverflow.com/questions/123559/a-comprehensive-regex-for-phone-number-validation
 */
public class Login {

    private String storedUsername;
    private String storedPassword;
    private String storedFirstName;
    private String storedLastName;
    private String storedCellPhone;

    // ==================== USERNAME VALIDATION ====================

    public boolean checkUserName(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("_") && username.length() <= 5;
    }

    public String getUsernameMessage(String username) {
        if (checkUserName(username)) {
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted; please ensure that your "
                 + "username contains an underscore and is no more than five "
                 + "characters in length.";
        }
    }

    // ==================== PASSWORD VALIDATION ====================

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    public String getPasswordMessage(String password) {
        if (checkPasswordComplexity(password)) {
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted; please ensure that the "
                 + "password contains at least eight characters, a capital letter, "
                 + "a number, and a special character.";
        }
    }

    // ==================== CELL PHONE VALIDATION ====================

    public boolean checkCellPhoneNumber(String cellPhone) {
        if (cellPhone == null) {
            return false;
        }
        String regex = "^\\+27[0-9]{9}$";
        return Pattern.matches(regex, cellPhone);
    }

    public String getCellPhoneMessage(String cellPhone) {
        if (checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number successfully added.";
        } else {
            return "Cell phone number incorrectly formatted or does not contain "
                 + "international code.";
        }
    }

    // ==================== REGISTRATION ====================

    public String registerUser(String firstName, String lastName, String username,
                               String password, String cellPhone) {
        StringBuilder result = new StringBuilder();

        if (!checkUserName(username)) {
            return getUsernameMessage(username);
        }
        result.append(getUsernameMessage(username)).append("\n");

        if (!checkPasswordComplexity(password)) {
            return getPasswordMessage(password);
        }
        result.append(getPasswordMessage(password)).append("\n");

        if (!checkCellPhoneNumber(cellPhone)) {
            return getCellPhoneMessage(cellPhone);
        }
        result.append(getCellPhoneMessage(cellPhone));

        this.storedFirstName = firstName;
        this.storedLastName = lastName;
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellPhone = cellPhone;

        return result.toString();
    }

    // ==================== LOGIN ====================

    public boolean loginUser(String username, String password) {
        if (storedUsername == null || storedPassword == null) {
            return false;
        }
        return storedUsername.equals(username) && storedPassword.equals(password);
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + storedFirstName + ", " + storedLastName
                 + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // ==================== GETTERS ====================

    public String getStoredUsername() {
        return storedUsername;
    }

    public String getStoredPassword() {
        return storedPassword;
    }

    public String getStoredFirstName() {
        return storedFirstName;
    }

    public String getStoredLastName() {
        return storedLastName;
    }
}