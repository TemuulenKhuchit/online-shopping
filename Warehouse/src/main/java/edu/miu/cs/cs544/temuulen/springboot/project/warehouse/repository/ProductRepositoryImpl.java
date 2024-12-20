package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findProductsByPriceRange(int minPrice, int maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        Predicate minPricePredicate = cb.greaterThanOrEqualTo(root.get("price"), minPrice);
        Predicate maxPricePredicate = cb.lessThanOrEqualTo(root.get("price"), maxPrice);
        query.where(cb.and(minPricePredicate, maxPricePredicate));

        return entityManager.createQuery(query).getResultList();
    }
}
