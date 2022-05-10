package io.tpd.kafkaexample.service;

import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import io.tpd.kafkaexample.to.PracticalAdvice;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumersService {

	 

//  @KafkaListener(topics = "advice-topic", clientIdPrefix = "json", containerFactory = "kafkaListenerContainerFactory")
//  public void listenAsDTO(ConsumerRecord<String, PracticalAdvice> cr, @Payload PracticalAdvice payload) throws InterruptedException {
//      log.info("log 0 [JSON] received key {}: Payload: {}", cr.key(), payload);        
//      Thread.sleep(3000);
//      
//  }

	@KafkaListener(topics = "advice-topic", clientIdPrefix = "json", containerFactory = "kafkaListenerContainerFactory")
	public void listenAsObject(@Payload PracticalAdvice payload) throws InterruptedException {
		log.info("log 1 [JSON] received Payload: {}", payload);
		Thread.sleep(2000);

	}

	@KafkaListener(topics = "advice-topic", clientIdPrefix = "string", containerFactory = "kafkaListenerStringContainerFactory")
	public void listenasString(ConsumerRecord<String, String> cr, @Payload String payload) throws InterruptedException {
		log.info("log 2 [String] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(), typeIdHeader(cr.headers()), payload, cr.toString());
		Thread.sleep(2000);

	}

	@KafkaListener(topics = "advice-topic", clientIdPrefix = "bytearray", containerFactory = "kafkaListenerByteArrayContainerFactory", groupId = "tpd-logs-2")
	public void listenAsByteArray(ConsumerRecord<String, byte[]> cr, @Payload byte[] payload) {
		log.info("log 3 [ByteArray] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(), typeIdHeader(cr.headers()), payload, cr.toString());
		// log.info("log 3 [ByteArray] received key {}", cr.key());

	}

	private static String typeIdHeader(Headers headers) {
		return StreamSupport.stream(headers.spliterator(), false).filter(header -> header.key().equals("__TypeId__")).findFirst().map(header -> new String(header.value())).orElse("N/A");
	}
}
