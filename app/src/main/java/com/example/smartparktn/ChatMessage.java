package com.example.smartparktn;

public class ChatMessage {
    private String text;
    private boolean received;

    public ChatMessage(String text, boolean received) {
        this.text = text;
        this.received = received;
    }

    public String getText() {
        return text;
    }

    public boolean isReceived() {
        return received;
    }
}

