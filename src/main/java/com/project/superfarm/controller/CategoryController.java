package com.project.superfarm.controller;


import com.project.superfarm.entity.UpperCategory;
import com.project.superfarm.service.CategoryServiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


    /**
     * @apiNote :  카테고리
     * URL /category :  /category
     * @brief : 카테고리의 내용 전체 리턴
     **/

@RequestMapping("/category")
@RestController
public class CategoryController {


    @Autowired
    private CategoryServiece categoryServiece;


    /**
     * @apiNote : 카테고리 전체를 리턴 upper 상위카테고리 / lower 하위카테
     * },고리
     * * @Url        : /category
     * * @see        : entity Cart   DB: cart
     * * @return     Json
     * *         "upperCode": 1,
     * *         "upperTitle": "농산물",
     * *         "lowerCategory": [
     * *             {
     * *                 "lowerCode": 1,
     * *                 "lowerTitle": "채소"
     * *             },
     * *             {
     * *                 "lowerCode": 2,
     * *                 "lowerTitle": "쌀"
     * *             },
     * *             {
     * *                 "lowerCode": 3,
     * *                 "lowerTitle": "과일"
     * {
     * "lowerCode": 4,
     * "lowerTitle": "야채"
     * }
     * ],
     * upperCode.
     * <p>
     * },
     * `http://localhost:8080/product/lower?lower=${lower.num}`
     */
    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @RequestMapping(
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_ATOM_XML_VALUE
            })
    public List<UpperCategory> loadAll() {

        return categoryServiece.findall();

    }


}
