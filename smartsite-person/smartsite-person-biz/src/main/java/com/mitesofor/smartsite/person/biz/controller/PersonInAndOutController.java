package com.mitesofor.smartsite.person.biz.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.common.core.util.R;
import com.mitesofor.smartsite.person.api.dto.PersonInAndOutInfo;
import com.mitesofor.smartsite.person.api.dto.PersonInAndOutStat;
import com.mitesofor.smartsite.person.api.entity.Person;
import com.mitesofor.smartsite.person.api.entity.PersonInAndOut;
import com.mitesofor.smartsite.person.api.vo.PersonInAndOutVO;
import com.mitesofor.smartsite.person.biz.service.PersonInAndOutService;
import com.mitesofor.smartsite.person.biz.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personInAndOut")
@Api(value = "人员进出信息", tags = "人员进出信息管理（大屏）", description = "统计人员进出信息")
public class PersonInAndOutController {

    @Autowired
    public PersonInAndOutService personInAndOutService;

    @Autowired
    public PersonService personService;

    /**
     * 新增出入信息
     * @param personInAndOut
     * @return
     */
    @ApiOperation(value = "新增出入场信息", notes = "新增出入场信息")
    @PostMapping
    public R save(@RequestBody PersonInAndOut personInAndOut) {
        Assert.notBlank(personInAndOut.getPersonId(), "请指定人员Id");
        Assert.notNull(personInAndOut.getInOrOut(), "请指定出/入场");

        try {
            boolean res = personInAndOutService.save(personInAndOut);
            return R.ok(res,"success");
        } catch (Exception e) {
            return R.failed(e, "fail");
        }
    }

    @ApiOperation(value = "修改出入场信息", notes = "修改出入场信息")
    @PutMapping
    public R update(@RequestBody PersonInAndOut personInAndOut) {
        Assert.notBlank(personInAndOut.getId(), "请指定Id");
        try {
            boolean res = personInAndOutService.update(personInAndOut);
            return R.ok(res,"success");
        } catch (Exception e) {
            return R.failed(e, "fail");
        }
    }
    @ApiOperation(value = "删除出入场信息", notes = "删除出入场信息")
    @DeleteMapping
    public R removeById(@PathVariable Integer id) {
        try {
            boolean res = personInAndOutService.removeById(id);
            return R.ok(res,"success");
        } catch (Exception e) {
            return R.failed(e, "fail");
        }
    }
    @ApiOperation(value = "查询出入场信息", notes = "查询出入场信息")
    @GetMapping
    public R getById(@PathVariable Integer id) {
        try {
            PersonInAndOut res = personInAndOutService.getById(id);
            return R.ok(res,"success");
        } catch (Exception e) {
            return R.failed(e, "fail");
        }
    }

    @ApiOperation(value = "查询出入场信息(分页)", notes = "查询出入场信息(分页)")
    @GetMapping("/page")
    public R getByPage(Long current, Long size, PersonInAndOut personInAndOut, String startDate, String endDate) {
        try {
            Page<PersonInAndOutVO> res = personInAndOutService.getListByPage(current, size, personInAndOut, startDate, endDate);
            return R.ok(res,"success");
        } catch (Exception e) {
            return R.failed(e, "fail");
        }
    }


    /**
     * 年龄统计
     *
     *
     */
    @ApiOperation(value = "统计人员年龄", notes = "统计人员年龄（大屏展示）")
    @GetMapping("/getByAge")
    public R getByAge(){
        R res;
        try{
            res = R.ok(personService.getByAge(), "success");
        }catch(Exception e){
            res = R.failed(e, "failed");
        }
        return res;
    }

    /**
     *
     * 岗位统计
     *
     */
    @ApiOperation(value = "统计人员岗位", notes = "统计人员岗位（大屏展示）")
    @GetMapping("/getByJob")
    public R getByJob(){
        R res;
        try{
            res = R.ok(personService.getByJob(), "success");
        }catch(Exception e){
            res = R.failed(e, "failed");
        }
        return res;
    }

