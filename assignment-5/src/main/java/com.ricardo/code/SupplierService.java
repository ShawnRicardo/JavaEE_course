package edu.whu.demo.service;
import edu.whu.demo.entity.Supplier;
import edu.whu.demo.entity.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    /**
     * 添加供货商
     * @param supplier 供货商实体
     * @return 添加后的供货商实体
     */
    public Supplier addSupplier(Supplier supplier) {
        supplierMapper.insert(supplier);
        return supplier;
    }

    /**
     * 根据ID查找供货商
     * @param id 供货商ID
     * @return 供货商实体
     */
    public Supplier getSupplier(long id) {
        return supplierMapper.selectById(id);
    }

    /**
     * 根据名称查找供货商
     * @param name 供货商名称
     * @return 供货商实体列表
     */
    public List<Supplier> findSupplierByName(String name) {
        return supplierMapper.findSuppliersByName(name);
    }

    /**
     * 更新供货商信息
     * @param id 供货商ID
     * @param supplier 更新后的供货商实体
     */
    public void updateSupplier(long id, Supplier supplier) {
        supplier.setId(id);
        supplierMapper.updateById(supplier);
    }

    /**
     * 删除供货商
     * @param id 供货商ID
     */
    public void deleteSupplier(long id) {
        supplierMapper.deleteById(id);
    }

    /**
     * 删除所有供货商
     */
    public void deleteAll() {
        supplierMapper.delete(null);
    }
}
