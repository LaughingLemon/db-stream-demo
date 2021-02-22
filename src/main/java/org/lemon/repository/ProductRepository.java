package org.lemon.repository;

import org.lemon.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    List<Product> findByGeoIdAndLanguageId(Long geoId, Long languageId);
    List<Product> findByGeoIdIsNotAndLanguageId(Long geoId, Long languageId);
}
