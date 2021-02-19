package org.lemon.repository;

import org.lemon.model.Product;

import java.util.List;

public interface ProductCustomRepository {
    List<Product> findByGeoIdAndLanguageId(Long geoId, Long languageId);
}
