package com.ricardo.springboothomework1.code;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品管理")
@RestController
@RequestMapping("/productMap")
public class ProductController {

    @Autowired
    ProductManager productManager;

    // get: localhost:8080/productMap/id=1
    @ApiOperation("根据id查询商品")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@ApiParam("商品Id")@PathVariable String id){
        Product result = productManager.findProductById(id);
        if(result==null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    // get: localhost:8080/productMap?name=作业
    @ApiOperation("根据名字查询商品")
    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@ApiParam("商品名称")@PathVariable String name){
        Product result = productManager.findProductByName(name);
        if(result==null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    // get: localhost:8088/productMap?minPrice=100&&maxPrice=500&&available=true
    @ApiOperation("查找符合要求的商品")
    @GetMapping("")
    public ResponseEntity<List<Product>> findProducts(
            @ApiParam("最低价格") @RequestParam(required = false) Double minPrice,
            @ApiParam("最高价格") @RequestParam(required = false) Double maxPrice,
            @ApiParam("是否可用") @RequestParam(required = false) Boolean available
    ) {
        List<Product> result = productManager.rightProducts(minPrice, maxPrice, available);
        return ResponseEntity.ok(result);
    }


    @ApiOperation("添加商品")
    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Product pro){
        Product result = productManager.addProduct(pro);
        return ResponseEntity.ok(result);
    }


    @ApiOperation("修改商品")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable String id,@RequestBody Product pro){
        productManager.resetProduct(id, pro);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("删除商品")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id){
        productManager.removeProduct(id);
        return ResponseEntity.ok().build();
    }
}
