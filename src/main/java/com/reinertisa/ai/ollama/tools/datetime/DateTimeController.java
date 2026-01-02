package com.reinertisa.ai.ollama.tools.datetime;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class DateTimeController {

    private final ChatClient chatClient;

    public DateTimeController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    // Ollama models do not reliably support tool/function calling
    @GetMapping("/tools")
    public String tools() {
        return chatClient.prompt()
                .user("What is tomorrow's date")
                .tools(new DateTimeTools())
                .call()
                .content();
    }
}
