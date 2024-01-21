package com.mes2.metadata.service;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_businessDTO;
import com.mes2.metadata.persistence.Business_DAO;

@Service
public class Business_ServiceImpl implements Business_Service {
	
	private static final Logger logger = LoggerFactory.getLogger(Business_ServiceImpl.class);
	
	@Inject
	private Business_DAO bdao;
	

	@Override
	public int businessinsert(md_businessDTO dto) throws Exception {
		
		//공통코드가 뭔지 가져오기
		String commoncode = bdao.commoncode(dto.getCategory());
		logger.debug("공통코드다!" + commoncode);
		
		// 공통코드가 몇자리인 알아보기
		int leng = commoncode.length();
		
		//품목테이블에 상품코드 가져오기
		String code = bdao.number(commoncode);
		logger.debug("코드다!" + code);
		
		//숫자만 빼서 +1 해주기
		int number = Integer.parseInt(code.substring(leng));
		number++;
		
		String result = String.format("%03d", number);
		
		//공통코드와 숫자 합치기
		String code2 = commoncode + result;
		logger.debug("이제 끝!" + code2);
		
		dto.setCompany_code(code2);
		
		
		return bdao.businessinsert(dto);
	}

	@Override
	public int businessupdate(md_businessDTO dto) throws Exception {
		
		return bdao.businessupdate(dto);
	}
	
	@Override
	public int businessdelete(md_businessDTO dto) throws Exception {
		
		return bdao.businessdelete(dto);
	}

	@Override
	public int gettotalcount(alllistDTO aDTO) throws Exception {
		
		return bdao.gettotalcount(aDTO);
	}

	@Override
	public List<md_businessDTO> getlist(alllistDTO aDTO) throws Exception {
		
		return bdao.getlist(aDTO);
	}

	

}
