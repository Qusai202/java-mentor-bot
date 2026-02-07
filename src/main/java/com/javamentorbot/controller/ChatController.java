package com.javamentorbot.controller;

import com.javamentorbot.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*") // يسمح للمتصفح بالاتصال بالسيرفر
public class ChatController {

    @Autowired
    private MentorService mentorService; // نستخدم المنطق القديم مباشرة

    // هذا الرابط هو ما سيتصل به الواجهة الأمامية
    @PostMapping("/api/chat")
    public Map<String, String> handleChat(@RequestBody Map<String, String> payload) {
        
        String userMessage = payload.get("message");
        
        // استدعاء المنطق الأساسي للحصول على الإجابة
        String botResponse = mentorService.getAnswer(userMessage);
        
        // إرجاع الإجابة بصيغة JSON
        return Map.of("reply", botResponse);
    }
}