package edu.whu.demo.controller;
import edu.whu.demo.entity.*;

import edu.whu.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional//测试完成会自动回滚
@MapperScan("edu.whu.demo.entity") // 指定Mapper接口所在的包路径
public class ProductServiceTest {
    @Autowired
    private SupplierMapper supplierMapper;
    Long supplierId1;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductSupplierMapper productSupplierMapper;
    @BeforeEach
    void init() {
        // 初始化供应商数据
        Supplier supplier1 = new Supplier();
        supplier1.setName("Supplier 1");
        supplier1.setAddress("123 Main Street");
        supplierMapper.insertSupplier(supplier1);
        supplierId1 = supplier1.getId(); // 获取插入后的 id 值
        Supplier supplier2 = new Supplier();
        supplier2.setName("Supplier 2");
        supplier2.setAddress("456 Elm Street");
        supplierMapper.insertSupplier(supplier2);

        // 初始化产品数据
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(50f);
        product1.setStockQuantity(100);
        product1.setCategory("Category A");
        productMapper.insertProduct(product1);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(75f);
        product2.setStockQuantity(75);
        product2.setCategory("Category B");
        productMapper.insertProduct(product2);

        // 初始化产品与供应商关系数据
        ProductSupplier productSupplier1 = new ProductSupplier();
        productSupplier1.setProductId(product1.getId());
        productSupplier1.setSupplierId(supplier1.getId());
        productSupplierMapper.insertProductSupplier(productSupplier1);

        ProductSupplier productSupplier2 = new ProductSupplier();
        productSupplier2.setProductId(product2.getId());
        productSupplier2.setSupplierId(supplier2.getId());
        productSupplierMapper.insertProductSupplier(productSupplier2);
        Product product = new Product();
        product.setId(2L);
        product.setName("Test Product");
        product.setPrice(100F);
        product.setStockQuantity(50);

        Product addedProduct = productService.addProduct(product);
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100F);
        product.setStockQuantity(50);

        Product addedProduct1 = productService.addProduct(product);

    }

    @Autowired
    private ProductService productService;

    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setId(10L);
        product.setName("Test Product");
        product.setPrice(100F);
        product.setStockQuantity(50);

        Product addedProduct = productService.addProduct(product);

        assertNotNull(addedProduct);
        assertEquals(10, addedProduct.getId());
    }

    @Test
    void testGetProduct() {
        long productId = 1L;
        Product product = productService.getProduct(productId);

        assertNotNull(product);
        assertEquals(productId, product.getId());
    }

    @Test
    void testFindProduct() {
        String productName = "Test Product";
        Float quantity = 50.0f;
        List<Product> products = productService.findProduct(productName, quantity);

        assertNotNull(products);
        assertFalse(products.isEmpty());

        for (Product product : products) {
            assertTrue(product.getName().contains(productName));
            assertTrue(product.getStockQuantity() >= quantity);
        }
    }

    @Test
    void testUpdateProduct() {
        long productId = 1;
        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(150F);
        updatedProduct.setStockQuantity(60);

        productService.updateProduct(productId, updatedProduct);

        Product product = productService.getProduct(productId);

        assertNotNull(product);
        assertEquals(updatedProduct.getName(), product.getName());
        assertEquals(updatedProduct.getPrice(), product.getPrice());
        assertEquals(updatedProduct.getStockQuantity(), product.getStockQuantity());
    }

    @Test
    void testDeleteProduct() {
        long productId = 2;
        productService.deleteProduct(productId);
        Product product = productService.getProduct(productId);
        assertNull(product);
    }

    @Test
    void testMultiTableQuery() {
        // 编写多表查询的测试用例
        List<ProductWithSupplier> productsWithSuppliers = productService.findProductsWithSuppliers("Product 1", 100.0f);

        // 验证多表查询结果是否符合预期
        assertNotNull(productsWithSuppliers);
        assertFalse(productsWithSuppliers.isEmpty());

        for (ProductWithSupplier productWithSupplier : productsWithSuppliers) {
            // 验证查询结果中的商品和供货商信息
            assertNotNull(productWithSupplier.getProduct());
            assertNotNull(productWithSupplier.getSupplier());

            // 执行更多断言，验证数据是否正确
            // 例如，验证商品名称、供货商名称等信息
            assertEquals(productWithSupplier.getProduct().getName(), "Product 1");
            assertEquals(productWithSupplier.getSupplier().getName(), "Product 1");
        }
    }


}
