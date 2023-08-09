package org.example.services;

import org.example.data.models.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductServices {
    Product save(Product product);
}
