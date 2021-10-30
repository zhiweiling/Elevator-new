package com.example.elevator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class InformationController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/info/{id}")
    public Object say1(@PathVariable(value = "id") Integer id){
        String sql = "select * from Summary_sheet where BuildingId = " + id + " order by Date desc limit 7";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        return list;
    }
}
