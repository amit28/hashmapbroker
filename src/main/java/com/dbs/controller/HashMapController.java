package com.dbs.controller;

/*
 * This controller defines the following api for the hashmap service
 * 1. Put in map
 * 2. Get from map
 * 3. Remove from map
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.service.HashService;

@RestController
public class HashMapController {

	Log log = LogFactory.getLog(HashMapController.class);
	@Autowired
	HashService hashservice;

	@Autowired
	Cloud cloud;

	@RequestMapping(value = "/hashmap/put/{service_instance_id}", method = RequestMethod.PUT, headers = { "content-type=application/json" })
	public ResponseEntity<String> put(
			@PathVariable("service_instance_id") String serviceInstanceId,
			@RequestBody KeyValuePair kp) {
		log.info("request received to put in map ");
		log.info("key is: " + kp.getKey().toString());
		log.info("value is: " + kp.getValue().toString());
		hashservice.put(serviceInstanceId, kp.getKey(), kp.getValue());
		return new ResponseEntity<String>("{}", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/hashmap/get/{service_instance_id}", method = RequestMethod.PUT, headers = { "content-type=application/json" })
	public ResponseEntity<Object> get(
			@PathVariable("service_instance_id") String serviceInstanceId,
			@RequestBody KeyValuePair kp) {
		Object value = hashservice.get(serviceInstanceId, kp.getKey());
		if (value != null) {
			return new ResponseEntity<Object>(value, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("{}", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/hashmap/remove/{service_instance_id}", method = RequestMethod.DELETE, headers = { "content-type=application/json" })
	public ResponseEntity<String> remove(
			@PathVariable("service_instance_id") String serviceInstanceId,
			@RequestBody KeyValuePair kp) {
		Object value = hashservice.get(serviceInstanceId, kp.getKey());
		if (value != null) {
			hashservice.remove(serviceInstanceId, kp.getKey());
			return new ResponseEntity<String>("{}", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{}", HttpStatus.GONE);
		}
	}
}
