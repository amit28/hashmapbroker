package com.dbs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.model.ServiceBinding;
@Repository
public interface ServiceBindingRepository extends CrudRepository<ServiceBinding,String> {
}
