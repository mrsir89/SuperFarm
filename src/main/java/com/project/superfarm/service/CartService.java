package com.project.superfarm.service;


import com.project.superfarm.entity.product.Cart;
import com.project.superfarm.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;



    @Transactional
    public Cart saveCartProduct(Cart cart){

        if(cart.getUserNum()!=null) {

            Cart optionalCart = cartRepository.save(cart);
            if(optionalCart!=null){
                return optionalCart;
            }
            else
                return null;
        }
        else
            return null;
    }

    public List<Cart> loadCart(Long userNum){

        return cartRepository.findAllByUserNum(userNum);
    }

    @Transactional
    public void deleteCart(Long cartProductNum){

        cartRepository.deleteById(cartProductNum);

    }

    public void deleteAllCart(Long userNum){

//        cartRepository.deleteAllByUserNum(userNum);
    }

    @Transactional
    public Cart productCountUpdate(Long cartProductNum, int count){

        int result = cartRepository
                .cartProductCountUpdate(count, cartProductNum);
        if(result ==1){
            return cartRepository.findById(cartProductNum).get();
        }
        else{
            return null;
        }
    }


}
