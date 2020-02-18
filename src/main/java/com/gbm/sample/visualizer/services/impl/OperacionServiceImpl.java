package com.gbm.sample.visualizer.services.impl;

import com.gbm.sample.visualizer.entities.Operacion;
import com.gbm.sample.visualizer.interfaces.OperacionRepository;
import com.gbm.sample.visualizer.services.OperacionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

  @Override
  public void clear(Pageable pageable) {
    Page<Operacion> page = operacionRepository.findAll(pageable);
    logger.info("Deleting page... ");
    logger.info("Pagina: ",page);
    operacionRepository.deleteAll();
  }
}