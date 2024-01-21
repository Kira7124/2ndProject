package com.mes2.system.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.system.domain.MenuDTO;
import com.mes2.system.persistence.MenuDAO;

@Service
public class MenuServiceImpl implements MenuService {

	
	
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Inject
	private MenuDAO udao;
	
	
	
	//메뉴상태조회
	@Override
	public MenuDTO getMenuStatus(MenuDTO dto) {
		logger.debug("메뉴상태조회 Service 출력");

		
		return udao.getMenuStatus(dto);
	}

	
	
	
	
	
	
}
