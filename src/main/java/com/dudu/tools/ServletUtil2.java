package com.dudu.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ServletUtil2 {

    public static ResponseEntity<?> createSysErrorResponse(ErrorCode errorCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", errorCode.getCode());
        map.put("message", errorCode.getMessage());
        map.put("data", new Object());
        String jsonString = "";
        jsonString = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);

        return ResponseEntity.status(200)
                .body(jsonString);
    }

    public static ResponseEntity<?> createSysErrorResponse(ErrorCode errorCode, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", errorCode.getCode());
        map.put("message", message);
        map.put("data", new Object());
        String jsonString = "";
        jsonString = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);

        return ResponseEntity.status(200)
                .body(jsonString);
    }

    public static ResponseEntity<?> createSuccessResponse(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        map.put("message", "ok");
        map.put("data", data);
        String jsonString = "";
        jsonString = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        return ResponseEntity.ok(jsonString);
    }
}
