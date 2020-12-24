package org.uch.mapper.test;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.uch.entity.EventCode;

@Mapper
public interface EventCodeMapper {

	EventCode selectEventCode(Integer id);

	int insertEventCode(EventCode eventCode);

	int updateEventCodeMaxCnt(@Param("id")Integer id, @Param("maxCnt")Integer maxCnt);
}
