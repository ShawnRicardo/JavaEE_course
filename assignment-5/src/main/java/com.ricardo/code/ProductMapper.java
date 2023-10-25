package edu.whu.demo.entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {
    @Select("SELECT p.id AS productId, p.name AS productName, p.stock_quantity AS productQuantity, " +
            "s.id AS supplierId, s.name AS supplierName " +
            "FROM product p " +
            "LEFT JOIN product_supplier ps ON p.id = ps.product_id " +
            "LEFT JOIN supplier s ON ps.supplier_id = s.id " +
            "WHERE p.name = #{name} AND p.stock_quantity >= #{quantity}")
    @Results({
            @Result(property = "productId", column = "productId"),
            @Result(property = "productName", column = "productName"),
            @Result(property = "productQuantity", column = "productQuantity"),
            @Result(property = "supplierId", column = "supplierId"),
            @Result(property = "supplierName", column = "supplierName")
    })
    List<ProductWithSupplier> findProductsWithSuppliers(@Param("name") String name, @Param("quantity") Float quantity);
    List<Product> findProductsByNameAndQuantity(@Param("name") String name, @Param("quantity") Float quantity);
    @Insert("INSERT INTO product (name, price, stock_Quantity, category) VALUES (#{name}, #{price}, #{stockQuantity}, #{category})")
    int insertProduct(Product product);
    @Update("UPDATE product SET name = #{name}, price = #{price}, stock_Quantity = #{stockQuantity}, category = #{category} WHERE id = #{id}")
    int updateProduct(Product product);
    @Delete("DELETE FROM product WHERE id = #{id}")
    int deleteProduct(@Param("id") Long id);

}
