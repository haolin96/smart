package com.mitesofor.smartsite.person.biz.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.common.core.util.R;
import com.mitesofor.smartsite.person.api.dto.PersonDto;
import com.mitesofor.smartsite.person.api.entity.Person;
import com.mitesofor.smartsite.person.api.vo.PersonVO;
import com.mitesofor.smartsite.person.biz.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@Api(value = "人员信息", tags = "人员信息管理", description = "统计人员信息")
//@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class PersonController {

    @Autowired
    public PersonService personService;

    /**
     * 增删改查
     * @param personDto
     * @return
     */
    @ApiOperation(value = "添加人员信息", notes = "添加人员信息")
    @PostMapping
    public R save(@RequestBody PersonDto personDto){
        try{
            boolean res = personService.save(personDto);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    @ApiOperation(value = "修改人员信息", notes = "修改人员信息")
    @PutMapping
    public R update(@RequestBody PersonDto personDto){
        try{
            boolean res = personService.update(personDto);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    @ApiOperation(value = "删除人员信息", notes = "删除人员信息")
    @DeleteMapping("/{id}")
    public R removeByPersonId(@PathVariable Integer id){
        try{
            boolean res = personService.removeByPersonId(id);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }


    @ApiOperation(value = "查询人员信息", notes = "查询人员信息（根据personId）")
    @GetMapping("/{id}")
    public R getByPersonId(@PathVariable Integer id){
        Assert.notNull(id, "personId不能为空");
        try{
            Person res = personService.getByPersonId(id);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    @ApiOperation(value = "查询人员信息列表", notes = "查询人员信息列表(分页)")
    @GetMapping("/page")
    public R getPersonListByPage(Long current, Long size, Person person){
        try{
            Page<PersonVO> res = personService.getPersonListByPage(current, size, person);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }
}
