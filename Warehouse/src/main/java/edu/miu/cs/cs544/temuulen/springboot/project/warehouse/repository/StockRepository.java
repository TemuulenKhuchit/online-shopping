package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Product;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Stock;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProductAndWarehouse(Product product, Warehouse warehouse);

    @Query("SELECT SUM(s.qty) FROM Stock s WHERE s.product.id = :productId")
    Optional<Integer> findTotalStockByProductId(@Param("productId") Long productId);

    @Query("SELECT s FROM Stock s WHERE s.product = :product ORDER BY s.qty DESC")
    List<Stock> findByProductOrderByQtyDesc(@Param("product") Product product);

}
