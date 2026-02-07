package com.javamentorbot.model;

/**
 * Represents a single message in the chat conversation.
 */
public class ChatMessage {
    private final String role; // e.g., "user", "assistant", "system"
    private final String content;

    public ChatMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "role='" + role + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}