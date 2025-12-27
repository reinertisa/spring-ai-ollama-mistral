package com.reinertisa.ai.ollama.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class OllamaChatController {
    private final ChatClient chatClient;

    public OllamaChatController(@Qualifier("ollamaChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat() {
        return chatClient.prompt()
                .user("Tell me an interesting fact about Java")
                .call()
                .content();
    }

    @GetMapping("/stream")
    public Flux<String> stream() {
        return chatClient.prompt()
                .user("I'm visiting Hilton Head soon, can you give me 10 places I must visit?")
                .stream()
                .content();
    }

    @GetMapping("/joke")
    public ChatResponse joke() {
        return chatClient.prompt()
                .user("Tell ma a dad joke about dogs")
                .call()
                .chatResponse();
    }
}
