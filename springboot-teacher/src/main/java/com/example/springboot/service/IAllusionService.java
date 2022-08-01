package com.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Allusion;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-30
 */
public interface IAllusionService extends IService<Allusion> {
    List<Allusion> getByName(String name);

    IPage<Allusion> findPage(Integer pageNum, Integer pageSize, String optionValue, String username);
}
