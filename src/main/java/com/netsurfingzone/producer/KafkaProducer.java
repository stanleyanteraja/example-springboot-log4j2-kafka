package com.netsurfingzone.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netsurfingzone.constant.ApplicationConstant;
//import com.netsurfingzone.dto.Student;

//import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/produce")
public class KafkaProducer {
	//static Logger log = LogManager.getLogger(KafkaProducer.class.getName());

	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate_clusterA;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate_clusterB;

	
	@PostMapping("/message")
	public String sendMessage(@RequestBody String message) {

		try {
			kafkaTemplate_clusterA.send(ApplicationConstant.TOPIC_NAME, message);
			kafkaTemplate_clusterB.send(ApplicationConstant.TOPIC_NAME, message);
			//kafkaTemplate.send(ApplicationConstant.TOPIC_NAME, 0, null, message);
			log.info("logging by log4j v2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json message sent succuessfully";
	}

	
	/*
	@PostMapping("/message-cluster-tab")
	public String sendMessagePartition1(@RequestBody Student message) {

		try {
			kafkaTemplate_clusterA.send(ApplicationConstant.TOPIC_NAME, null, message);
			//log.info("logging by log4j v2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json message sent succuessfully";
	}

	
	@PostMapping("/message-clusterB")
	public String sendMessageClusterB(@RequestBody Student message) {

		try {
			kafkaTemplate.send(ApplicationConstantClusterB.TOPIC_NAME, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json message sent succuessfully";
	}
	*/
}
