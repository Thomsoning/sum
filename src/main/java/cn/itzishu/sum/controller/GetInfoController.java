package cn.itzishu.sum.controller;


import cn.itzishu.sum.service.ConnectJWGL;
import cn.itzishu.sum.vo.Result;
import cn.itzishu.sum.vo.User;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GetInfoController {
    @Autowired
    private ConnectJWGL connectJWGL;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Result test(String s)throws Exception{
        System.out.println("我收到的数据为："+s);
        Result result = new Result();
        result.setMessage(s);
        result.setCode(200);
        return result ;
        }

    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    public Result getInfo(User user) throws Exception {
//        User user=new User();
//        user.setStuNum("201531002029");
//        user.setPassword("hzw027710");
//        user.setYear(2017);
//        user.setTerm(1);
        //初始化
        connectJWGL.setStuNum(user.getStuNum());
        connectJWGL.setPassword(user.getPassword());
        connectJWGL.init();
        Boolean bool = connectJWGL.beginLogin();
        Result result = new Result();
        //账户密码正确
        if (bool == true) {
            //获取基本信息
            Map studenInfo = connectJWGL.getStudentInformaction();
            HashMap map = new HashMap<>();
            map.put("studentInfo", studenInfo);
            result.setResult(map);
            //获取课表信息
            if (user.getYear() == null && user.getTerm() == null) {

                result.setCode(200);
                result.setMessage("没有发现课表信息");
                return result;
            }

            List timetable = connectJWGL.getStudentTimetable(user.getYear(), user.getTerm());
            map.put("timetable", timetable);
            result.setResult(map);
            result.setCode(200);
            result.setMessage("响应成功");
            return result;
        }
        result.setCode(404);
        result.setMessage("学生信息有误");
      return result;

    }
}
