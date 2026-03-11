package com.akbyk.awsdemo.rekognition;


import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rekognition")
public class RekognitionController {

    private final RekognitionService rekognitionService;

    public RekognitionController(RekognitionService rekognitionService) {
        this.rekognitionService = rekognitionService;
    }

    @GetMapping("/labels")
    public List<LabelResult> getLabels(@RequestParam String imageUrl) throws Exception {
        return rekognitionService.detectLabels(imageUrl)
                .stream()
                .map(label -> new LabelResult(label.name(), label.confidence()))
                .collect(java.util.stream.Collectors.toList());
    }
}
