package com.javamentorbot.service;
import org.springframework.stereotype.Service;
import com.javamentorbot.data.KnowledgeBase;
import com.javamentorbot.integration.AiClient;

/**
 * The Service layer handles business logic.
 * It decides whether to answer locally or query the AI.
 */
@Service
public class MentorService {

    private final KnowledgeBase knowledgeBase;
    private final AiClient aiClient;

    public MentorService() {
        this.knowledgeBase = new KnowledgeBase();
        this.aiClient = new AiClient();
    }

    /**
     * Process the user query and return an appropriate answer.
     */
    public String getAnswer(String userQuery) {
        if (userQuery == null || userQuery.trim().isEmpty()) {
            return "Please ask a Java-related question.";
        }

        // 1. Check Local Knowledge Base first (Efficiency)
        String localAnswer = knowledgeBase.search(userQuery);
        if (localAnswer != null) {
            return "[Local Knowledge] " + localAnswer;
        }

        // 2. Fallback to AI API for complex questions
        // We add a small delay marker or text to indicate we are thinking
        return aiClient.getResponse(userQuery);
    }
}