package com.example.demo130.Repository;

import com.example.demo130.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByVendor_VendorId(int vendorId); // Corrected method name

    List<Product> findByCategory_CategoryName(String categoryName);
    Product findByProductName(String name);
}
