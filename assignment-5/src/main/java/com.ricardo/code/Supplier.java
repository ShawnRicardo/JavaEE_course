package edu.whu.demo.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@TableName("supplier")
public class Supplier {
    @TableId
    private Long id;
    private String name;
    private String address;
}