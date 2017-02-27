package com.dbs.controller;
/*
 This controller serves the first endpoint i.e. retrieves catalog
 */
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.model.Service;
import com.dbs.repository.ServiceRepository;

@RestController
public class CatalogController {

	Log log = LogFactory.getLog(CatalogController.class);

	@Autowired
	ServiceRepository serviceRepository;
	
	//get catalog
	@RequestMapping("/v2/catalog")
	public Map<String, Iterable<Service>> catalog() {
		log.info("request received to get catalog");
		Map<String, Iterable<Service>> result = new HashMap<String, Iterable<Service>>();
		result.put("services", serviceRepository.findAll());
		return result;
	}

}
