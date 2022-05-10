package io.tpd.kafkaexample.controller;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.tpd.kafkaexample.service.KafkaMessageService;
import io.tpd.kafkaexample.to.PracticalAdvice;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloKafkaController {
  
    //private CountDownLatch latch;
	
	@Autowired
	KafkaMessageService kafkaMessageService;
	
	@Value("${tpd.messages-per-request}")
	private int messagesPerRequest;

    @GetMapping("/hello")
    public String hello() throws Exception {
        //latch = new CountDownLatch(messagesPerRequest);    	
        log.info("Sending messages");
        IntStream.range(0, messagesPerRequest)
           .forEach(i ->  kafkaMessageService.sendMessage(new PracticalAdvice(i,"A Practical Advice"))           
        );
        //latch.await(5, TimeUnit.SECONDS);
        log.info("All messages received");
        return "Hello Kafka!";
    }
    
//    @GetMapping("/hello2")
//    public String hello2() throws Exception {
//        //latch = new CountDownLatch(messagesPerRequest);
//    	//latch = new CountDownLatch(messagesPerRequest*2);
//        log.info("Sending messages 2");
//        for(int i=0; i< this.messagesPerRequest; i++) {        	
//        	this.template.send(topicName, String.valueOf(i+1000), "A String data");
//        }
//        //latch.await(5, TimeUnit.SECONDS);
//        log.info("All messages 2 received");
//        return "Hello Kafka 2!";
//    }
    

}
