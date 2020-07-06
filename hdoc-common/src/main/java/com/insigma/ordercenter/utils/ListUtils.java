package com.insigma.ordercenter.utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ListUtils
 * @Description TODO
 * @Author youwk
 * @Date 2019/7/11 17:31
 * @Version 1.0
 */
public class ListUtils {

    /*public static JSONArray listToTree(JSONArray arr,String id,String pid,String child){
        JSONArray r = new JSONArray();
        JSONObject hash = new JSONObject();
        //将数组转为Object的形式，key为数组中的id
        for(int i=0;i<arr.size();i++){
            JSONObject json = (JSONObject) arr.get(i);
            hash.put(json.getString(id), json);
        }
        //遍历结果集
        for(int j=0;j<arr.size();j++){
            //单条记录
            JSONObject aVal = (JSONObject) arr.get(j);
            //在hash中取出key为单条记录中pid的值
            JSONObject hashVP = (JSONObject) hash.get(aVal.get(pid).toString());
            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if(hashVP!=null){
                //检查是否有child属性
                if(hashVP.get(child)!=null){
                    JSONArray ch = (JSONArray) hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }else{
                    JSONArray ch = new JSONArray();
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }
            }else{
                r.add(aVal);
            }
        }
        return r;
    }* */
    public static void main(String[] args) {
        Map  stu1 = new HashMap<>();
        stu1.put("sid",1);
        stu1.put("sname","zhangsan");
        stu1.put("sex",2);
        stu1.put("pid",0);

        Map  stu2 = new HashMap<>();
        stu2.put("sid",3);
        stu2.put("sname","zhanger");
        stu2.put("sex",2);
        stu2.put("pid",1);

        Map  stu3 = new HashMap<>();
        stu3.put("sid",1);
        stu3.put("sname","zhangyi");
        stu3.put("sex",2);
        stu3.put("pid",1);


        Map  stu4 = new HashMap<>();
        stu4.put("sid",1);
        stu4.put("sname","zhangyi");
        stu4.put("sex",2);
        stu4.put("pid",1);
        
        List list = new ArrayList<>();
        list.add(stu1);
        list.add(stu2);
        list.add(stu3);
        list.add(stu4);
        
        Gson son = new Gson();

    }
}
