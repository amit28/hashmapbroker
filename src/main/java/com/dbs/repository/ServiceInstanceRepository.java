package com.dbs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.model.ServiceInstance;
@Repository
public interface ServiceInstanceRepository extends CrudRepository<ServiceInstance, String> {
}
