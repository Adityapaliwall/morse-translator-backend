package com.example.morsetranslatorbackend.logic;

import org.springframework.stereotype.Service;
import java.util.HashMap;

// The @Service annotation marks this class as a Spring service component.
// This allows Spring to manage its lifecycle and inject it where needed.
@Service
public class MorseCodeLogic {

    private final HashMap<Character, String> morseCodeMap;
    private final HashMap<String, Character> reverseMorseCodeMap;

    public MorseCodeLogic() {
        morseCodeMap = new HashMap<>();

        // Uppercase letters
        morseCodeMap.put('A', ".-"); morseCodeMap.put('B', "-..."); morseCodeMap.put('C', "-.-.");
        morseCodeMap.put('D', "-.."); morseCodeMap.put('E', "."); morseCodeMap.put('F', "..-.");
        morseCodeMap.put('G', "--."); morseCodeMap.put('H', "...."); morseCodeMap.put('I', "..");
        morseCodeMap.put('J', ".---"); morseCodeMap.put('K', "-.-"); morseCodeMap.put('L', ".-..");
        morseCodeMap.put('M', "--"); morseCodeMap.put('N', "-."); morseCodeMap.put('O', "---");
        morseCodeMap.put('P', ".--."); morseCodeMap.put('Q', "--.-"); morseCodeMap.put('R', ".-.");
        morseCodeMap.put('S', "..."); morseCodeMap.put('T', "-"); morseCodeMap.put('U', "..-");
        morseCodeMap.put('V', "...-"); morseCodeMap.put('W', ".--"); morseCodeMap.put('X', "-..-");
        morseCodeMap.put('Y', "-.--"); morseCodeMap.put('Z', "--..");

        // Lowercase letters (map to the same Morse code as uppercase)
        morseCodeMap.put('a', ".-"); morseCodeMap.put('b', "-..."); morseCodeMap.put('c', "-.-.");
        morseCodeMap.put('d', "-.."); morseCodeMap.put('e', "."); morseCodeMap.put('f', "..-.");
        morseCodeMap.put('g', "--."); morseCodeMap.put('h', "...."); morseCodeMap.put('i', "..");
        morseCodeMap.put('j', ".---"); morseCodeMap.put('k', "-.-"); morseCodeMap.put('l', ".-..");
        morseCodeMap.put('m', "--"); morseCodeMap.put('n', "-."); morseCodeMap.put('o', "---");
        morseCodeMap.put('p', ".--."); morseCodeMap.put('q', "--.-"); morseCodeMap.put('r', ".-.");
        morseCodeMap.put('s', "..."); morseCodeMap.put('t', "-"); morseCodeMap.put('u', "..-");
        morseCodeMap.put('v', "...-"); morseCodeMap.put('w', ".--"); morseCodeMap.put('x', "-..-");
        morseCodeMap.put('y', "-.--"); morseCodeMap.put('z', "--..");

        // Numbers
        morseCodeMap.put('0', "-----"); morseCodeMap.put('1', ".----"); morseCodeMap.put('2', "..---");
        morseCodeMap.put('3', "...--"); morseCodeMap.put('4', "....-"); morseCodeMap.put('5', ".....");
        morseCodeMap.put('6', "-...."); morseCodeMap.put('7', "--..."); morseCodeMap.put('8', "---..");
        morseCodeMap.put('9', "----.");

        // Special characters
        morseCodeMap.put(' ', "/"); morseCodeMap.put(',', "--..--"); morseCodeMap.put('.', ".-.-.-");
        morseCodeMap.put('?', "..--.."); morseCodeMap.put(';', "-.-.-."); morseCodeMap.put(':', "---...");
        morseCodeMap.put('(', "-.--."); morseCodeMap.put(')', "-.--.-"); morseCodeMap.put('[', "-.--.");
        morseCodeMap.put(']', "-.--.-"); morseCodeMap.put('{', "-.--."); morseCodeMap.put('}', "-.--.-");
        morseCodeMap.put('+', ".-.-."); morseCodeMap.put('-', "-....-"); morseCodeMap.put('_', "..--.-");
        morseCodeMap.put('"', ".-..-."); morseCodeMap.put('\'', ".----."); morseCodeMap.put('/', "-..-.");
        morseCodeMap.put('\\', "-..-."); morseCodeMap.put('@', ".--.-."); morseCodeMap.put('=', "-...-");
        morseCodeMap.put('!', "-.-.--");

        // Reverse Morse Code Map (for Morse to Text translation)
        reverseMorseCodeMap = new HashMap<>();
        for (Character key : morseCodeMap.keySet()) {
            String morse = morseCodeMap.get(key);
            // Handle cases where multiple characters map to the same Morse code (e.g., 'A' and 'a')
            // We'll just take the first one encountered, or you could decide on a specific priority.
            if (!reverseMorseCodeMap.containsKey(morse)) {
                reverseMorseCodeMap.put(morse, key);
            }
        }
    }

    /**
     * Translates a given text string into Morse code.
     * Each character is translated and separated by a space.
     * Spaces between words are represented by "/".
     * @param textToTranslate The input text to be translated.
     * @return The translated Morse code string.
     */
    public String translateToMorse(String textToTranslate) {
        StringBuilder translatedText = new StringBuilder();
        for (char letter : textToTranslate.toCharArray()) {
            String morse = morseCodeMap.get(letter);
            if (morse != null) {
                translatedText.append(morse).append(" ");
            } else {
                // Handle characters not in the map, e.g., by appending a placeholder or skipping
                translatedText.append("? "); // Or throw an exception, or skip
            }
        }
        return translatedText.toString().trim(); // Trim trailing space
    }

    /**
     * Translates a Morse code string into plain text.
     * Morse symbols should be separated by spaces. Words are separated by "/".
     * Unknown symbols are replaced with '#'.
     * @param morseCode The input Morse code string to be translated.
     * @return The translated plain text string.
     */
    public String translateToText(String morseCode) {
        StringBuilder translatedText = new StringBuilder();
        // Split by single space to get individual Morse symbols or word separators
        String[] morseSymbols = morseCode.trim().split(" ");

        for (String symbol : morseSymbols) {
            if (symbol.isEmpty()) {
                continue; // Skip empty strings that might result from multiple spaces
            }
            Character letter = reverseMorseCodeMap.get(symbol);
            if (letter != null) {
                translatedText.append(letter);
            } else if (symbol.equals("/")) {
                translatedText.append(" ");
            } else {
                translatedText.append("#"); // Placeholder for unknown Morse symbols
            }
        }
        return translatedText.toString();
    }

    // The playSound method is removed from the backend as it's for client-side audio.
    // Client-side audio will be handled by JavaScript in the HTML file.
}
