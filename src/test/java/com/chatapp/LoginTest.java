<<<<<<< HEAD
package com.chatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @BeforeEach
    public void setUp() {
        ChatApp.sentMessages.clear();
        ChatApp.storedMessages.clear();
        ChatApp.disregardedMessages.clear();
        ChatApp.messageHashes.clear();
        ChatApp.messageIDs.clear();
        
        ChatApp.addMessage(new Message(1, "+27834557896", "Did you get the cake?", "Sent"));
        ChatApp.addMessage(new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored"));
        ChatApp.addMessage(new Message(3, "+27834484567", "Yohoooo, I am at your gate.", "Disregard"));
        ChatApp.addMessage(new Message(4, "0838884567", "It is dinner time!", "Sent"));
        ChatApp.addMessage(new Message(5, "+27838884567", "Ok, I am leaving without you.", "Stored"));
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        assertEquals(2, ChatApp.sentMessages.size());
        assertEquals("Did you get the cake?", ChatApp.sentMessages.get(0).getMessageText());
        assertEquals("It is dinner time!", ChatApp.sentMessages.get(1).getMessageText());
    }

    @Test
    public void testDisplayLongestMessage() {
        String longest = "";
        for (Message m : ChatApp.storedMessages) {
            if (m.getMessageText().length() > longest.length()) {
                longest = m.getMessageText();
            }
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchForMessageID() {
        // We will test the logic directly to ensure it finds the message
        String targetID = ChatApp.sentMessages.get(1).getMessageID();
        String foundMessage = "";
        
        for (Message m : ChatApp.sentMessages) {
            if (m.getMessageID().equals(targetID)) {
                foundMessage = m.getMessageText();
            }
        }
        assertEquals("It is dinner time!", foundMessage);
    }

    @Test
    public void testSearchAllMessagesForRecipient() {
        ArrayList<String> messagesToRecipient = new ArrayList<>();
        for (Message m : ChatApp.storedMessages) {
            if (m.getRecipient().equals("+27838884567")) {
                messagesToRecipient.add(m.getMessageText());
            }
        }
        
        assertEquals(2, messagesToRecipient.size());
        assertTrue(messagesToRecipient.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(messagesToRecipient.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageUsingHash() {
        String hashToDelete = ChatApp.storedMessages.get(0).getMessageHash();
        int initialSize = ChatApp.storedMessages.size();
        
        ChatApp.storedMessages.removeIf(m -> m.getMessageHash().equals(hashToDelete));
        
        assertEquals(initialSize - 1, ChatApp.storedMessages.size());
    }

    @Test
    public void testDisplayReport() {
        Message m = ChatApp.storedMessages.get(0);
        String report = m.toString();
        
        assertTrue(report.contains("Message ID:"));
        assertTrue(report.contains("Recipient:"));
        assertTrue(report.contains("Message:"));
    }
=======
package com.chatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Login class using PoE test data.
 */
public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
    }

    // ==================== USERNAME TESTS ====================

    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
        assertEquals("Username successfully captured.",
                login.getUsernameMessage("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
        assertEquals("Username is not correctly formatted; please ensure that your "
                + "username contains an underscore and is no more than five "
                + "characters in length.",
                login.getUsernameMessage("kyle!!!!!!"));
    }

    @Test
    public void testUsernameNoUnderscore() {
        assertFalse(login.checkUserName("kyle1"));
    }

    @Test
    public void testUsernameTooLong() {
        assertFalse(login.checkUserName("kyl_123"));
    }

    @Test
    public void testUsernameNull() {
        assertFalse(login.checkUserName(null));
    }

    // ==================== PASSWORD TESTS ====================

    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99!"));
        assertEquals("Password successfully captured.",
                login.getPasswordMessage("Ch&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
        assertEquals("Password is not correctly formatted; please ensure that the "
                + "password contains at least eight characters, a capital letter, "
                + "a number, and a special character.",
                login.getPasswordMessage("password"));
    }

    @Test
    public void testPasswordTooShort() {
        assertFalse(login.checkPasswordComplexity("Ch&1!"));
    }

    @Test
    public void testPasswordNoCapital() {
        assertFalse(login.checkPasswordComplexity("ch&sec@ke99!"));
    }

    @Test
    public void testPasswordNoNumber() {
        assertFalse(login.checkPasswordComplexity("Ch&sec@ke!"));
    }

    @Test
    public void testPasswordNoSpecial() {
        assertFalse(login.checkPasswordComplexity("Chsecake99"));
    }

    @Test
    public void testPasswordNull() {
        assertFalse(login.checkPasswordComplexity(null));
    }

    // ==================== CELL PHONE TESTS ====================

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        assertEquals("Cell phone number successfully added.",
                login.getCellPhoneMessage("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
        assertEquals("Cell phone number incorrectly formatted or does not contain "
                + "international code.",
                login.getCellPhoneMessage("08966553"));
    }

    @Test
    public void testCellPhoneNoInternationalCode() {
        assertFalse(login.checkCellPhoneNumber("0838968976"));
    }

    @Test
    public void testCellPhoneTooShort() {
        assertFalse(login.checkCellPhoneNumber("+27838968"));
    }

    @Test
    public void testCellPhoneNull() {
        assertFalse(login.checkCellPhoneNumber(null));
    }

    // ==================== REGISTRATION TESTS ====================

    @Test
    public void testRegisterUserSuccess() {
        String result = login.registerUser("Kyle", "Smith", "kyl_1",
                "Ch&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Username successfully captured."));
        assertTrue(result.contains("Password successfully captured."));
        assertTrue(result.contains("Cell phone number successfully added."));
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        String result = login.registerUser("Kyle", "Smith", "kyle!!!!!!",
                "Ch&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Username is not correctly formatted"));
        assertNull(login.getStoredUsername());
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        String result = login.registerUser("Kyle", "Smith", "kyl_1",
                "password", "+27838968976");
        assertTrue(result.contains("Password is not correctly formatted"));
        assertNull(login.getStoredUsername());
    }

    @Test
    public void testRegisterUserInvalidCellPhone() {
        String result = login.registerUser("Kyle", "Smith", "kyl_1",
                "Ch&sec@ke99!", "08966553");
        assertTrue(result.contains("Cell phone number incorrectly formatted"));
        assertNull(login.getStoredUsername());
    }

    // ==================== LOGIN TESTS ====================

    @Test
    public void testLoginSuccessful() {
        login.registerUser("Kyle", "Smith", "kyl_1",
                "Ch&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        login.registerUser("Kyle", "Smith", "kyl_1",
                "Ch&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
        assertFalse(login.loginUser("wronguser", "Ch&sec@ke99!"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        login.registerUser("Kyle", "Smith", "kyl_1",
                "Ch&sec@ke99!", "+27838968976");
        String status = login.returnLoginStatus("kyl_1", "Ch&sec@ke99!");
        assertEquals("Welcome Kyle, Smith it is great to see you again.", status);
    }

    @Test
    public void testReturnLoginStatusFailure() {
        login.registerUser("Kyle", "Smith", "kyl_1",
                "Ch&sec@ke99!", "+27838968976");
        String status = login.returnLoginStatus("kyl_1", "wrong");
        assertEquals("Username or password incorrect, please try again.", status);
    }

    @Test
    public void testLoginWithoutRegistration() {
        assertFalse(login.loginUser("kyl_1", "Ch&sec@ke99!"));
    }
>>>>>>> 71497bf3b3dc637d47af196fa169514240cc9bb4
}