package com.mes2.platform.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mes2.platform.domain.MdbDTO;

@Mapper
public interface PlatformMapper {
	
	public MdbDTO read(String company_code);
	
}
