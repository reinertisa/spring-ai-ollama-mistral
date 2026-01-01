package com.reinertisa.ai.ollama.bydo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ModelComparison {
    ChatClient chatClient;

    ModelComparison(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/models")
    public String models() {
        return chatClient.prompt()
                .user("Can you give me an up to date list popular large " +
                        "language models and their current context window?")
                .call()
                .content();
    }


//    This is important 👇
//    If you are using Ollama (LLaMA, Mistral, etc.):
//    Many Ollama models do not strictly obey system messages
//    Some treat system + user as merged text
//    Some models prioritize last user message only
//➡️ This is model behavior, not Spring Boot behavior.
    @GetMapping("/models/stuff-the-prompt")
    public String modelsStuffThePrompt() {
        var system = """
                If you're asked about up to date language models and there context window, here is some information to help you with your response:
                [
                    {"company": "OpenAI",        "model": "GPT-1o",           "context_window_size": 12000},
                    {"company": "OpenBI",        "model": "GPT-2o",           "context_window_size": 13000},
                    {"company": "OpenCI",        "model": "GPT-3o",           "context_window_size": 14000},
                    {"company": "OpenDI",        "model": "GPT-4o",           "context_window_size": 15000},
                    {"company": "OpenFI",        "model": "GPT-5o",           "context_window_size": 16000}
                ]
                """;

        return chatClient.prompt()
                .system(system)
                .user("Can you give me an up to date list popular large " +
                        "language models and their current context window?")
                .call()
                .content();
    }
}
