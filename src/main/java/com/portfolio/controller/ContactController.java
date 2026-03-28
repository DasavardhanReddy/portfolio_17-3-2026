package com.portfolio.controller;

import com.portfolio.model.ContactMessage;
import com.portfolio.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/test")
    public String test() {
        return "Contact API is working! Ready to receive POST requests at /api/contact/send";
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody ContactMessage message) {
        try {
            // Log incoming data for debugging
            System.out.println("Received message request: " + message);

            if (message == null) {
                return ResponseEntity.badRequest().body("Error: Request body is empty");
            }

            ContactMessage savedMessage = contactRepository.save(message);
            System.out.println("Message saved successfully: " + savedMessage.getId());

            return ResponseEntity.ok(savedMessage);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.internalServerError().body("Error saving message: " + e.getMessage());
        }
    }
}