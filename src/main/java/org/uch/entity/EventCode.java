package org.uch.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EventCode {

	// 이벤트 아이디
	private Integer id;

	// 이벤트명
	private String name;

	private Integer maxCnt;

	private Date createdAt;
}
