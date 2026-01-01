package com.reinertisa.ai.ollama.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ModelsController {

    private final ChatClient chatClient;

    public ModelsController(@Qualifier("ragChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/rag/models")
    public Models faq(@RequestParam(value = "message", defaultValue = "Give me a list of all the models from OpenAI along with their context window.") String message) {
        return chatClient.prompt()
                .user(message)
                .system("""
                        You must answer ONLY using the provided context.
                        If the answer is not in the context, say "I don't know".""")
                .call()
                .entity(Models.class);
    }

}
