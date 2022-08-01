package com.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Bless;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-27
 */
public interface IBlessService extends IService<Bless> {
    IPage<Bless> findPage(Integer pageNum, Integer pageSize, String optionValue,String username);

    List<Bless> findByReceiver(String name);

    List<Bless> findBySender(String name);
}
