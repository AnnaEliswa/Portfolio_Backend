package com.portfolio.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.portfolio.backend.entity.Contact;
import com.portfolio.backend.service.ContactService;

@RestController
@RequestMapping("/api/contact") //Endpoint URL to match the React frontend
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public Contact submitContactForm(@RequestBody Contact contact) {
        return contactService.saveContact(contact); // Save the contact data to the database
    }
}
