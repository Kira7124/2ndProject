package com.mes2.system.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.system.domain.MenuDTO;


@Repository
public class MenuDAOImpl implements MenuDAO {

	
	private static final Logger logger = LoggerFactory.getLogger(MenuDAOImpl.class);
	
	
	@Inject
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.mes2.mapper.MenuMapper";
	
	
	//메뉴상태조회
	@Override
	public MenuDTO getMenuStatus(MenuDTO dto) {
		logger.debug("MenuDAOImpl 실행!");
		
		MenuDTO resultDTO = sqlSession.selectOne(NAMESPACE+".getMenuStatus", dto);
		
		return resultDTO;
	}

}
