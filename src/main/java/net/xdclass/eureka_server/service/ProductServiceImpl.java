package net.xdclass.eureka_server.service;

import net.xdclass.eureka_server.domain.entity.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private static Map<Integer, Product> daoMap = new HashMap<Integer, Product>();

    static{
        Product p1 = new Product(1,"zqq1");
        Product p2 = new Product(2,"zqq2");
        Product p3= new Product(3,"zqq3");
        Product p4 = new Product(4,"zqq4");
        Product p5 = new Product(5,"zqq5");
        Product p6 = new Product(6,"zqq6");
        daoMap.put(p1.getId(),p1);
        daoMap.put(p2.getId(),p1);
        daoMap.put(p3.getId(),p1);
        daoMap.put(p4.getId(),p1);
        daoMap.put(p5.getId(),p1);
        daoMap.put(p6.getId(),p1);

    }
    @Override
    public List<Product> getProductList() {
     Collection<Product> coll =   daoMap.values();
     List<Product> list = new ArrayList<>(coll);
        return list;
    }

    @Override
    public Product findProductById(int id) {
        return daoMap.get(id);
    }
}
