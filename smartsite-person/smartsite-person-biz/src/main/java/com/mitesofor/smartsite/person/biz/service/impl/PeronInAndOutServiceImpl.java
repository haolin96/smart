package com.mitesofor.smartsite.person.biz.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitesofor.smartsite.person.api.dto.PersonInAndOutInfo;
import com.mitesofor.smartsite.person.api.dto.PersonInAndOutStat;
import com.mitesofor.smartsite.person.api.entity.Person;
import com.mitesofor.smartsite.person.api.entity.PersonInAndOut;
import com.mitesofor.smartsite.person.api.vo.PersonInAndOutVO;
import com.mitesofor.smartsite.person.biz.mapper.PersonInAndOutMapper;
import com.mitesofor.smartsite.person.biz.mapper.PersonMapper;
import com.mitesofor.smartsite.person.biz.service.JobService;
import com.mitesofor.smartsite.person.biz.service.PersonInAndOutService;
import com.mitesofor.smartsite.person.biz.service.PersonService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PeronInAndOutServiceImpl extends ServiceImpl<PersonInAndOutMapper, PersonInAndOut> implements PersonInAndOutService {

    @Autowired
    public PersonInAndOutMapper personInAndOutMapper;

    @Autowired
    public JobService jobService;

    @Autowired
    public PersonMapper personMapper;

    @Autowired
    public PersonService personService;

    /**
     * 添加
     * @param personInAndOut
     * @return
     */
    public boolean save(PersonInAndOut personInAndOut) {
        if(personInAndOut.getInOrOutTime() == null){
            Date date = new Date();
            personInAndOut.setInOrOutTime(date);
        }
        if (this.checkRegistered(personInAndOut.getPersonId())) {
            personInAndOut.setIsRegistered(1);
            if (personInAndOut.getInOrOut() == 1 ){
                if (personInAndOut.getInOrOutTime().getHours() < 9) {
                    personInAndOut.setPresenceStatus(1);
                }
                else {
                    personInAndOut.setPresenceStatus(0);
                }
            }
            else {
                if (personInAndOut.getInOrOutTime().getHours() > 18) {
                    personInAndOut.setPresenceStatus(1);
                }
                else {
                    personInAndOut.setPresenceStatus(2);
                }
            }
        }
        else {
            personInAndOut.setIsRegistered(0);
        }


//        else {
//            PersonInAndOut personInAndOutOri = checkPersonToday(personInAndOut.getPersonId());
//            if (personInAndOutOri == null){
//                personInAndOut.setPresenceStatus(0);
//            }
//            else{
//                personInAndOut.setPresenceStatus(personInAndOutOri.getPresenceStatus());
//            }
//        }
        personInAndOut.setIsDelete(0);
        personInAndOutMapper.insert(personInAndOut);
        return true;
    }
    private PersonInAndOut checkPersonToday(String personId){
        QueryWrapper<PersonInAndOut> wrapper = new QueryWrapper<>();
        wrapper.eq("person_id", personId);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(date);
        wrapper.like("in_or_out_time", today);
        wrapper.orderByAsc("in_or_out_time");
        wrapper.last("limit 1");
        return personInAndOutMapper.selectOne(wrapper);
    }

    @Override
    public boolean update(PersonInAndOut personInAndOut) {
        return personInAndOutMapper.update(personInAndOut);
    }

    @Override
    public boolean removeById(Integer id) {
        return personInAndOutMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PersonInAndOut getById(Integer id) {
        return personInAndOutMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean checkRegistered(String personId) {
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        wrapper.eq("person_id", personId);
        if (personMapper.selectOne(wrapper) == null) {
            return false;
        }
        else {
            return true;
        }
    }
    @Override
    public Page<PersonInAndOutVO> getListByPage(Long current, Long size, PersonInAndOut personInAndOut, String startDate, String endDate) {
        Long currentIndex = (current - 1) * size;
        Date startTime = null;
        Date endTime = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (startDate != null && startDate != ""){
            String startTimeTmp = startDate + " 00:00:00";
            try {
                startTime = formatter.parse(startTimeTmp);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        if (endDate != null && endDate != ""){
            String endTimeTmp = endDate + " 23:59:59";
            try{
                endTime = formatter.parse(endTimeTmp);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        Page<PersonInAndOut> page = new Page<>(current, size);
        Page<PersonInAndOutVO> pageRes = personInAndOutMapper.selectByPage(page, personInAndOut, startTime, endTime);

        return pageRes;
    }

    private List<PersonInAndOut> getListToday() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(date);

        QueryWrapper<PersonInAndOut> wrapper = new QueryWrapper<>();
        wrapper.like("in_or_out_time", today);

        return personInAndOutMapper.selectList(wrapper);
    }
    @Override
    public Map<String, Long> countInAndOutToday() {

        List<PersonInAndOut> personInAndOutList = this.getListToday();

        Long personIn = personInAndOutList.stream().filter(a -> a.getInOrOut().equals(1)).count();
        Long personOut = personInAndOutList.stream().filter(a -> a.getInOrOut().equals(0)).count();
        Long personOnsite = personIn - personOut;
        Long personPresence = personInAndOutList.stream()
                .filter(a -> a.getInOrOut().equals(1))
                .filter(a -> a.getIsRegistered().equals(1))
                .filter(distinctbyKey(PersonInAndOut::getPersonId))
                .count();

        Map<String, Long> resMap = new HashMap<>();
        Long personRegistered = personService.countPerson().longValue();
        Long personUnregistered = personInAndOutList.stream()
                .filter(a -> a.getInOrOut().equals(1))
                .filter(a -> a.getIsRegistered().equals(0))
                .count();

        resMap.put("registeredPerson", personRegistered);
        resMap.put("in", personIn);
        resMap.put("out", personOut);
        resMap.put("onsite", personOnsite);
        resMap.put("presence", personPresence);
        resMap.put("unregisteredPersonIn", personUnregistered);
        return resMap;
    }



    /**
     * 内部方法，按字段去重
     * @param keyExtractor
     * @return
     * @param <T>
     */
    private static <T> Predicate<T> distinctbyKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> map = new HashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }



    /**
     * 按小时统计进出人数和在场人数
     * @return
     */
    @Override
    public Map<Integer, Map<String, Long>> countInAndOutByHour() {

        List<PersonInAndOut> personInAndOutListToday = this.getListToday();

        Map<Integer, List<PersonInAndOut>> personInAndOutListMap = personInAndOutListToday.stream()
                .collect(Collectors.groupingBy(a -> a.getInOrOutTime().getHours()));

        Map<Integer, Map<String, Long>> countInAndOutMap = new HashMap<>();
        Long countOnsite = 0L;
        for (int hour = 0; hour < 24; hour++) {
            List<PersonInAndOut> personList = personInAndOutListMap.getOrDefault(hour, new ArrayList<>());
            Long countIn = personList.stream()
                    .filter(a -> a.getInOrOut().equals(1))
                    .count();
            Long countOut = personList.stream()
                    .filter(a -> a.getInOrOut().equals(0))
                    .count();

            countOnsite += countIn - countOut;
            if (countOnsite < 0){
                countOnsite = 0L;
            }
            Map<String, Long> InAndOutMap = new HashMap<>();
            InAndOutMap.put("in", countIn);
            InAndOutMap.put("out", countOut);
            InAndOutMap.put("onsite", countOnsite);


            countInAndOutMap.put(hour, InAndOutMap);
        }

        return countInAndOutMap;
    }

    public Map<String, Long> countPresenceBySevenDays(){
        List<PersonInAndOut> personInAndOutList = personInAndOutMapper.selectBySevenDays();
        Map<String, List<PersonInAndOut>> personInAndOutMap = personInAndOutList.stream()
                .filter(distinctbyKey(PersonInAndOut::getPersonId))
                .collect(Collectors.groupingBy(a -> DateUtil.format(a.getInOrOutTime(), "yyyy-MM-dd")));

        Map<String, Long> presenceCountMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (String key: personInAndOutMap.keySet()) {
            List<PersonInAndOut> personPresenceList = personInAndOutMap.get(key);
            Long presenceCount = personPresenceList.stream()
                    .filter(distinctbyKey(PersonInAndOut::getPersonId))
                    .count();
            presenceCountMap.put(key, presenceCount);
        }

        return presenceCountMap;
    }

    public PersonInAndOutVO getLatestIn(){
        return personInAndOutMapper.selectLatestIn();
    }

    public PersonInAndOutVO getLatestOut(){
        return personInAndOutMapper.selectLatestOut();
    }

}
