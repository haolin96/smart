package com.mitesofor.smartsite.person.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitesofor.smartsite.person.api.dto.PersonAgeStat;
import com.mitesofor.smartsite.person.api.dto.PersonDto;
import com.mitesofor.smartsite.person.api.dto.PersonJobStat;
import com.mitesofor.smartsite.person.api.entity.Person;
import com.mitesofor.smartsite.person.api.vo.PersonVO;

import java.util.List;
import java.util.Map;

public interface PersonService extends IService<Person> {

    boolean save(PersonDto personDto);
    boolean update(PersonDto personDto);
    boolean delete(Person person);

    boolean removeByPersonId(Integer id);
    Person getByPersonId(Integer id);

    Page<PersonVO> getPersonListByPage(Long current, Long size, Person person);
    /**
     * 年龄统计
     * @return
     */
    List<Map<String, String>> getByAge();

    /**
     * 岗位统计
     * @return
     */
    List<Map<String, String>> getByJob();

    List<Map<String, String>> getByJobType();

    Integer countPerson();
}
