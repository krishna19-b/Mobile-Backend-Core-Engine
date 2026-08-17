package com.mobilebackend.exception;

public class InsufficientInventoryException
        extends RuntimeException {

    public InsufficientInventoryException(
            int productId) {

        super(
                "Insufficient inventory for product: "
                        + productId
        );
    }
}