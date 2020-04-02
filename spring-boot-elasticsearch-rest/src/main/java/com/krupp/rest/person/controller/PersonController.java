package com.krupp.rest.person.controller;

import com.alibaba.fastjson.JSONObject;
import com.krupp.rest.person.model.SearchParam;
import com.krupp.rest.person.service.PersonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/search")
    @ApiOperation("根据id查询ES对应的数据")
    public JSONObject getDataById(@RequestBody SearchParam param) {
        return personService.getDataById(param);
    }

    @PostMapping("/add")
    @ApiOperation("往ES里插入数据")
    public ResponseEntity add(@RequestBody SearchParam param) {
        return personService.add(param);
    }

    @PostMapping("/update")
    @ApiOperation("根据id更新文档内容")
    public ResponseEntity update(@RequestBody SearchParam param) {
        return personService.update(param);
    }

    @PostMapping("/delete")
    @ApiOperation("根据id更新文档内容")
    public ResponseEntity delete(@RequestBody SearchParam param) {
        return personService.delete(param);
    }


}
