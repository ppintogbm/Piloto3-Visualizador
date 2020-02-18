package com.gbm.sample.visualizer.services;

import java.util.List;

import com.gbm.sample.visualizer.entities.Operacion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperacionService{

  Page<Operacion> findAllPageable(Pageable pageable);

  void clear();
  void clearTop();

}