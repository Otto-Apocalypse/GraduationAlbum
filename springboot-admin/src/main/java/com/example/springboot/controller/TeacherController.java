package com.example.springboot.controller;

import com.example.springboot.common.Result;
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
}

