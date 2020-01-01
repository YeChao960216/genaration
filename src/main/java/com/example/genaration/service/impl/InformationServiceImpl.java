package com.example.genaration.service.impl;

import com.example.genaration.entity.Information;
import com.example.genaration.service.InformationService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 叶超(Chace)-01387009
 * @version V1.0
 * @Program: air-commom
 * @Description:
 * @Create 2019/12/31 14:34
 */
@Service
public class InformationServiceImpl implements InformationService {
    String path="F:\\test1.mobileconfig";
    @Override
    public int insert(Information entity) throws Exception {
        //要修改的行
        Map<Integer,String> map=new HashMap<>();
        map.put(8,entity.getAppagree());
        map.put(26,entity.getAppname());
        map.put(39,entity.getIcon());
        map.put(42,entity.getUsedelete());
        map.put(62,entity.getAppaddress());
        map.put(66,entity.getAppdescribe());
        map.put(68,entity.getAppname());
        map.put(70,entity.getIdent());
        map.put(72,entity.getAppname());

        int modifyline=1;//要修改的行
        BufferedReader in=new BufferedReader(new FileReader(path));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path)));
        String line;
        int count=0;
        while((line=in.readLine())!=null){
            count++;
            switch(count){
                case 0:
                    System.out.println("0");
                case 1:
                    System.out.println("1");
                case 2:
                    System.out.println("2");
                default:
                    System.out.println("default");
            }
            if(count==modifyline){
                Matcher ma = Pattern.compile("^[+-]?\\d+").matcher(line.trim());
                if (ma.find()) {
                    String bbString = ma.group(0);
                }
                out.println(line.replace("abc", "def"));  //替换abc成def
            }else{
                out.println(line);
            }

        }
        in.close();
        out.close();

        return 0;
    }
}
