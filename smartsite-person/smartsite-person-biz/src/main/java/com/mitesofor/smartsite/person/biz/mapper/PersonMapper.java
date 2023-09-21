package com.mitesofor.smartsite.person.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.person.api.dto.PersonDto;
import com.mitesofor.smartsite.person.api.entity.Person;
import com.mitesofor.smartsite.person.api.vo.PersonVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {

    boolean update(Person person);
    boolean delete(Person person);

    boolean deleteByPrimaryKey(@Param("id") Integer id);
    Person selectByPrimaryKey(@Param("id") Integer id);
    Person selectByExample(Person person);

    List<Person> selectAll();

    Page<PersonVO> selectByPage(Page<Person> page, @Param("person")Person person);
}
