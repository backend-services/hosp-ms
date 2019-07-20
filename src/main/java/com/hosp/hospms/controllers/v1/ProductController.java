package com.hosp.hospms.controllers.v1;

import com.hosp.hospms.models.DTOs.ProductDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
@CrossOrigin( origins = "*")
public class ProductController {

    @PostMapping
    public void create(@RequestBody ProductDTO productDTO){

        System.out.println(productDTO.getClass().getName());
        System.out.println(productDTO.toString());
    }

}
