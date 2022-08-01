package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.PasswordDTO;
import com.example.springboot.controller.dto.TeacherDTO;
import com.example.springboot.entity.Teacher;
import com.example.springboot.service.ITeacherService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-29
 */

@RestController
@Api(tags = "老师接口")
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private ITeacherService teacherService;

    @PostMapping("/save")
    public Result save(@RequestBody Teacher teacher) {
        return Result.success(teacherService.saveOrUpdate(teacher));
    }

    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(teacherService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(teacherService.removeByIds(ids));
    }

    @GetMapping("/all")
    public Result findAll() {
        return Result.success(teacherService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(teacherService.getById(id));
    }

    @GetMapping("/username/{username}")
    public Result findUser(@PathVariable String username) {
        return Result.success(teacherService.findUser(username));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "username") String optionValue,
                           @RequestParam(defaultValue = "") String username){
        return Result.success(teacherService.findPage(pageNum, pageSize, optionValue, username));
    }

    @PostMapping("/login")
    public Result login(@RequestBody TeacherDTO teacherDTO){
        String username = teacherDTO.getUsername();
        String password = teacherDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        return Result.success(teacherService.login(teacherDTO));
    }
    /**
     * 修改密码
     * @param passwordDTO 密码
     * @return
     */
    @PostMapping("/password")
    public Result password(@RequestBody PasswordDTO passwordDTO) {
        teacherService.updatePassword(passwordDTO);
        passwordDTO.setPassword(SecureUtil.md5(passwordDTO.getPassword()));
        passwordDTO.setNewPassword(SecureUtil.md5(passwordDTO.getNewPassword()));
        return Result.success();
    }
}

