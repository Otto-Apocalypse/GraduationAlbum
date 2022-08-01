package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Allusion;
import com.example.springboot.service.IAllusionService;
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
 * @since 2022-07-30
 */

@RestController
@Api(tags = "典故接口")
@RequestMapping("/allusion")
public class AllusionController {

    @Resource
    private IAllusionService allusionService;

    @PostMapping("/save")
    public Result save(@RequestBody Allusion allusion) {
        return Result.success(allusionService.saveOrUpdate(allusion));
    }

    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(allusionService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(allusionService.removeByIds(ids));
    }

    @GetMapping("/teacher/{name}")
    public Result getByName(@PathVariable String name){
        return Result.success(allusionService.getByName(name));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "username") String optionValue,
                           @RequestParam(defaultValue = "") String username){
        return Result.success(allusionService.findPage(pageNum, pageSize, optionValue, username));
    }
}

