package com.akbyk.awsdemo.rekognition;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;
import software.amazon.awssdk.core.SdkBytes;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Service
public class RekognitionService {
    private final RekognitionClient rekognitionClient;

    public RekognitionService() {
        // Automatically uses ~/.aws/credentials
        this.rekognitionClient = RekognitionClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    public List<Label> detectLabels(String imageUrl) throws Exception {
        InputStream imageStream = new URL(imageUrl).openStream();
        SdkBytes imageBytes = SdkBytes.fromInputStream(imageStream);

        DetectLabelsRequest request = DetectLabelsRequest.builder()
                .image(Image.builder().bytes(imageBytes).build())
                .maxLabels(10)
                .minConfidence(75F)
                .build();

        DetectLabelsResponse response = rekognitionClient.detectLabels(request);
        return response.labels();
    }
}
