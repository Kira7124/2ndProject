package com.mes2.metadata.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_businessDTO;
import com.mes2.platform.persistence.PlatformDAOImpl;

@Repository
public class Business_DAOImpl implements Business_DAO{
	
private static final Logger logger = LoggerFactory.getLogger(PlatformDAOImpl.class);
	
	private static final String NAMESPACE ="com.mes2.mapper.business_Mapper";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public int businessinsert(md_businessDTO dto) throws Exception {
		logger.debug(" DAO : businessinsert() " + dto);
		return sqlSession.insert(NAMESPACE + ".insert", dto);
	}


	@Override
	public int businessupdate(md_businessDTO dto) throws Exception {
		logger.debug(" DAO : businessupdate() " + dto);
		return sqlSession.update(NAMESPACE + ".update", dto);
	}


	@Override
	public int businessdelete(md_businessDTO dto) throws Exception {
		logger.debug(" DAO : businessdelete() " + dto);
		return sqlSession.update(NAMESPACE + ".delete", dto);
	}


	@Override
	public int gettotalcount(alllistDTO aDTO) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".count", aDTO);
	}


	@Override
	public List<md_businessDTO> getlist(alllistDTO aDTO) throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".list", aDTO);
	}


	@Override
	public String commoncode(String category) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".common", category);
	}


	@Override
	public String number(String commoncode) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".number", commoncode);
	}



}
