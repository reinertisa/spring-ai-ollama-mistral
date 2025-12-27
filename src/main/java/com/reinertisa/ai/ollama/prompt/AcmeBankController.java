package com.reinertisa.ai.ollama.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/acme")
@CrossOrigin("*")
public class AcmeBankController {

    private final ChatClient chatClient;

    public AcmeBankController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {

        var systemInstructions = """
                You are a customer service assistant for AcmeBank.
                You can ONLY discuss.
                    - Account balances and transactions
                    - Branch locations and hours
                    - General banking services
                    If asked about anything else, respond: "I can only help with banking-related questions."
                """;

        return chatClient.prompt()
                .user(message)
                .system(systemInstructions)
                .call()
                .content();
    }
}
