package com.javamentorbot.ui;

import com.javamentorbot.service.MentorService;

import java.util.Scanner;

/**
 * The Console View handles user interaction.
 * It loops until the user types 'exit'.
 */
public class ConsoleView {

    private final MentorService mentorService;
    private final Scanner scanner;

    public ConsoleView(MentorService mentorService) {
        this.mentorService = mentorService;
        this.scanner = new Scanner(System.in);
    }

    public void startChat() {
        System.out.println("--------------------------------------------------");
        System.out.println("   Welcome to JavaMentorBot (AI + Local KB)       ");
        System.out.println("   Type 'exit' to quit the session.               ");
        System.out.println("--------------------------------------------------");

        while (true) {
            System.out.print("\nYou: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("JavaMentor: Good luck with your studies!");
                break;
            }

            // Delegate logic to Service
            String response = mentorService.getAnswer(input);
            System.out.println("JavaMentor: " + response);
        }
        
        scanner.close();
    }
}