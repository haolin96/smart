package com.mitesofor.smartsite.person.biz.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitesofor.smartsite.person.api.dto.PersonAgeStat;
import com.mitesofor.smartsite.person.api.dto.PersonDto;
import com.mitesofor.smartsite.person.api.dto.PersonJobStat;
import com.mitesofor.smartsite.person.api.entity.Person;
import com.mitesofor.smartsite.person.api.vo.PersonVO;
import com.mitesofor.smartsite.person.biz.mapper.PersonMapper;
import com.mitesofor.smartsite.person.biz.service.JobService;
import com.mitesofor.smartsite.person.biz.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    @Autowired
    public PersonMapper personMapper;

    @Autowired
    public JobService jobService;


    @Override
    public boolean save(PersonDto personDto) {
        personDto.setIsDelete(0);
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String createTime = dateFormat.format(date);
        personDto.setCreateTime(new Date());
        Person person = new Person();
        BeanUtils.copyProperties(personDto, person);
        personMapper.insert(person);

//        PersonLicense personLicense = new PersonLicense();
//        BeanUtils.copyProperties(personDto, personLicense);
//        personLicenseMapper.insert(personLicense);

        return true;
    }
    @Override
    public boolean update(PersonDto personDto) {
        Person person = new Person();
        BeanUtils.copyProperties(personDto, person);
        personMapper.update(person);
        return true;
    }
    @Override
    public boolean delete(Person person) {
        personMapper.delete(person);
        return true;
    }

    public boolean removeByPersonId(Integer id) {
        return personMapper.deleteByPrimaryKey(id);
    }

    public Person getByPersonId(Integer id) {
        return personMapper.selectByPrimaryKey(id);
    }


    public Page<PersonVO> getPersonListByPage(Long current, Long size, Person person) {
//        LambdaQueryWrapper<Person> wrapper = new LambdaQueryWrapper<>();
//
//        wrapper.like(StringUtils.isNotBlank(person.getPersonId()), Person::getPersonId, person.getPersonId());
//        wrapper.like(StringUtils.isNotBlank(person.getPersonName()), Person::getPersonName, person.getPersonName());
//        wrapper.like((person.getGender() != null), Person::getGender, person.getGender());
//        wrapper.like(StringUtils.isNotBlank(person.getEducation()), Person::getEducation, person.getEducation());
//        wrapper.like(StringUtils.isNotBlank(person.getJob()), Person::getJob, person.getJob());
//        wrapper.like(StringUtils.isNotBlank(person.getPhone()), Person::getPhone, person.getPhone());
//        wrapper.like(StringUtils.isNotBlank(person.getTeam()), Person::getTeam, person.getTeam());
//        wrapper.like(StringUtils.isNotBlank(person.getLocation()), Person::getLocation, person.getLocation());
//        wrapper.like(StringUtils.isNotBlank(person.getCreateTime()), Person::getCreateTime, person.getCreateTime());

//        wrapper.eq(Person::getIsDelete, '0');

        // 谁用 wrapper 谁是傻瓜
        Page<Person> page = new Page(current, size);
        Page<PersonVO> pageRes = personMapper.selectByPage(page, person);

        return pageRes;
    }


    public List<Map<String, String>> getByAge() {

        List<Person> personList = personMapper.selectAll();

        Map<String, Long> countPersonByAgeMap = personList.stream()
                .collect(Collectors.groupingBy(person -> {
                    int a = person.getAge();
                    if (a < 25){
                        return "25岁以下";
                    }
                    else if (a < 35){
                        return "25-35岁";
                    }
                    else if (a < 45){
                        return "35-45岁";
                    }
                    else if (a < 55){
                        return "45-55岁";
                    }
                    else {
                        return "55岁以上";
                    }
                },
                        Collectors.counting()));

        List<Map<String, String>> countPersonByAgeList = new ArrayList<>();

        for (String key: countPersonByAgeMap.keySet()){
            String value = countPersonByAgeMap.get(key).toString();
            HashMap<String, String> map = new HashMap<>();
            map.put("name", key);
            map.put("value", value);
            countPersonByAgeList.add(map);
        }
        return countPersonByAgeList;
    }


    public List<Map<String, String>> getByJob() {
//        PersonJobStat personJobStat = new PersonJobStat();

        List<Person> personList = personMapper.selectAll();

        Map<String, Long> countPersonByJobMap = personList.stream()
                .collect(Collectors.groupingBy(Person::getJob, Collectors.counting()));
//        HashMap<String, Integer> map = new HashMap<>();
//        List<String> joblist = jobService.loadAllJob();
//
//        for (String job: joblist) {
//            QueryWrapper<Person> wrapper = new QueryWrapper<>();
//            wrapper.eq("job", job);
//            Integer numberofpeople = personMapper.selectCount(wrapper);
//            map.put(job, numberofpeople);
//        }
//
//        personJobStat.setJob(map);
        List<Map<String, String>> countPersonByJobList = new ArrayList<>();

        for (String key: countPersonByJobMap.keySet()){
            String value = countPersonByJobMap.get(key).toString();
            HashMap<String, String> map = new HashMap<>();
            map.put("name", key);
            map.put("value", value);
            countPersonByJobList.add(map);
        }

        return countPersonByJobList;
    }

    public List<Map<String, String>> getByJobType(){
        List<Person> personList = personMapper.selectAll();

        Map<String, Long> countPersonByJobType = personList.stream()
                .collect(Collectors.groupingBy(Person::getJobType, Collectors.counting()));

        List<Map<String, String>> countPersonByJobTypeList = new ArrayList<>();

        for (String key: countPersonByJobType.keySet()) {
            String value = countPersonByJobType.get(key).toString();
            HashMap<String, String> map = new HashMap<>();
            map.put("name", key);
            map.put("value", value);
            countPersonByJobTypeList.add(map);
        }

        return countPersonByJobTypeList;
    }
    @Override
    public Integer countPerson() {
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        wrapper.select("Distinct person_id").eq("is_delete", 0);
        return personMapper.selectCount(wrapper);
    }

}
