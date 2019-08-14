package com.project.superfarm.controller;


import com.project.superfarm.entity.product.Cart;
import com.project.superfarm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
          @apiNote   : 카트  추가,삭제,전체삭제, 수량 수정
          @Url /cart : /add, /delete, /deleteAll, /edit
          @brief     : /add 추가
                     : /delete 삭제
                     : /deleteAll 전체삭제
                     : /edit   수량 수정

 **/


@RequestMapping("/cart")
@RestController
public class CartController {
    // 안수윤 테스트
    @Autowired
    private CartService cartService;


    /** Todo : error 발생 규격화
     *  @apiNote : 고개번호로 카트 상품추가
     *  @Url     : /cart/add
     *  @see : entity Cart   DB: cart
        @param   :Json
                 "userNum",
                 "productBoardNum",
                 "cartProductName",
                 "cartProductOption1",
                 "cartProductOption2",
                 "cartProductPrice",
                 "cartProductCount",
                 "cartProductImg"

        @return  :Json Cart.java

     */
    @RequestMapping(path="/add",
    method = RequestMethod.POST,
    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
                MediaType.APPLICATION_ATOM_XML_VALUE})
    public Cart addCartProduct(@RequestBody Cart cart) throws ClassNotFoundException {

        Cart cartCheck = cartService.saveCartProduct(cart);
        if(cartCheck ==null){
            throw new ClassNotFoundException();
        }
        return cartCheck;
    }


    /**
     *
     * @apiNote    : 장바구니 CartNum 으로 장바구니 목록 상품 하나 삭제
     * @Url        : /cart/delete
     * @see        : entity Cart   DB: cart
     * @param      : Long cartNum
     * @return     : void
     *
     */
    @RequestMapping(path="/delete",
    method = RequestMethod.DELETE)
    public void deleteCartProduct(@RequestParam(name ="cartNum")Long cartNum){

        cartService.deleteCart(cartNum);

    }

    /**
     * :
     * @apiNote    : 장바구니 userNum 으로 고객장바구니 목록 전체 삭제
     * @Url        :/cart/deleteAll
     * @see        : entity Cart   DB: cart
     * @param      : Long userNum 고객 번호
     * @return      void
     *
     */
    @RequestMapping(path="/deleteAll",
    method = RequestMethod.DELETE)
    public void deleteCartProductAll(@RequestParam(name ="userNum")Long userNum){

        cartService.deleteAllCart(userNum);
    }


    /**
     *
     * @apiNote    : 장바구니 userNum 으로 고객장바구니 목록 전체 삭제
     * @Url        : /cart/edit
     * @see        : entity Cart   DB: cart
     * @param      : Long cartNum 고객 번호
     *               Integer count 장바구니 상품의 변경된 수량
     * @return       Cart.java
     *
     */
    @RequestMapping(path="/edit",
    method = RequestMethod.PATCH)
    public Cart cartProductEdit(@RequestParam(name="cartProductNum")Long cartProductNum
            , @RequestParam(name="count")Integer count){
        if(cartProductNum !=null && count !=count) {
            return cartService.productCountUpdate(cartProductNum, count);
        }
        else
            return null;
    }
}
