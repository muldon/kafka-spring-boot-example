package io.tpd.kafkaexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import io.tpd.kafkaexample.to.PracticalAdvice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Producer {
	@Autowired
	private KafkaTemplate<String, Object> template;

	@Value("${tpd.topic-name}")
	private String topicName;

	
	public void sendMessage(PracticalAdvice practicalAdvice) {
		template.send(topicName, String.valueOf(practicalAdvice.identifier()), practicalAdvice);		
	}
	
	
}