    @ApiOperation(value = "统计人员岗位类型", notes = "统计人员岗位类型（大屏展示）")
    @GetMapping("/getByJobType")
    public R getByJobType(){
        R res;
        try{
            res = R.ok(personService.getByJobType(), "success");
        }catch(Exception e){
            res = R.failed(e, "failed");
        }
        return res;
    }

//    /**
//     * 显示实时出入场人员信息
//     * @param personInAndOut
//     * @return
//     */
//    @ApiOperation(value = "显示实时出入场人员信息", notes = "显示实时出入场人员信息")
//    @GetMapping("loadBatches")
//    public R loadBatches(@RequestBody PersonInAndOut personInAndOut){
//        try{
//            List<PersonInAndOutInfo> res = personInAndOutService.loadBatches(personInAndOut);
//            return R.ok(res, "success");
//        }catch(Exception e){
//            return R.failed(e, "failed");
//        }
//    }
//
//    /**
//     * 门禁统计，按天，出/入场
//     * @param personInAndOut
//     * @return
//     */
//    @ApiOperation(value = "门禁统计（按天）", notes = "门禁统计（按天）")
//    @GetMapping("/countInAndOut")
//    public R countInAndOut(@RequestBody PersonInAndOut personInAndOut) {
//        Assert.notNull(personInAndOut.getYear(), "请指定年份");
//        Assert.notNull(personInAndOut.getMonth(), "请指定月份");
//        Assert.notNull(personInAndOut.getDay(), "请指定日期");
//        Assert.notNull(personInAndOut.getInOrOut(), "请指定出/入场");
//
//        try {
//            Integer res = personInAndOutService.countInandOut(personInAndOut);
//            return R.ok(res,"success");
//        } catch (Exception e) {
//            return R.failed("failed");
//        }
//    }
//
//
//    /**
//     * 出勤统计，按天
//     * @param personInAndOut
//     * @return
//     */
//    @ApiOperation(value = "出勤统计", notes = "出勤统计")
//    @GetMapping("/countpresence")
//    public R countPresence(@RequestBody PersonInAndOut personInAndOut) {
//        Assert.notNull(personInAndOut.getYear(), "请指定年份");
//        Assert.notNull(personInAndOut.getMonth(), "请指定月份");
//        Assert.notNull(personInAndOut.getDay(), "请指定日期");
//        try {
//            Integer res = personInAndOutService.countPresence(personInAndOut);
//            return R.ok(res, "success");
//        } catch (Exception e) {
//            return R.failed(e, "failed");
//        }
//    }
//
//    /**
//     * 统计在场人数
//     * @return
//     */
//    @ApiOperation(value = "统计在场人数", notes = "统计在场人数")
//    @GetMapping("/countOnsitePeople")
//    public R countOnsitePeople(@RequestBody PersonInAndOut personInAndOut) {
//        Assert.notNull(personInAndOut.getYear(), "请指定年份");
//        Assert.notNull(personInAndOut.getMonth(), "请指定月份");
//        Assert.notNull(personInAndOut.getDay(), "请指定日期");
//        try {
//            Integer res = personInAndOutService.countOnsitePeople(personInAndOut);
//            return R.ok(res,"success");
//        } catch (Exception e) {
//            return R.failed(e,"failed");
//        }
//    }
//
//    /**
//     * 按岗位类型统计出勤人数
//     * @param personInAndOut
//     * @return
//     */
//    @ApiOperation(value = "统计出勤人数（按岗位类型）", notes = "统计出勤人数（按岗位类型）")
//    @GetMapping("/countByJobType")
//    public R countByJobType(@RequestBody PersonInAndOut personInAndOut) {
//        Assert.notNull(personInAndOut.getYear(), "请指定年份");
//        Assert.notNull(personInAndOut.getMonth(), "请指定月份");
//        Assert.notNull(personInAndOut.getDay(), "请指定日期");
//        try {
//            PersonInAndOutStat res = personInAndOutService.countByJobType(personInAndOut);
//            return R.ok(res,"success");
//        } catch (Exception e) {
//            return R.failed(e, "failed");
//        }
//    }

    /**
     * 统计当天进出场和出勤人数
     * @return
     */
    @ApiOperation(value = "统计在册人数，今天进出场人数，今天出勤人数", notes = "统计在册人数，今天进出场人数，今天出勤人数")
    @GetMapping("/countInAndOutToday")
    public R countInAndOutToday() {
        try {
            Map<String, Long> res = personInAndOutService.countInAndOutToday();
            return R.ok(res, "success");
        } catch(Exception e) {
            return R.failed(e, "failed");
        }
    }

    /**
     * 按小时统计进出人数
     * @return
     */
    @ApiOperation(value = "按小时统计今天进出场人数", notes = "按小时统计今天进出场人数")
    @GetMapping("/countInAndOutByHour")
    public R countInAndOutByHour() {
        try {
            Map<Integer, Map<String, Long>> res = personInAndOutService.countInAndOutByHour();
            return R.ok(res, "success");
        } catch(Exception e) {
            return R.failed(e, "failed");
        }
    }

    @ApiOperation(value = "统计七天出勤人数", notes = "统计七天出勤人数")
    @GetMapping("/countPresenceBySevenDays")
    public R countPresenceBySevenDays() {
        try {
            Map<String, Long> res = personInAndOutService.countPresenceBySevenDays();
            return R.ok(res, "success");
        } catch(Exception e) {
            return R.failed(e, "failed");
        }
    }

    @ApiOperation(value = "最新进场人员", notes = "最新进场人员")
    @GetMapping("/getLatestIn")
    public R getLatestIn() {
        try {
            PersonInAndOutVO res = personInAndOutService.getLatestIn();
            return R.ok(res, "success");
        } catch(Exception e) {
            return R.failed(e, "failed");
        }
    }

    @ApiOperation(value = "最新出场人员", notes = "最新出场人员")
    @GetMapping("/getLatestOut")
    public R getLatestOut() {
        try {
            PersonInAndOutVO res = personInAndOutService.getLatestOut();
            return R.ok(res, "success");
        } catch(Exception e) {
            return R.failed(e, "failed");
        }
    }
}
