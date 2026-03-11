package com.akbyk.awsdemo.comprehend;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comprehend")
public class ComprehendController {

    private final ComprehendService comprehendService;

    public ComprehendController(ComprehendService comprehendService) {
        this.comprehendService = comprehendService;
    }

    @GetMapping("/sentiment")
    public SentimentResult getSentiment(@RequestParam String text) {
        return comprehendService.detectSentiment(text);
    }

    @GetMapping("/entities")
    public List<EntityResult> getEntities(@RequestParam String text) {
        return comprehendService.detectEntities(text);
    }
}
