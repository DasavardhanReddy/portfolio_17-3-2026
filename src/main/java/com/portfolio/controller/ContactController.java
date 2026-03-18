package com.portfolio.controller;

import com.portfolio.model.ContactMessage;
import com.portfolio.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody @NonNull ContactMessage message) {
        try {
            // 🔍 DEBUG: check incoming data
            System.out.println("Received: " + message);

            ContactMessage savedMessage = contactRepository.save(message);

            // 🔍 DEBUG: check saved data
            System.out.println("Saved: " + savedMessage);

            return ResponseEntity.ok(savedMessage);
        } catch (Exception e) {
            e.printStackTrace(); // 🔍 show full error
            return ResponseEntity.badRequest().body("Error saving message: " + e.getMessage());
        }
    }
}