package com.reinertisa.ai.ollama.multimodal;


import org.springframework.ai.image.*;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ImageGeneration {

    private final OpenAiImageModel imageModel;
    public ImageGeneration(OpenAiImageModel imageModel) {
        this.imageModel = imageModel;
    }


    @GetMapping("/generate-image")
    public ResponseEntity<Map<String, String>> generateImage(
            @RequestParam(defaultValue = "A beautiful sunset over mountains") String prompt) {
        ImageOptions options = OpenAiImageOptions.builder()
                .model("dall-e-3")
                .width(1024)
                .height(1024)
                .quality("hd")
                .style("vivid")
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(prompt, options);
        ImageResponse imageResponse = imageModel.call(imagePrompt);

        String url = imageResponse.getResult().getOutput().getUrl();

        return ResponseEntity.ok(Map.of(
                "prompt", prompt,
                "imageUrl", url
        ));
    }
}
