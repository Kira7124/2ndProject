package com.mes2.system.persistence;

import com.mes2.system.domain.MenuDTO;

public interface MenuDAO {

	//메뉴상태조회
	public MenuDTO getMenuStatus(MenuDTO dto);
	
	
	
	
}
