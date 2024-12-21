package edu.miu.cs.cs544.temuulen.springboot.project.order.repository;

import edu.miu.cs.cs544.temuulen.springboot.project.order.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}