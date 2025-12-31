package com.reinertisa.ai.ollama.memory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class MemoryController {

    private final ChatClient chatClient;

    public MemoryController(@Qualifier("chatClient") ChatClient chatClient) {
        this.chatClient = chatClient;

    }

    @GetMapping("/memory")
    public String memory(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
