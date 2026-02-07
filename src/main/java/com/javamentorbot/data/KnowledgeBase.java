package com.javamentorbot.data;

import java.util.HashMap;
import java.util.Map;

/**
 * A local static knowledge base for simple Java syntax questions.
 */
public class KnowledgeBase {
    private final Map<String, String> facts;

    public KnowledgeBase() {
        this.facts = new HashMap<>();
        initializeFacts();
    }

    private void initializeFacts() {
        facts.put("main", "The 'public static void main(String[] args)' method is the entry point where a Java program starts execution.");
        facts.put("system.out.println", "System.out.println() is a command to print text to the console.");
        facts.put("int", "'int' is a primitive data type in Java used to store integer values (whole numbers) without decimals.");
        facts.put("string", "'String' is a class in Java used to represent sequences of characters (text).");
        facts.put("class", "A 'Class' in Java is a blueprint from which individual objects are created.");
    }

    /**
     * Searches the local knowledge base for an answer.
     * Returns null if no answer is found locally.
     */
    public String search(String query) {
        // Simple keyword matching (case-insensitive)
        for (String key : facts.keySet()) {
            if (query.toLowerCase().contains(key)) {
                return facts.get(key);
            }
        }
        return null;
    }
}