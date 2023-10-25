package edu.whu.demo.entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface SupplierMapper extends BaseMapper<Supplier> {
    List<Supplier> findSuppliersByName(@Param("name") String name);
    @Insert("INSERT INTO supplier (name, address) VALUES (#{name}, #{address})")
    int insertSupplier(Supplier supplier);
    @Update("DELETE FROM supplier WHERE id = #{id}")
    int deleteSupplier(@Param("id") Long id);


}