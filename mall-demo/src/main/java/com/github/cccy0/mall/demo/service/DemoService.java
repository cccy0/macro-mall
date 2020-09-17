package com.github.cccy0.mall.demo.service;

import com.github.cccy0.mall.demo.dto.PmsBrandDto;
import com.github.cccy0.mall.model.PmsBrand;

import java.util.List;

/**
 * DemoService接口
 * @author cy
 */
public interface DemoService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrandDto pmsBrandDto);

    int updateBrand(Long id, PmsBrandDto pmsBrandDto);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
