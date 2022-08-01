package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Bless;
import com.example.springboot.service.IBlessService;
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
 * @since 2022-07-27
 */

@RestController
@Api(tags = "赠语接口")
@RequestMapping("/bless")
public class BlessController {

    @Resource
    private IBlessService blessService;

    @PostMapping("/save")
    public Result save(@RequestBody Bless bless) {
        return Result.success(blessService.saveOrUpdate(bless));
    }

    @DeleteMapping("/del/{id}")
    public Result deleteByReceiver(@PathVariable int id) { return Result.success(blessService.removeById(id)); }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(blessService.removeByIds(ids));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(blessService.getById(id));
    }

    @GetMapping("/receiver/{name}")
    public Result findByReceiver(@PathVariable String name) { return Result.success(blessService.findByReceiver(name)); }

    @GetMapping("/sender/{name}")
    public Result findBySender(@PathVariable String name) { return Result.success(blessService.findBySender(name)); }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "receiver") String optionValue,
                           @RequestParam(defaultValue = "") String username){
        return Result.success(blessService.findPage(pageNum, pageSize, optionValue, username));
        }
}

