package com.reinertisa.ai.ollama.tools.action;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class TaskManagementController {

    private final ChatClient chatClient;
    private final TaskManagementTools taskManagementTools;

    public TaskManagementController(ChatClient chatClient, TaskManagementTools taskManagementTools) {
        this.chatClient = chatClient;
        this.taskManagementTools = taskManagementTools;
    }

    @GetMapping("/tasks")
    public String createTask(@RequestParam String message) {
        return chatClient.prompt()
                .tools(taskManagementTools)
                .user(message)
                .call()
                .content();
    }
}
