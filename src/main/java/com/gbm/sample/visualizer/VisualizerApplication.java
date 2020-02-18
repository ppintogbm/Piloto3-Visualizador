package com.gbm.sample.visualizer;

import com.gbm.sample.visualizer.entities.Operacion;
import com.gbm.sample.visualizer.services.OperacionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
		PageRequest pageable = PageRequest.of(0, 5);
    Page<Operacion> operacionPage = operacionService.findAllPageable(pageable);
    
		int totalPages = operacionPage.getTotalPages();
		for(int i = totalPages; i > 10; i--){
			logger.info("Eliminando página: "+i);
			pageable = PageRequest.of(i -1, 5);
			operacionService.clear(pageable);
		}

		logger.info("Terminando DB Cleaner...");
	}

}
