package com.ricardo.springboothomework1.code;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductManager {

    private Map<String, Product> productMap = Collections.synchronizedMap(new HashMap<String, Product>());

    // 查找单个商品
    public Product findProductById(String id){
        return productMap.get(id);
    }
    public Product findProductByName(String name){
        return productMap.get(name);
    }
    //查找符合要求的系列商品
    public List<Product> rightProducts(double minPrice, double maxPrice, boolean is_available){
        List<Product> results = new ArrayList<>();

        for(Product product: productMap.values()){
            if(product.is_available() == is_available && product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                results.add(product);
            }
        }
        return results;
    }

    // 添加新的商品
    public Product addProduct(Product product){

        productMap.put(product.getId(), product);
        return product;
    }


    // 更新相对应id的product
    public void resetProduct(String id, Product newProduct){

        Product oldProduct = findProductById(id);
        oldProduct.setName(newProduct.getName());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setInfo(newProduct.getInfo());

        productMap.put(id, oldProduct);
    }

    // 删除对应 id 的商品
    public void removeProduct(String id) {
        productMap.remove(id);
    }

}
