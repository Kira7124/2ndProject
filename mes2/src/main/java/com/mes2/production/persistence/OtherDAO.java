package com.mes2.production.persistence;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OtherDAO {

	@Inject
	private SqlSession sqlSession;
	
	private final Logger log = LoggerFactory.getLogger(OtherDAO.class);
	private final String NAMESAPCE="com.mes2.mapper.EtcMapper";
	
	public String getMaterialsLastLot(String param) {
		
		return sqlSession.selectOne(NAMESAPCE+".selectMaxMaterialsLot", param);
	}
	
	public String createRawMaterialsLot(String productCode) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");

		String inputStrDate = sf.format(Date.valueOf(LocalDate.now()));
		
		String paramLot = inputStrDate+"-RM-"+productCode+"-";
		
		System.out.println("instruction : paramCode 값 : " +paramLot);
		
		String result = getMaterialsLastLot(paramLot);
		
		if(result==null) {
			result = paramLot+"0001";
		}else {
			int tmpCount =Integer.valueOf(result.substring(20));
			
			log.debug("tmpCount 작업 전 값 : "+tmpCount);
			tmpCount+=1;
			
			log.debug("tmpCount 작업 전 후 : "+tmpCount);
			
			result = paramLot+String.format("%04d",tmpCount);
			
			log.debug("최종 생성된 LOT : "+result);
		}
		
		log.debug("@@@@@@@@@@@검색한 해당 LotNumber : "+ result);
		
		
		return result;
	}
	
	public String createProductCode() {
		
		
		
		return null;
	}
}
