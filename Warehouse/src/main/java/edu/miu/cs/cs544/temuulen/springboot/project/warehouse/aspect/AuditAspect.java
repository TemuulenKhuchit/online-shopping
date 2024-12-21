package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.aspect;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Product;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Stock;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class AuditAspect {

    @Before("execution(* edu.miu.cs.cs544.temuulen.springboot.project.warehouse.service.SaveService.saveStock(..)) && args(stock)")
    public void setStockUpdatedAt(Stock stock) {
        stock.setUpdatedAt(new Date());
        System.out.println("Updated 'updatedAt' timestamp for stock ID: " + stock.getId());
    }

    @Before("execution(* edu.miu.cs.cs544.temuulen.springboot.project.warehouse.service.SaveService.saveProduct(..)) && args(product)")
    public void setProductUpdatedAt(Product product) {
        product.setUpdatedAt(new Date());
        System.out.println("Updated 'updatedAt' timestamp for product ID: " + product.getId());
    }

}