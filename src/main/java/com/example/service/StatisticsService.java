package com.example.service;

import java.sql.SQLException;
import java.util.Map;

public interface StatisticsService {

    Map getDataMap(String startDate, String endDate) throws SQLException;
}
