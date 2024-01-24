package com.springproj.emotionshare.glassBottle.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlacklistDto {
	private Long userId;//블랙리스트 대상자
	private String userName; // 블랙리스트 대상자의 이름
}
