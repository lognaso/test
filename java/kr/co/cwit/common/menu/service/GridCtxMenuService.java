/** ########################################################
 * @ Class Name   	: GridCtxMenuService.java
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2018. 07.01
 * @version 1.0
 * @see
 * @ [저작권 및 라이센스 관련 정보를 여기에 작성한다.]
 * @ Copyright© 2013 (주)중외정보기술 All Rights Reserved.
 * ######################################################### */

package kr.co.cwit.common.menu.service;

import java.util.List;
import java.util.Map;

/** ########################################################
 * @System         	: 전체공통
 * @Unit System     : 전체공통
 * @Program Name  	: 그리드 컬럼 개인화
 * @Class Name     	: GridCtxMenuService.java
 * @Description     : 그리드 컬럼틀고정, 컬럼위치, 컬럼숨김 등을 개인화 처리한다.
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------      ---------      -------------------------------
 * @ 2018.09.17    	김태진        			최초생성
 *
* ########################################################## */

public interface GridCtxMenuService {
	/**
	 * 그리드 컬럼 개인화 조회
	 * @param paramMap
	 * @return
	 * @throws Throwable
	 */
	public List<Map<String, Object>> selectSyGrdColIndvList(Map<String, Object> paramMap) throws Throwable;

	/**
	 * 그리드 컬럼 개인화 저장
	 * @param paramMap
	 * @return
	 * @throws Throwable
	 */
	public int saveSyGrdColIndv(Map<String, Object> paramMap) throws Throwable;
}
