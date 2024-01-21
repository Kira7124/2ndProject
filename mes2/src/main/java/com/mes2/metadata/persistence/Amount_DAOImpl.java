package com.mes2.metadata.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_amountDTO;
import com.mes2.metadata.domain.md_productDTO;
import com.mes2.platform.persistence.PlatformDAOImpl;

@Repository
public class Amount_DAOImpl implements Amount_DAO {

private static final Logger logger = LoggerFactory.getLogger(PlatformDAOImpl.class);
	
	private static final String NAMESPACE ="com.mes2.mapper.amount_Mapper";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public int amountinsert(md_amountDTO dto) throws Exception {
		logger.debug(" DAO : amountinsert() " + dto);
		return sqlSession.insert(NAMESPACE + ".insert", dto);
	}


	@Override
	public int amountupdate(md_amountDTO dto) throws Exception {
		logger.debug(" DAO : amountupdate() " + dto);
		return sqlSession.update(NAMESPACE + ".update", dto);
	}


	@Override
	public int amountdelete(md_amountDTO dto) throws Exception {
		logger.debug(" DAO : amountdelete() " + dto);
		return sqlSession.update(NAMESPACE + ".delete", dto);
	}


	@Override
	public int gettotalcount(alllistDTO aDTO) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".count", aDTO);
	}


	@Override
	public List<md_amountDTO> getlist(alllistDTO aDTO) throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".list", aDTO);
	}
	
	@Override
	public List<md_productDTO> selectbox() throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".selectbox");
	}
	
	@Override
	public List<md_productDTO> selectbox2() throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".selectbox2");
	}
	
	@Override
	public String selectbox3(String product_code) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".selectbox3", product_code);
	}

	@Override
	public String selectbox4(String product_code) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".selectbox4", product_code);
	}
	
}
