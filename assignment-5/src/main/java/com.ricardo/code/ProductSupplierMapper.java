package edu.whu.demo.entity;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductSupplierMapper {
    @Select("SELECT * FROM product_supplier WHERE product_id = #{productId} AND supplier_id = #{supplierId}")
    ProductSupplier findProductSupplier(@Param("productId") Long productId, @Param("supplierId") Long supplierId);

    @Insert("INSERT INTO product_supplier (product_id, supplier_id) VALUES (#{productId}, #{supplierId})")
    int insertProductSupplier(ProductSupplier productSupplier);

    @Update("UPDATE product_supplier SET product_id = #{productId}, supplier_id = #{supplierId} WHERE id = #{id}")
    int updateProductSupplier(ProductSupplier productSupplier);

    @Delete("DELETE FROM product_supplier WHERE id = #{id}")
    int deleteProductSupplier(@Param("id") Long id);
}
