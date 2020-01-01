package com.example.genaration.controller;

import com.example.genaration.entity.Information;
import com.example.genaration.service.InformationService;
import com.example.genaration.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author 叶超(Chace)-01387009
 * @version V1.0
 * @Program: air-commom
 * @Description:
 * @Create 2019/12/31 14:08
 */
@RestController
@RequestMapping("info")
public class InformationController {
    @Autowired
    private InformationService informationService;

    @ResponseBody
    @PostMapping(value = "/post")
    public ServerResponse postGrow(@RequestBody Information entity) throws Exception {
        entity.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        System.out.println(entity.getAppname());
        if (informationService.insert(entity) == 1) {
            return new ServerResponse("200", "成功");
        } else {
            return new ServerResponse("500", "失败");
        }
    }
}
