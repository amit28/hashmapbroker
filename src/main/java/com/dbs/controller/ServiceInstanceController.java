package com.dbs.controller;

/*
 This controller define the apis for creating and deleting service instances i.e. provisioning and deprovisioning
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.model.ServiceInstance;
import com.dbs.repository.ServiceInstanceRepository;
import com.dbs.repository.ServiceRepository;
import com.dbs.service.HashService;

@RestController
public class ServiceInstanceController {

	Log log = LogFactory.getLog(ServiceInstanceController.class);

	@Autowired
	ServiceInstanceRepository serviceInstanceRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	HashService hashService;

	@RequestMapping(value = "/v2/service_instances/{id}", method = RequestMethod.PUT, headers = { "content-type=application/json" })
	public ResponseEntity<String> create(@PathVariable("id") String id,
			@RequestBody ServiceInstance serviceInstance) {

		log.info("request received to create service instance with id: " + id);
		serviceInstance.setId(id);
		boolean exists = serviceInstanceRepository.exists(id);
		if (exists) {

			ServiceInstance existing = serviceInstanceRepository.findOne(id);
			if (existing.equals(serviceInstance)) {
				log.info("service instance with id: " + id
						+ " already exists having same content");
				return new ResponseEntity<>("{}", HttpStatus.OK);
			} else {
				log.info("service instance with id: "
						+ id
						+ " already exists but different content leading to a conflict");

				return new ResponseEntity<>("{}", HttpStatus.CONFLICT);
			}
		} else {
			log.info("service instance with id: " + id + " created");
			serviceInstanceRepository.save(serviceInstance);
			hashService.create(id);
			return new ResponseEntity<>("{}", HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/v2/service_instances/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("id") String id,
			@RequestParam("service_id") String serviceId,
			@RequestParam("plan_id") String planId) {
		log.info("request received to delete service instance with id: " + id);
		boolean exists = serviceRepository.exists(id);

		if (exists) {
			log.info("service instance with id: " + id
					+ " found and now deleting it");
			serviceRepository.delete(id);
			hashService.delete(id);
			return new ResponseEntity<>("{}", HttpStatus.OK);
		} else {
			log.info("service instance with id: " + id
					+ " does not exists or is already deleted");
			return new ResponseEntity<>("{}", HttpStatus.GONE);
		}
	}
}
