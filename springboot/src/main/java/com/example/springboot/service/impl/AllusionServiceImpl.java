package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Allusion;
import com.example.springboot.entity.Bless;
import com.example.springboot.mapper.AllusionMapper;
import com.example.springboot.service.IAllusionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-30
 */
@Service
public class AllusionServiceImpl extends ServiceImpl<AllusionMapper, Allusion> implements IAllusionService {

    @Override
    public List<Allusion> getByName(String name) {
        QueryWrapper<Allusion> queryWrapper = new QueryWrapper<>();
        return list(queryWrapper.eq("teacher",name));
    }

    @Override
    public IPage<Allusion> findPage(Integer pageNum, Integer pageSize, String optionValue, String username){
        IPage<Allusion> iPage = new Page<>(pageNum,pageSize);
        QueryWrapper<Allusion> queryWrapper = new QueryWrapper<>();
        switch (optionValue){
            case "sender":
                queryWrapper.like("sender",username);
                break;
            case "receiver":
                queryWrapper.like("receiver",username);
                break;
            case "content":
                queryWrapper.like("content",username);
                break;
        }
        return page(iPage,queryWrapper);
    }
}
