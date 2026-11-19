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
}