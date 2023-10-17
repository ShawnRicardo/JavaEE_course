package com.ricardo.springboothomework1.code;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "商品实体")
public class Product {

    @ApiModelProperty("商品实体编号")
    private String id;
    @ApiModelProperty("商品实体名称")
    private String name;
    @ApiModelProperty("商品实体价格")
    private double price;
    @ApiModelProperty("商品实体信息")
    private String info;
    @ApiModelProperty("商品是否可用")
    boolean is_available;


}
