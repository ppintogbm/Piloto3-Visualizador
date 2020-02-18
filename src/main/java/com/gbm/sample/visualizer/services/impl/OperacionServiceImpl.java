package com.gbm.sample.visualizer.services.impl;

import com.gbm.sample.visualizer.entities.Operacion;
import com.gbm.sample.visualizer.interfaces.OperacionRepository;
import com.gbm.sample.visualizer.services.OperacionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OperacionServiceImpl implements OperacionService{

  private static final Logger logger = LoggerFactory.getLogger(OperacionServiceImpl.class);
  
  @Autowired
  private OperacionRepository operacionRepository;

  @Override
  public Page<Operacion> findAllPageable(Pageable pageable) {
    return operacionRepository.findAll(pageable);
  }

  @Override
  public void clear() {
    operacionRepository.deleteAll();
  }

  public void clearTop(){
    if(operacionRepository.count() > 50){
      logger.info("Eliminando registros por encima del 50...");
      int i = 2;
      Page<Operacion> page;
      PageRequest pageable;
      do{
        pageable = PageRequest.of(i-1, 50);
        page = this.findAllPageable(pageable);
        logger.info("Eliminando página "+i);
        logger.info(page.getContent().toString());
        i++;
      }while(i-1 <= page.getTotalPages());
    }
  }
}