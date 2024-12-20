package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
