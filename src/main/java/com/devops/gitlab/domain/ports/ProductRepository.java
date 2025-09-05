package com.devops.gitlab.domain.ports;

import com.devops.gitlab.domain.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository  extends CrudRepository<Product, UUID> {

}
