package com.example.morsetranslatorbackend.controller;

import com.example.morsetranslatorbackend.logic.MorseCodeLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // Marks this class as a REST controller
@RequestMapping("/api/morse") // Base path for all endpoints in this controller
@CrossOrigin(origins = "*") // Allows requests from any origin (for development, restrict in production)
public class MorseRestController {

    private final MorseCodeLogic morseCodeLogic;

    // Spring will automatically inject an instance of MorseCodeLogic
    @Autowired
    public MorseRestController(MorseCodeLogic morseCodeLogic) {
        this.morseCodeLogic = morseCodeLogic;
    }

    /**
     * Endpoint to translate text to Morse code.
     * Expects a plain text string in the request body.
     * @param text The text to translate.
     * @return The translated Morse code string.
     */
    @PostMapping("/text-to-morse")
    public String textToMorse(@RequestBody String text) {
        return morseCodeLogic.translateToMorse(text);
    }

    /**
     * Endpoint to translate Morse code to text.
     * Expects a Morse code string in the request body.
     * @param morseCode The Morse code to translate.
     * @return The translated plain text string.
     */
    @PostMapping("/morse-to-text")
    public String morseToText(@RequestBody String morseCode) {
        return morseCodeLogic.translateToText(morseCode);
    }
}
