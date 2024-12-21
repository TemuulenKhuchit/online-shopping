package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.config;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.*;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    private static final int NUMBER_OF_STOCKS = 50;
    private static final int MAX_QTY = 100;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private InventoryLogRepository inventoryLogRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("user", passwordEncoder.encode("user123"), Set.of("ROLE_USER"));
        userRepository.save(user);

        User admin = new User("admin", passwordEncoder.encode("admin123"), Set.of("ROLE_ADMIN"));
        userRepository.save(admin);

        List<Product> products = List.of(
                new Product("Laptop", 1000, 800, Category.ELECTRONICS, new Date()),
                new Product("T-shirt", 20, 10, Category.FASHION, new Date()),
                new Product("Apple", 2, 1, Category.GROCERY, new Date()),
                new Product("Microwave", 150, 120, Category.HOME_APPLIANCES, new Date()),
                new Product("Book", 15, 5, Category.BOOKS, new Date()),
                new Product("Doll", 25, 10, Category.TOYS, new Date()),
                new Product("Shampoo", 10, 4, Category.BEAUTY_AND_PERSONAL_CARE, new Date()),
                new Product("Soccer Ball", 30, 15, Category.SPORTS_AND_OUTDOORS, new Date()),
                new Product("Car Cover", 50, 30, Category.AUTOMOTIVE, new Date()),
                new Product("Vitamin C", 12, 6, Category.HEALTH_AND_WELLNESS, new Date())
        );
        productRepository.saveAll(products);

        List<Warehouse> warehouses = List.of(
                new Warehouse("Main Warehouse", 5000.0, new Address("USA", "CA", "Los Angeles", "123 Main St", "90001"), new Date()),
                new Warehouse("East Coast Warehouse", 3000.0, new Address("USA", "NY", "New York", "456 East Ave", "10001"), new Date()),
                new Warehouse("Central Storage", 4000.0, new Address("USA", "IL", "Chicago", "789 Central Blvd", "60601"), new Date()),
                new Warehouse("South Warehouse", 2500.0, new Address("USA", "TX", "Houston", "101 South St", "77001"), new Date()),
                new Warehouse("West Coast Storage", 3500.0, new Address("USA", "WA", "Seattle", "202 West Ave", "98101"), new Date()),
                new Warehouse("Mountain Warehouse", 2000.0, new Address("USA", "CO", "Denver", "303 Mountain Rd", "80201"), new Date()),
                new Warehouse("Florida Storage", 3200.0, new Address("USA", "FL", "Miami", "404 Sunshine Blvd", "33101"), new Date()),
                new Warehouse("Northeast Depot", 2800.0, new Address("USA", "MA", "Boston", "505 Harbor Rd", "02101"), new Date()),
                new Warehouse("Southwest Depot", 3600.0, new Address("USA", "AZ", "Phoenix", "606 Desert Dr", "85001"), new Date()),
                new Warehouse("Midwest Storage", 4100.0, new Address("USA", "OH", "Columbus", "707 Prairie Ln", "43001"), new Date())
        );
        warehouseRepository.saveAll(warehouses);

        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_STOCKS; i++) {
            Product product = products.get(random.nextInt(products.size()));
            Warehouse warehouse = warehouses.get(random.nextInt(warehouses.size()));
            int qty = random.nextInt(MAX_QTY) + 1;

            Stock stock = new Stock(product, warehouse, qty, new Date());
            stockRepository.save(stock);

            InventoryLog log = new InventoryLog(stock, StockChangeType.RESTOCK, 50, null, new Date(), "Initial stock added");
            inventoryLogRepository.save(log);
        }

        System.out.println("Test data loaded into database.");
    }
}
