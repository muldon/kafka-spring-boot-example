package io.tpd.kafkaexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.tpd.kafkaexample.to.PracticalAdvice;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessageService {
	
	@Autowired
	private Producer producer;
	

	public void sendMessage(PracticalAdvice practicalAdvice) {

		log.info("KafkaMessageService receiving TO: " + practicalAdvice);
		
		producer.sendMessage(practicalAdvice);
		
		
		
	}

}
