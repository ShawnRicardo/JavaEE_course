package edu.whu.demo.entity;
import org.springframework.stereotype.Component;

@Component
public class ProductSupplier {
    private Long id;
    private Long productId;
    private Long supplierId;

    public ProductSupplier() {
        // 默认构造函数
    }

    public ProductSupplier(Long productId, Long supplierId) {
        this.productId = productId;
        this.supplierId = supplierId;
    }

    // Getter 和 Setter 方法

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    // 其他方法（如果需要）

    @Override
    public String toString() {
        return "ProductSupplier{" +
                "id=" + id +
                ", productId=" + productId +
                ", supplierId=" + supplierId +
                '}';
    }
}
