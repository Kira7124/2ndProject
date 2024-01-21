package com.mes2.metadata.service;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_productDTO;
import com.mes2.metadata.persistence.Product_DAO;

@Service
public class Product_ServiceImpl implements Product_Service {
	
	private static final Logger logger = LoggerFactory.getLogger(Product_ServiceImpl.class);
	
	@Inject
	private Product_DAO mdao;
	

	@Override
	public int productinsert(md_productDTO dto) throws Exception {
		
		//공통코드가 뭔지 가져오기
		String commoncode = mdao.commoncode(dto.getCategory());
		logger.debug("공통코드다!" + commoncode);
		
		// 공통코드가 몇자리인 알아보기
		int leng = commoncode.length();
		
		//품목테이블에 상품코드 가져오기
		String code = mdao.number(commoncode);
		
		//숫자만 빼서 +1 해주기
		int number = Integer.parseInt(code.substring(leng));
		
		number++;
		
		String result;
		
		if(commoncode.equals("PS")) {
			result = String.format("%05d", number);
		}
		else {
			result = String.format("%03d", number);
		}
		
		
		//공통코드와 숫자 합치기
		String code2 = commoncode + result;
		logger.debug("이제 끝!" + code2);
		
		dto.setProduct_code(code2);
		
		
		return mdao.productinsert(dto);
	}

	@Override
	public int productupdate(md_productDTO dto) throws Exception {
		
		return mdao.productupdate(dto);
	}
	
	@Override
	public int productdelete(md_productDTO dto) throws Exception {
		
		return mdao.productdelete(dto);
	}

	@Override
	public int gettotalcount(alllistDTO aDTO) throws Exception {
		
		return mdao.gettotalcount(aDTO);
	}

	@Override
	public List<md_productDTO> getlist(alllistDTO aDTO) throws Exception {
		
		return mdao.getlist(aDTO);
	}

	@Override
	public List<common_DTO> selectbox() throws Exception {
		
		return mdao.selectbox();
	}

	@Override
	public List<common_DTO> selectbox2() throws Exception {
		
		return mdao.selectbox2();
	}

	
	

}
