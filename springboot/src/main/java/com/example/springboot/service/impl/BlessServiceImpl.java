package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Bless;
import com.example.springboot.mapper.BlessMapper;
import com.example.springboot.service.IBlessService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-27
 */
@Service
public class BlessServiceImpl extends ServiceImpl<BlessMapper, Bless> implements IBlessService {
    public IPage<Bless> findPage(Integer pageNum, Integer pageSize, String optionValue,String username){
        IPage<Bless> iPage = new Page<>(pageNum,pageSize);
        QueryWrapper<Bless> queryWrapper = new QueryWrapper<>();
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

    @Override
    public List<Bless> findByReceiver(String name) {
        QueryWrapper<Bless> queryWrapper = new QueryWrapper<>();
        return list(queryWrapper.eq("receiver",name));
    }

    @Override
    public List<Bless> findBySender(String name) {
        QueryWrapper<Bless> queryWrapper = new QueryWrapper<>();
        return list(queryWrapper.eq("sender",name));

    }
}
