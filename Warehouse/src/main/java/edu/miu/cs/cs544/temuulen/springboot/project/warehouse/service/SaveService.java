package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.service;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Product;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Stock;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository.ProductRepository;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}