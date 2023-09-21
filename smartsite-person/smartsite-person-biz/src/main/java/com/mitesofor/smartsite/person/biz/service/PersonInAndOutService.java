package com.mitesofor.smartsite.person.biz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitesofor.smartsite.person.api.dto.PersonInAndOutInfo;
import com.mitesofor.smartsite.person.api.dto.PersonInAndOutStat;
import com.mitesofor.smartsite.person.api.entity.Person;
import com.mitesofor.smartsite.person.api.entity.PersonInAndOut;
import com.mitesofor.smartsite.person.api.vo.PersonInAndOutVO;
import com.mitesofor.smartsite.person.api.vo.PersonVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PersonInAndOutService extends IService<PersonInAndOut> {

    boolean save(PersonInAndOut personInAndOut);
    boolean update(PersonInAndOut personInAndOut);

    boolean removeById(Integer id);
    PersonInAndOut getById(Integer id);

    Page<PersonInAndOutVO> getListByPage(Long current, Long size, PersonInAndOut personInAndOut, String startDate, String endDate);

    boolean checkRegistered(String personId);

    Map<String, Long> countInAndOutToday();

    /**
     * 按小时统计进出人数和在场人数
     * @return
     */

    Map<Integer, Map<String, Long>> countInAndOutByHour();

    Map<String, Long> countPresenceBySevenDays();

    PersonInAndOutVO getLatestIn();

    PersonInAndOutVO getLatestOut();
}
