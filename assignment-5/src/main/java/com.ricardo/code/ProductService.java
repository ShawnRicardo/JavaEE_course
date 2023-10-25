
package edu.whu.demo.service;
import edu.whu.demo.entity.Product;
import edu.whu.demo.entity.ProductMapper;
import edu.whu.demo.entity.ProductWithSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    // 实现多表查询方法
    public List<ProductWithSupplier> findProductsWithSuppliers(String name, Float quantity) {
        return productMapper.findProductsWithSuppliers(name, quantity);
    }

    /**
     * 添加商品
     * @param product
     * @return
     */
    public Product addProduct(Product product) {
        productMapper.insert(product);
        return product;
    }

    /**
     * 根据Id查找
     * @param id
     * @return
     */
    public Product getProduct(long id) {
        return productMapper.selectById(id);
    }

    /**
     * 根据名称和库存查找
     * @param name
     * @param quantity
     * @return
     */
    public List<Product> findProduct(String name, Float quantity) {
        return productMapper.findProductsByNameAndQuantity(name, quantity);
    }

    /**
     * 更新商品信息
     * @param id
     * @param product
     */
    public void updateProduct(long id, Product product) {
        product.setId(id);
        productMapper.updateById(product);
    }

    /**
     * 删除商品
     * @param id
     */
    public void deleteProduct(long id) {
        productMapper.deleteById(id);
    }
}
