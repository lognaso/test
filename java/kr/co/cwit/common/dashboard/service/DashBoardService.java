package kr.co.cwit.common.dashboard.service;

import java.util.List;
import java.util.Map;

public interface DashBoardService {

    public List<Map<String, Object>> selectDownLeft(Map<String, Object> paramMap) throws Throwable;
    
    public List<Map<String, Object>> selectDownRight(Map<String, Object> paramMap) throws Throwable;
    
    public List<Map<String, Object>> selectUpLeft(Map<String, Object> paramMap) throws Throwable;
    
    public List<Map<String, Object>> selectUpRight(Map<String, Object> paramMap) throws Throwable;
    
    public List<Map<String, Object>> selectNotice(Map<String, Object> paramMap) throws Throwable;
    
    public List<Map<String, Object>> selectMainNotice(Map<String, Object> paramMap) throws Throwable;
}
