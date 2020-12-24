package org.uch.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uch.entity.EventCode;
import org.uch.entity.EventCodeParam;
import org.uch.mapper.test.EventCodeMapper;

@Slf4j
@Api(description="restful test API", tags = "A. eventCode CRUD")
@RequestMapping("eventCode")
@RestController
public class TestController {

    @Autowired
    private EventCodeMapper eventCodeMapper;

    @ApiOperation(value = "select Test", response = EventCode.class)
    @RequestMapping(value = "/{id}/info", method = RequestMethod.GET)
    public ResponseEntity<EventCode> eventCodeInfo(@PathVariable Integer id) {
        EventCode eventCode = eventCodeMapper.selectEventCode(id);
        return ResponseEntity.ok(eventCode);
    }

    @ApiOperation(value = "insert Test1 using RequestBody & POST", response = Boolean.class)
    @RequestMapping(value = "/insert1", method = RequestMethod.POST)
    public ResponseEntity<Boolean> insert1(@RequestBody EventCodeParam param) {
        EventCode eventCode = new EventCode();
        eventCode.setName(param.getName());
        eventCode.setMaxCnt(param.getMaxCnt());
        int cnt = eventCodeMapper.insertEventCode(eventCode);
        return ResponseEntity.ok(cnt>0);
    }

    @ApiOperation(value = "insert Test2 using RequestParam & POST", response = Boolean.class)
    @RequestMapping(value = "/insert2", method = RequestMethod.POST)
    public ResponseEntity<Boolean> insert2(@RequestParam String name,
                                           @RequestParam(defaultValue = "10") Integer maxCnt) {
        EventCode eventCode = new EventCode();
        eventCode.setName(name);
        eventCode.setMaxCnt(maxCnt);
        int cnt = eventCodeMapper.insertEventCode(eventCode);
        return ResponseEntity.ok(cnt > 0);
    }

    @ApiOperation(value = "update Test using RequestParam & POST", response = Boolean.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Boolean> update(@RequestParam Integer id,
                                           @RequestParam(defaultValue = "10") Integer maxCnt) {

        int cnt = eventCodeMapper.updateEventCodeMaxCnt(id, maxCnt);
        return ResponseEntity.ok(cnt > 0);
    }
}
