package com.akbyk.awsdemo.comprehend;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.comprehend.ComprehendClient;
import software.amazon.awssdk.services.comprehend.model.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComprehendService {

    private final ComprehendClient comprehendClient;

    public ComprehendService() {
        this.comprehendClient = ComprehendClient.builder()
                .region(Region.US_EAST_1) // same region as before
                .build();
    }

    public SentimentResult detectSentiment(String text) {
        DetectSentimentRequest request = DetectSentimentRequest.builder()
                .text(text)
                .languageCode("en")
                .build();

        DetectSentimentResponse response = comprehendClient.detectSentiment(request);
        SentimentScore score = response.sentimentScore();

        return new SentimentResult(
                response.sentiment().toString(),
                score.positive(),
                score.negative(),
                score.neutral(),
                score.mixed()
        );
    }

    public List<EntityResult> detectEntities(String text) {
        DetectEntitiesRequest request = DetectEntitiesRequest.builder()
                .text(text)
                .languageCode("en")
                .build();

        DetectEntitiesResponse response = comprehendClient.detectEntities(request);

        return response.entities()
                .stream()
                .map(e -> new EntityResult(e.text(), e.type().toString(), e.score()))
                .collect(Collectors.toList());
    }
}
