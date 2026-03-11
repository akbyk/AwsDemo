package com.akbyk.awsdemo.guardduty;

import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.guardduty.GuardDutyClient;
import software.amazon.awssdk.services.guardduty.model.*;

import java.util.List;

@RestController
@RequestMapping("/guardduty")
public class GuardDutyController {

    private final GuardDutyClient guardDutyClient;

    public GuardDutyController(GuardDutyClient guardDutyClient) {
        this.guardDutyClient = guardDutyClient;
    }

    // Detector ID'sini getir (GuardDuty'nin aktif olduğunu kanıtlar)
    @GetMapping("/detector")
    public List<String> getDetectors() {
        return guardDutyClient.listDetectors()
                .detectorIds();
    }

    // Mevcut tehditleri listele
    @GetMapping("/findings")
    public List<String> getFindings() {
        String detectorId = guardDutyClient.listDetectors()
                .detectorIds()
                .get(0);

        ListFindingsRequest request = ListFindingsRequest.builder()
                .detectorId(detectorId)
                .build();

        return guardDutyClient.listFindings(request)
                .findingIds();
    }
}
