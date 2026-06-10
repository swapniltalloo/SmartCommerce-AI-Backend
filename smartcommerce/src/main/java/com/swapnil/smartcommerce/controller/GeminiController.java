package com.swapnil.smartcommerce.controller;

import com.swapnil.smartcommerce.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class GeminiController {

    @Autowired
    private GeminiService geminiService;

    @GetMapping
    public String askAI(
            @RequestParam String prompt
    ) {

        return geminiService.askGemini(prompt);
    }

    @GetMapping("/recommend")
    public String recommendProducts() {

           return geminiService.recommendProducts();
    }
}