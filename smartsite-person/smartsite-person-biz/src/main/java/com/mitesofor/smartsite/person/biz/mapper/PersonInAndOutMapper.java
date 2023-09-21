package com.mitesofor.smartsite.person.biz.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.person.api.dto.PersonInAndOutInfo;
import com.mitesofor.smartsite.person.api.entity.PersonInAndOut;
import com.mitesofor.smartsite.person.api.vo.PersonInAndOutVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface PersonInAndOutMapper extends BaseMapper<PersonInAndOut> {

    boolean update(PersonInAndOut personInAndOut);
    boolean deleteByPrimaryKey(Integer id);
    PersonInAndOut selectByPrimaryKey(Integer id);

    Page<PersonInAndOutVO> selectByPage(Page<PersonInAndOut> page,
                                        @Param("personInAndOut") PersonInAndOut personInAndOut,
                                        @Param("startTime") Date startTime, @Param("endTime") Date endTime);
//    List<PersonInAndOutInfo> selectPersonInfo(@Param("year") Integer year,
//                                         @Param("month") Integer month,
//                                         @Param("day") Integer day);
//    Integer selectSum(@Param("year") Integer year,
//                      @Param("month") Integer month,
//                      @Param("day") Integer day);
//
//    Integer selectCountByJobType(@Param("jobType") String jobType,
//                                 @Param("year") Integer year,
//                                 @Param("month") Integer month,
//                                 @Param("day") Integer day);
//
//    List<PersonInAndOut> selectByExample(@Param("year") Integer year,
//                                      @Param("month") Integer month,
//                                      @Param("day") Integer day);

    List<PersonInAndOut> selectBySevenDays();

    PersonInAndOutVO selectLatestIn();

    PersonInAndOutVO selectLatestOut();
}
