package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.AdminDTO;
import com.example.springboot.service.IAdminService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-31
 */

@RestController
@Api(tags = "管理员接口")
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody AdminDTO adminDTO){
        return Result.success(adminService.login(adminDTO));
    }
}

