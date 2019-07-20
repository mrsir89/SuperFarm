package com.example.spring.catalog.controller;

import com.example.spring.catalog.entity.Merchant;
import com.example.spring.catalog.entity.MerchantProduct;
import com.example.spring.catalog.entity.User;
import com.example.spring.catalog.service.ProductService;
import com.example.spring.catalog.util.PrincipalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAnyAuthority('WRITE_PRODUCT')")
    @RequestMapping(
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public MerchantProduct create(@Valid @RequestBody MerchantProduct product, Principal principal) {
        User user = (User) PrincipalUtil.from(principal);
        product.setMerchant(user.getMerchant());        //Merchant ID를 넣을 필요 없게 해줌
        return productService.createProduct(product);
    }

    @PreAuthorize("hasAnyAuthority('READ_PRODUCT')") // 게스트권한을 줌. 읽을수 있게.
    @RequestMapping(
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Collection<MerchantProduct> listOf(Principal principal) {
        User user = (User) PrincipalUtil.from(principal);
        Merchant merchant = user.getMerchant();
        return (merchant != null) ?
                productService.getProductsByMerchant(user.getMerchant())
                : productService.getProducts();
    }


    @PreAuthorize("hasAnyAuthority('WRITE_PRODUCT')")
    @RequestMapping(
            method = RequestMethod.PATCH,
            path = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public MerchantProduct updateBy(@PathVariable("id") UUID id,
                                    @RequestBody MerchantProduct product,
                                    Principal principal){
        return null;
    }

    @PreAuthorize("hasAnyAuthority('WRITE_PRODUCT')")
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/{id}",     // product Id 임
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public MerchantProduct deleteBy(@PathVariable("id") UUID id,
                                    Principal principal){
        return null;
    }
}