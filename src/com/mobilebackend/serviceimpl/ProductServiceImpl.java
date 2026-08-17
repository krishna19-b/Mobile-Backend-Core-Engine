package com.mobilebackend.serviceimpl;

import com.mobilebackend.exception.ProductNotFoundException;
import com.mobilebackend.model.Product;
import com.mobilebackend.repository.ProductRepository;
import com.mobilebackend.service.ProductService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl
        implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(
            ProductRepository repository) {

        this.repository = repository;
    }


    @Override
    public Product add(Product product) {

        validate(product);

        return repository.save(product);
    }


    @Override
    public Optional<Product> findById(int id) {

        return repository.findById(id);
    }


    @Override
    public Product update(Product product) {

        validate(product);

        if (repository.findById(
                product.getId()
        ).isEmpty()) {

            throw new ProductNotFoundException(
                    product.getId()
            );
        }

        repository.update(product);

        return product;
    }


    @Override
    public void delete(int id) {

        if (repository.findById(id).isEmpty()) {

            throw new ProductNotFoundException(id);
        }

        repository.delete(id);
    }


    @Override
    public List<Product> search(
            String keyword) {

        return repository.search(keyword);
    }


    @Override
    public List<Product> filterByCategory(
            String category) {

        return repository.findByCategory(category);
    }


    @Override
    public List<Product> sortByPrice(
            boolean ascending) {

        Comparator<Product> comparator =
                Comparator.comparing(
                        Product::getPrice
                );

        if (!ascending) {

            comparator =
                    comparator.reversed();
        }

        return repository.findAll()
                .stream()
                .sorted(comparator)
                .toList();
    }


    private void validate(Product product) {

        if (product == null) {

            throw new IllegalArgumentException(
                    "Product cannot be null"
            );
        }

        if (product.getName() == null ||
                product.getName().isBlank()) {

            throw new IllegalArgumentException(
                    "Product name required"
            );
        }

        if (product.getPrice() == null ||
                product.getPrice().signum() < 0) {

            throw new IllegalArgumentException(
                    "Invalid price"
            );
        }

        if (product.getQuantity() < 0) {

            throw new IllegalArgumentException(
                    "Invalid quantity"
            );
        }
    }
}