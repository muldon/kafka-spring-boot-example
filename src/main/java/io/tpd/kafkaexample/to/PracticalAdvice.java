package io.tpd.kafkaexample.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PracticalAdvice(@JsonProperty("identifier") int identifier, @JsonProperty("message") String message) {
}
