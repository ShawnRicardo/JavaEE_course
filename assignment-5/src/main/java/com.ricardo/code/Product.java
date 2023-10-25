package edu.whu.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiaxy
 */
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@TableName("product")
public class Product {
    @TableId

    private Long id;
    private String name;
    private Float price;
    private int stockQuantity;
    private String category;
    private String productType;
    private String description;

    @TableField(exist = false)
    private Long supplierId; // 用于关联供货商
}

