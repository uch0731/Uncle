package org.uch.testuch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

    @RestController
    public class TestController {
        @GetMapping("/test")
        public Map<String, Object> getTest() {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("a",1);
            resultMap.put("c",3);
            return resultMap;
        }
    }
