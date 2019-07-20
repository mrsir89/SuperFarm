package com.example.spring.catalog.service;

import com.example.spring.catalog.entity.Merchant;
import com.example.spring.catalog.entity.MerchantProduct;
import com.example.spring.catalog.entity.MerchantProductComposite;
import com.example.spring.catalog.model.CompositeItem;
import com.example.spring.catalog.model.CreateComposite;
import com.example.spring.catalog.repository.MerchantProductCompositeRepository;
import com.example.spring.catalog.repository.MerchantProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
public class ProductService {

    @Autowired
    private MerchantProductRepository productRepository;

    @Autowired
    private MerchantProductCompositeRepository compositeRepository;

   /* @Transactional
    public MerchantProduct createComposite(CreateComposite createComposite) {
        // Step 1: make a primary product item
        MerchantProduct product
                = productRepository.save(createComposite.getParent());

        // Step 2: make composite items
        for (CompositeItem item : createComposite.getItems()) {
            MerchantProductComposite composite = new MerchantProductComposite();
            composite.setParent(product);
            composite.setItem(
                    productRepository.findById(item.getItemId())
                            .get());
            composite.setQuantity(item.getQuantity());
            compositeRepository.save(composite);
        }
        return product;
    }*/

    @Transactional
    public MerchantProduct createProduct(MerchantProduct product) {
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Collection<MerchantProduct> getProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<MerchantProduct> getProductsByMerchant(Merchant merchant) {
        return productRepository.findByMerchantId(merchant.getId());
    }



    @Transactional // 내거만 찾아서 지우겠다. id, merchant가 둘 다 매칭이 되어야 내것.
    public MerchantProduct deleteProductById(String id, Merchant merchant) {
        MerchantProduct product = productRepository.findByIdAndMerchantId(id,merchant.getId());
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not fount");
        }
        productRepository.deleteById(id);
        return product;
    }

}
