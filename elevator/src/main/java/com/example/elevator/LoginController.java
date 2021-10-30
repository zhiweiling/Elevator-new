package com.example.elevator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/login/{s1}/{s2}/{identity}")
    public Object GetAllInformation(@PathVariable(value = "s1") String s1, @PathVariable(value = "s2") String s2, @PathVariable(value = "identity") Integer identity){
        String query = "";
        switch(identity){
            case 0:
                query = "select count(*),Area from Users where UserName = '" + s1 + "' and PassWord = '" + s2 + "' and Position = '物业管理员'" ;
                break;
            case 1:
                query = "select count(*),Area from Users where UserName = '" + s1 + "' and PassWord = '" + s2 + "' and Position = '社区管理员'" ;
                break;
            case 2:
                query = "select count(*),Area from Users where UserName = '" + s1 + "' and PassWord = '" + s2 + "' and Position = '超管'" ;
                break;
            default :
                query = "";
                break;
        }
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query);
//        return Integer.parseInt(list.get(0).get("count(*)").toString()) > 0;
        return list;
    }


}
