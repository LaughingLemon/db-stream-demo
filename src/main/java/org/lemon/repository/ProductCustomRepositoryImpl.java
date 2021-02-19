package org.lemon.repository;

import org.lemon.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findByGeoIdAndLanguageId(Long geoId, Long languageId) {
        List<Product> productsForGeoId =
                entityManager.createQuery("select p from Product p where p.geoId = :geoId and p.languageId = :languageId")
                        .setParameter("geoId", geoId)
                        .setParameter("languageId", languageId)
                        .getResultList();
        final int size = productsForGeoId.size();
        if (size < 5) {
            List<Product> productsForLangId =
                    entityManager.createQuery("select p from Product p where p.geoId <> :geoId and p.languageId = :languageId")
                            .setParameter("geoId", geoId)
                            .setParameter("languageId", languageId)
                            .getResultList();
            productsForGeoId.addAll(productsForLangId.stream().limit(10 - size).collect(Collectors.toList()));
        }

        return productsForGeoId;
    }
}
