package com.example.elevator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

@RestController
public class CommunityController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/Community")
    public Object say1(){
        String query = "select distinct CommunityName, Communityid from Community";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/wg/{s1}")
    public Object say21(@PathVariable(value = "s1") String s1){
        String query = "select distinct wgName, wgContact from Community where CommunityName = '" + s1 + "'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/wg/{s1}/{s2}/{s3}")
    public boolean say22(@PathVariable(value = "s1") String s1, @PathVariable(value = "s2") String s2, @PathVariable(value = "s3") String s3){
        String query = "update Community set wgName = '" + s2 + "', wgContact = '" + s3 + "' where CommunityName = '" + s1 + "'";
        return jdbcTemplate.update(query) > 0;
    }

    @GetMapping("/CommunityId/{s1}")
    public Object getCommunityId(@PathVariable(value = "s1") String s1){
        String query = "select distinct CommunityId from Community where CommunityName = '" + s1 + "'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/{s1}/Community")
    public Object say2(@PathVariable(value = "s1") String s1){
        String query = "select distinct CommunityName, Communityid from Community where sqName = '" + s1 + "'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/{s1}/Elevator")
    public Object say3(@PathVariable(value = "s1") String s1){
        String query = "select distinct ElevatorName, ElevatorId from Community where CommunityName = '" + s1 + "'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/{s1}/Elevator1")
    public Object say18(@PathVariable(value = "s1") String s1){
        String query = "select distinct ElevatorName, ElevatorId from Community where sqName = '" + s1 + "'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/SearchByDate/{s1}/{id1}")
    public Object say4(@PathVariable(value = "s1") String s1, @PathVariable(value = "id1") Integer id1){
        String query = "SELECT * FROM Warning where DATE_SUB(CURDATE(), INTERVAL " + id1 + " DAY) <= date(DateTime) and CommunityName = '" + s1 + "'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/SearchDate/{s1}/{id1}")
    public Object say17(@PathVariable(value = "s1") String s1, @PathVariable(value = "id1") Integer id1){
        String query = "SELECT * FROM Warning where DATE_SUB(CURDATE(), INTERVAL " + id1 + " DAY) <= date(DateTime) and Sq_Name = '" + s1 + "'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/SearchTop10/{s1}")
    public Object say5(@PathVariable(value = "s1") String s1){
        String query = "select * from WarningTime WHERE CommunityName = '" + s1 + "' ORDER BY DateTime desc LIMIT 10";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }
    @GetMapping("/SearchTop10/{s1}/{s2}")
    public Object say15(@PathVariable(value = "s1") String s1, @PathVariable(value = "s2") String s2){
        String query = "select * from WarningTime WHERE CommunityName = '" + s1 + "'and ElevatorName = '" +s2 + "' ORDER BY DateTime desc LIMIT 10";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/SearchByWarning/{s1}")
    public Object say36(@PathVariable(value = "s1") String s1){
        String query = "select * from WarningTime WHERE CommunityName = '" + s1 + "'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/SearchByWarning/{s1}/{id1}/{id2}")
    public Object say16(@PathVariable(value = "s1") String s1, @PathVariable(value = "id1") Integer id1, @PathVariable(value = "id2") Integer id2){
        String query = "select * from WarningTime WHERE CommunityName = '" + s1 + "' ORDER BY DateTime DESC limit " + (id1 - 1) * id2 + ", " + id2;
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/SearchByWarning/{s1}/{s2}/{s3}/{s4}")
    public Object say6(@PathVariable(value = "s1") String s1, @PathVariable(value = "s2") String s2, @PathVariable(value = "s3") String s3, @PathVariable(value = "s4") String s4){
        String startDate = "", endDate = "";
        try {
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            Date t;
            t = ft.parse(s4);
            startDate = ft.format(t);
            Calendar c = Calendar.getInstance();
            c.setTime(t);
            c.add(Calendar.DAY_OF_MONTH, 1);
            t = c.getTime();
            endDate = ft.format(t);
        } catch (ParseException e) {
        }
        String query = "select * from WarningTime WHERE CommunityName = '" + s1 + "' and ElevatorName = '" + s2 + "' and WarningType = '" + s3 + "' and DateTime > '" + startDate + "' and DateTime < '" + endDate + "'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/SearchByWarning/{s1}/{s2}/{s3}/{s4}/{id2}/{id3}")
    public Object say7(@PathVariable(value = "s1") String s1, @PathVariable(value = "s2") String s2, @PathVariable(value = "s3") String s3, @PathVariable(value = "s4") String s4, @PathVariable(value = "id2") Integer id2, @PathVariable(value = "id3") Integer id3){
        String startDate = "", endDate = "";
        try {
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            Date t;
            t = ft.parse(s4);
            startDate = ft.format(t);
            Calendar c = Calendar.getInstance();
            c.setTime(t);
            c.add(Calendar.DAY_OF_MONTH, 1);
            t = c.getTime();
            endDate = ft.format(t);
        } catch (ParseException e) {
        }
        String query = "select * from WarningTime WHERE CommunityName = '" + s1 + "' and ElevatorName = '" + s2 + "' and WarningType = '" + s3 + "' and DateTime > '" + startDate + "' and DateTime < '" + endDate + "' ORDER BY DateTime DESC limit " + (id2 - 1) * id3 + ", " + id3;
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/SearchDate/{s1}/{s2}/{s3}")
    public Object say27(@PathVariable(value = "s1") String s1, @PathVariable(value = "s2") String s2, @PathVariable(value = "s3") String s3){
        String startDate = "", endDate = "";
        try {
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            Date t1, t2;
            t1 = ft.parse(s2);
            startDate = ft.format(t1);
            t2 = ft.parse(s3);
            endDate = ft.format(t2);
        } catch (ParseException e) {
        }
        String query = "SELECT * FROM Warning where Sq_Name = '" + s1 + "' and DateTime > '" + startDate + "' and DateTime < '" + endDate + "'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/SearchDate1/{s1}/{s2}/{s3}")
    public Object say30(@PathVariable(value = "s1") String s1, @PathVariable(value = "s2") String s2, @PathVariable(value = "s3") String s3){
        String startDate = "", endDate = "";
        try {
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            Date t1, t2;
            t1 = ft.parse(s2);
            startDate = ft.format(t1);
            t2 = ft.parse(s3);
            endDate = ft.format(t2);
        } catch (ParseException e) {
        }
        String query = "SELECT * FROM Warning where CommunityName = '" + s1 + "' and DateTime > '" + startDate + "' and DateTime < '" + endDate + "'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/SearchSum/{s1}/{s4}/{s2}/{s3}")
    public Object say8(@PathVariable(value = "s1") String s1, @PathVariable(value = "s4") String s4, @PathVariable(value = "s2") String s2, @PathVariable(value = "s3") String s3){
        String startDate = "", endDate = "";
        try {
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            Date t1, t2;
            t1 = ft.parse(s2);
            startDate = ft.format(t1);
            t2 = ft.parse(s3);
            endDate = ft.format(t2);
        } catch (ParseException e) {
        }
        String query;
        switch(s4){
            case "电动车入梯":
                query = "select CommunityName, sum(ElectricBicycleWarning) as warnsum from Warning WHERE Sq_Name = '" + s1 + "' and DateTime > '" + startDate + "' and DateTime < '" + endDate + "' GROUP BY CommunityName";
                break;
            case "未戴口罩入梯":
                query = "select CommunityName, sum(MaskWarning) as warnsum from Warning WHERE Sq_Name = '" + s1 + "' and DateTime > '" + startDate + "' and DateTime < '" + endDate + "' GROUP BY CommunityName";
                break;
            case "电动车电池入梯":
                query = "select CommunityName, sum(BatteryWarning) as warnsum from Warning WHERE Sq_Name = '" + s1 + "' and DateTime > '" + startDate + "' and DateTime < '" + endDate + "' GROUP BY CommunityName";
                break;
            default :
                query = "";
                break;
        }
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/SearchSum/{s1}")
    public Object say28(@PathVariable(value = "s1") String s1){
        String query = "select CommunityName, sum(WarningSum) as warnsum from Warning WHERE Sq_Name = '" + s1 + "' GROUP BY CommunityName";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/users")
    public Object say29(){
        String query = "select * from Users where Position <> '超管'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }
    @GetMapping("/users/{id1}/{id2}")
    public Object say9(@PathVariable(value = "id1") Integer id1, @PathVariable(value = "id2") Integer id2){
        String query = "select * from Users where Position <> '超管' limit " + (id1 - 1) * id2 + ", " + id2 ;
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/name/{s1}")
    public Object say12(@PathVariable(value = "s1") String s1){
        String query = "select Principal,Contact from Users WHERE UserName = '" + s1 + "'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
        return list;
    }

    @GetMapping("/add/{s1}/{s2}/{s3}/{s4}/{s5}/{s6}/{s7}")
    public boolean say10(@PathVariable(value = "s1") String s1, @PathVariable(value = "s2") String s2, @PathVariable(value = "s3") String s3, @PathVariable(value = "s4") String s4, @PathVariable(value = "s5") String s5, @PathVariable(value = "s6") String s6, @PathVariable(value = "s7") String s7){
        String query = "insert into Users(UserName, PassWord, Position, Area, Organization, Principal, Contact) VALUES('" + s1 + "', '" +  s2 +  "', '" + s3 + "', '" +  s4 + "', '" + s5 + "', '" +  s6 + "', '" + s7 + "')";
        return jdbcTemplate.update(query) > 0;
    }
    @GetMapping("/edit/{s1}/{s2}/{s3}/{s4}/{s5}/{s6}/{s7}")
    public boolean say40(@PathVariable(value = "s1") String s1, @PathVariable(value = "s2") String s2, @PathVariable(value = "s3") String s3, @PathVariable(value = "s4") String s4, @PathVariable(value = "s5") String s5, @PathVariable(value = "s6") String s6, @PathVariable(value = "s7") String s7){
        String query = "update Users set PassWord = '" + s2 + "', Position = '" + s3 + "', Area = '" + s4 + "', Organization = '" + s5 + "', Principal = '" + s6 + "', Contact = '" + s7 + "' where UserName = '" + s1 + "'";
        return jdbcTemplate.update(query) > 0;
    }

    @GetMapping("/del/{s1}")
    public boolean say11(@PathVariable(value = "s1") String s1){
        String query = "delete from Users where UserName = '" + s1 + "'";
        return jdbcTemplate.update(query) > 0;
    }
}
