package org.uch.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventCodeParam {

	// 이벤트명
	private String name;

	private Integer maxCnt;
}
