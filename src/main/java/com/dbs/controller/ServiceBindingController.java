package com.dbs.controller;

/*
 This controller defines the apis for creating and deleting binding with services which are bindable i.e. binding and unbinding api.
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.model.Credentials;
import com.dbs.model.ServiceBinding;
import com.dbs.repository.ServiceBindingRepository;
import com.dbs.repository.ServiceInstanceRepository;
import com.dbs.repository.ServiceRepository;
import com.dbs.service.HashService;

@RestController
public class ServiceBindingController {

	Log log = LogFactory.getLog(ServiceBindingController.class);

	@Autowired
	ServiceInstanceRepository serviceInstanceRepository;

	@Autowired
	ServiceBindingRepository serviceBindingRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	HashService hashService;

	@Autowired
	Environment env;

	@Autowired
	Cloud cloud;

	@RequestMapping(value = "/v2/service_instances/{instanceId}/service_bindings/{id}", method = RequestMethod.PUT, headers = { "content-type=application/json" })
	public ResponseEntity<Object> createBinding(
			@PathVariable("instanceId") String instanceId,
			@PathVariable("id") String id,
			@RequestBody ServiceBinding serviceBinding) {

		log.info("request received to create binding with id: " + id
				+ " to service instance instance having id: " + instanceId);

		if (!serviceInstanceRepository.exists(instanceId)) {
			log.info("service instance with instanceId :" + instanceId
					+ " does not exists ");
			return new ResponseEntity<Object>("{}", HttpStatus.BAD_REQUEST);
		}

		serviceBinding.setId(id);
		serviceBinding.setInstanceId(instanceId);

		if (serviceBindingRepository.exists(id)) {
			ServiceBinding existing = serviceBindingRepository.findOne(id);
			if (existing.equals(serviceBinding)) {
				log.info("service binding with binding id :" + id
						+ " already exists  ");
				return new ResponseEntity<Object>(
						wrapCredentials(existing.getCredentials()),
						HttpStatus.OK);
			} else {
				log.info("service binding with binding id :"
						+ id
						+ " but having different content already exists leading to conflict.");

				return new ResponseEntity<Object>("{}", HttpStatus.CONFLICT);
			}
		} else {
			log.info("No binding exists whith id: " + id
					+ " therefore a new binding will be created");
			ApplicationInstanceInfo appInstanceInfo = cloud
					.getApplicationInstanceInfo();
			List<String> list = (List<String>) appInstanceInfo.getProperties()
					.get("uris");
			List<ServiceInfo> serviceList = cloud.getServiceInfos();
			log.info("service list size is :" + serviceList.size());
			for (ServiceInfo service : serviceList) {
				System.out.println(service.getClass());
			}
			Credentials credentials = new Credentials();
			setCredentials(credentials);
			log.info("Credentials created with id: " + credentials.getId());
			serviceBinding.setCredentials(credentials);
			serviceBindingRepository.save(serviceBinding);
			return new ResponseEntity<Object>(wrapCredentials(credentials),
					HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/v2/service_instances/{instanceId}/service_bindings/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBinding(
			@PathVariable("instanceId") String instanceId,
			@PathVariable("id") String id,
			@RequestParam("service_id") String serviceId,
			@RequestParam("plan_id") String planId) {
		log.info("request received to delete service binding with id: " + id);
		boolean exists = serviceBindingRepository.exists(id);

		if (exists) {
			log.info("service binding with id: " + id
					+ " found and now going to delete it");
			serviceBindingRepository.delete(id);
			return new ResponseEntity<>("{}", HttpStatus.OK);
		} else {
			log.info("service binding with id: " + id + " is already deleted.");
			return new ResponseEntity<>("{}", HttpStatus.GONE);
		}
	}

	private Map<String, Object> wrapCredentials(Credentials credentials) {
		Map<String, Object> wrapper = new HashMap<>();
		wrapper.put("credentials", credentials);
		return wrapper;
	}

	private void setCredentials(Credentials credentials) {
		ApplicationInstanceInfo applicationInstanceInfo = cloud
				.getApplicationInstanceInfo();
		List<String> uriList = (List<String>) applicationInstanceInfo
				.getProperties().get("uris");
		credentials.setId(UUID.randomUUID().toString());
		credentials.setUsername(env.getProperty("username"));
		credentials.setPassword(env.getProperty("password"));
		if (uriList != null && uriList.get(0) != null) {
			credentials.setUri(uriList.get(0));
		}
	}
}
