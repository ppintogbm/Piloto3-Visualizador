package com.gbm.sample.visualizer;

import com.gbm.sample.visualizer.services.OperacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class VisualizerApplication {

	private static final Logger logger = LoggerFactory.getLogger(VisualizerApplication.class);

	@Autowired
	private OperacionService operacionService;

	public static void main(String[] args) {
		SpringApplication.run(VisualizerApplication.class, args);
	}

	@Scheduled(fixedRateString = "PT30S")
	public void cleanDB(){
		logger.info("Iniciando DB Cleaner...");
		operacionService.clearTop();
		logger.info("Terminando DB Cleaner...");
	}

}
