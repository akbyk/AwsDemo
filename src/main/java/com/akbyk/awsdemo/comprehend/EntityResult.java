package com.akbyk.awsdemo.comprehend;

public record EntityResult(String text,
                           String type,
                           float confidence) {
}
