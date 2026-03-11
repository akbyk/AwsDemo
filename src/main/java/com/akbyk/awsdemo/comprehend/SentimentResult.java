package com.akbyk.awsdemo.comprehend;

public record SentimentResult(String sentiment,
                              float positive,
                              float negative,
                              float neutral,
                              float mixed) {
}
