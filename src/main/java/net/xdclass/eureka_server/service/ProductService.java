package net.xdclass.eureka_server.service;

import net.xdclass.eureka_server.domain.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductList();

    Product findProductById(int id);

}
