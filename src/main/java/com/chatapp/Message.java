package com.chatapp;

import java.util.Random;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String flag; 
    private String messageHash;

    public Message(int messageNumber, String recipient, String messageText, String flag) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.flag = flag;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }

    private String generateID() {
        Random rand = new Random();
        long id = (long) (rand.nextDouble() * 10000000000L);
        return String.format("%010d", id);
    }

    public String createMessageHash() {
        String firstTwoID = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        
        if (firstWord.isEmpty()) firstWord = "XX";
        if (lastWord.isEmpty()) lastWord = "XX";

        return (firstTwoID + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getFlag() { return flag; }

    @Override
    public String toString() {
        return "Message ID: " + messageID + "\n" +
               "Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText + "\n" +
               "Flag: " + flag + "\n";
    }
}