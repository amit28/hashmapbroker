package com.dbs.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class HashService {
	Log log = LogFactory.getLog(HashService.class);
	private CustomHashMap<String, CustomHashMap<Object, Object>> customMaps = new CustomHashMap<String, CustomHashMap<Object, Object>>();

	public void create(String id) {
		customMaps.put(id, new CustomHashMap<Object, Object>());
	}

	public void delete(String id) {
		customMaps.remove(id);
	}

	public void put(String id, Object key, Object value) {
		log.info("retrieving mapInstance for service instance id: " + id);
		CustomHashMap<Object, Object> mapInstance = customMaps.get(id);
		mapInstance.put(key, value);
	}

	public Object get(String id, Object key) {
		CustomHashMap<Object, Object> mapInstance = customMaps.get(id);
		return mapInstance.get(key);

	}

	public void remove(String id, Object key) {
		CustomHashMap<Object, Object> mapInstance = customMaps.get(id);
		mapInstance.remove(key);
	}
}
