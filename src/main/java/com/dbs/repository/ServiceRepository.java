package com.dbs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.model.Service;
@Repository
public interface ServiceRepository extends CrudRepository<Service, String> {
}
