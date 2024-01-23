package com.springproj.emotionshare.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomVO {
	String title;
	String owner;
	List<String> memberIDs;
	int totalHeadCount;
	int currentHeadCount;
}
