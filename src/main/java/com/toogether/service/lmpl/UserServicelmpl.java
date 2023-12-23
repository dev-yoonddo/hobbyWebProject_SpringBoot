package com.toogether.service.lmpl;

import com.toogether.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServicelmpl implements UserService {
    @Override
    public Map<String,Object> getMain() {
        Map<String,Object> mainData = new HashMap<>();
        mainData.put("시작1", "start1");
        mainData.put("시작2", "start2");
        mainData.put("시작3", "start3");

        return mainData;
    }
}
