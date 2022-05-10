package io.tpd.kafkaexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import io.tpd.kafkaexample.to.PracticalAdvice;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessageService {
	@Autowired
	private KafkaTemplate<String, Object> template;

	@Value("${tpd.topic-name}")
	private String topicName;
	

	public void sendMessage(PracticalAdvice practicalAdvice) {

		//log.info("KafkaMessageService receiving TO: " + practicalAdvice);
		template.send(topicName, String.valueOf(practicalAdvice.identifier()), practicalAdvice);
		
	}

}
