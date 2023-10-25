package edu.whu.demo.entity;
import org.springframework.stereotype.Component;

@Component
public class ProductWithSupplier {
    // 类的属性
    private Product product;
    private Supplier supplier;

    // 构造函数
    public ProductWithSupplier(Product product, Supplier supplier) {
        this.product = product;
        this.supplier = supplier;
    }

    // Getter 和 Setter 方法

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    // 其他方法
}