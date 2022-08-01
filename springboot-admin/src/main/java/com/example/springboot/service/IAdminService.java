package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.controller.dto.AdminDTO;
import com.example.springboot.entity.Admin;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-31
 */
public interface IAdminService extends IService<Admin> {

    AdminDTO login(AdminDTO adminDTO);
}
