package com.example.service.impl;

import com.example.service.StatisticsService;
import com.example.util.Constants;
import com.example.util.UtilDateTime;
import com.hx.demo.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dzh
 * @since 2020-05-15
 */
@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private IUserService userService;

    @Override
    public Map getDataMap(String startDate, String endDate) throws SQLException {
        //实体档案统计
        if (StringUtils.isBlank(startDate)){
            startDate = UtilDateTime.monthBeginString();
        }
        if (StringUtils.isBlank(endDate)){
            endDate = UtilDateTime.monthEndString();
        }
        Map dataMap = new HashMap();
        String entityCode = Constants.ENTITY_APPLY_RELATE_TABLE;
        List entityColumnList = new ArrayList();
        entityColumnList.add("CF1011");
        entityColumnList.add("CF1038");
        entityColumnList.add("count(cf0000) passNum");//审批通过数量
        Map conditionMap = new HashMap();
        conditionMap.put("CF1010", "4");//申请状态
        List groupList = new ArrayList();
        groupList.add("CF1011");
        groupList.add("CF1038");
        //初始化条件
        StringBuffer sqlScript = new StringBuffer();
        if(StringUtils.isNotBlank(sqlScript)){
            sqlScript.append(" and ");
        }
        sqlScript.append("CF1007 >= '"+startDate+"'");
        if(StringUtils.isNotBlank(sqlScript)){
            sqlScript.append(" and ");
        }
        sqlScript.append("CF1007 <= '"+endDate+"'");
        List categoryList = getAllEnableCategoryList();
        List<Map<String,Object>> entitydataList = null;
        if (categoryList != null && categoryList.size() > 0){
            int jiechuTotalNums = 0;
            int chayueTotalNums = 0;
            Map m = null;
            for (int i = 0; i < categoryList.size(); i++) {
                m = (Map) categoryList.get(i);
                String categoryId = m.get("CATEGORY_ID").toString();
                conditionMap.put("CF1001", categoryId);
                try {
                    entitydataList = userService.getDataList(entityCode, entityColumnList, conditionMap, null, null, null, null, groupList, null, sqlScript.toString());
                    if(entitydataList != null && entitydataList.size() > 0){
                        int jiechuTotalNum = 0;
                        int chayueTotalNum = 0;
                        for (int j = 0; j < entitydataList.size(); j ++) {
                            int passNum = 0;
                            String CF1011 = null;
                            String CF1038 = null;
                            if(entitydataList != null && entitydataList.size() > 0){
                                Map<String,Object> map = entitydataList.get(j);
                                if(map.containsKey("CF1011")){
                                    CF1011 = (String)map.get("CF1011");
                                }
                                if(map.containsKey("CF1038")){
                                    CF1038 = (String)map.get("CF1038");
                                }
                                if(map.containsKey("PASSNUM")){
                                    String bdStr = map.get("PASSNUM").toString();
                                    Integer bd = Integer.parseInt(bdStr);
                                    if(bd != null){
                                        passNum = bd.intValue();
                                    }
                                }
                            }
                            dataMap.put(categoryId+"-"+CF1011+"-"+CF1038, passNum);
                            if ("借出".equals(CF1038)) {
                                jiechuTotalNum += passNum;
                                jiechuTotalNums += passNum;
                            }else if ("阅览".equals(CF1038)) {
                                chayueTotalNum += passNum;
                                chayueTotalNums += passNum;
                            }
                        }
                        dataMap.put(categoryId+"-借出", jiechuTotalNum);
                        dataMap.put(categoryId+"-阅览", chayueTotalNum);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return dataMap;
    }

    /**
     * 获取全部门类
     * @return List
     */
    public List getAllEnableCategoryList() throws SQLException {
        String sql="select * from CI_CATEGORY where CATEGORY_STATE = '1' order by ORDERNO";
        List<Map<String, Object>> dataList = userService.getDataList(sql);
        return dataList;
    }
}
