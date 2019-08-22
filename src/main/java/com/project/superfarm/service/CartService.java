package com.project.superfarm.service;


import com.project.superfarm.entity.product.Cart;
import com.project.superfarm.repository.CartRepository;
import com.project.superfarm.util.ExceptionList.UrlNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public Cart deleteCart(Long cartProductNum){

        cartRepository.deleteById(cartProductNum);
        Optional<Cart> cartOptional = cartRepository.findById(cartProductNum);
        if(cartOptional.isPresent()){
            return cartOptional.get();
        }else
            throw new UrlNotFountException();

    }

    public void deleteAllCart(Long userNum){

//        cartRepository.deleteAllByUserNum(userNum);
    }

    @Transactional
    public List<Cart> productCountUpdate(Cart cart){

        if(cart.getCartNum()!=null  && cart.getCartProductCount() !=0) {
            Long cartProductNum = cart.getCartNum();
            Long userNum = cart.getUserNum();
            int count = cart.getCartProductCount();

            int result = cartRepository
                    .cartProductCountUpdate(count, cartProductNum);
            if (result == 1) {
                return cartRepository.findAllByUserNum(userNum);
            } else {
                return null;
            }
        }else{
            throw new UrlNotFountException();
        }
    }


}
