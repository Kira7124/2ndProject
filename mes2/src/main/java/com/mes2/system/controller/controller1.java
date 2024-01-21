package com.mes2.system.controller;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mes2.system.domain.BoardDTO;
import com.mes2.system.domain.CommonCodeDTO;
import com.mes2.system.domain.Criteria;
import com.mes2.system.domain.MemberDTO;
import com.mes2.system.domain.MenuDTO;
import com.mes2.system.domain.PageVO;
import com.mes2.system.service.BoardService;
import com.mes2.system.service.CommonCodeService;
import com.mes2.system.service.MemberService;
import com.mes2.system.service.MenuService;

@Controller
@RequestMapping("/system/*")
public class controller1 {

	private static final Logger logger = LoggerFactory.getLogger(controller1.class);

	@Inject
	private MemberService mService;
	
	@Inject
	private CommonCodeService cService;
	
	
	@Inject
	private BoardService bService;
	
	
	@Inject
	private ServletContext servletContext;
	
	
	
	
	 // http://localhost:8088/system/login
	 // http://localhost:8088/system/sidehead
	 
	
	
	
	// 로그인 페이지호출
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(Model model, @CookieValue(value = "rememberedId", required = false) String rememberedId) {
		model.addAttribute("rememberedId", rememberedId);
		logger.debug("loginget() 호출!");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberDTO dto,HttpSession session, HttpServletResponse response,
			@RequestParam(value = "remember", required = false) String remember,Criteria cri,Model model) throws Exception {
		logger.debug("loginpost() 호출!");
		logger.debug("전달정보 :" + dto);
		
		
		int totalMembers = mService.totalMember();
		Integer totalProduct = mService.totalPd();
		Integer totalInP = mService.totalIn();
		Integer totalOutP = mService.totalOut();
		Integer totalOk = mService.totalOk();
		Integer totalNo = mService.totalNo();
		Integer firstLine = mService.firstLine();
		Integer secondLine = mService.secondLine();
		Integer thirdLine = mService.thirdLine();
		Integer forthLine = mService.forthLine();
		Integer fifthLine = mService.fifthLine();
		Integer sixthLine = mService.sixthLine();
		Integer sevenLine = mService.sevenLine();
		
		
		
		session.setAttribute("viewcntCheck", true);
		
		MemberDTO resultDTO = mService.memberLogin(dto);
		
		
		if (resultDTO != null) {

			session.setAttribute("id", resultDTO.getUser_id());
			session.setAttribute("pw", resultDTO.getUser_pw());
			session.setAttribute("name", resultDTO.getUser_name());
			session.setAttribute("jumin", resultDTO.getUser_jumin());
			session.setAttribute("position", resultDTO.getUser_position());
			session.setAttribute("tel", resultDTO.getUser_tel());
			session.setAttribute("auth", resultDTO.getUser_auth());
			session.setAttribute("img", resultDTO.getUser_img());
			session.setAttribute("status", resultDTO.getMenu_status());
			session.setAttribute("department", resultDTO.getUser_department());
			session.setAttribute("total", totalMembers);
			session.setAttribute("totalProduct", totalProduct);
			session.setAttribute("totalIn", totalInP);
			session.setAttribute("totalOut", totalOutP);
			session.setAttribute("totalOk", totalOk);
			session.setAttribute("totalNo", totalNo);
			session.setAttribute("firstLine", firstLine);
			session.setAttribute("secondLine", secondLine);
			session.setAttribute("thirdLine", thirdLine);
			session.setAttribute("forthLine", forthLine);
			session.setAttribute("fifthLine", fifthLine);
			session.setAttribute("sixthLine", sixthLine);
			session.setAttribute("sevenLine", sevenLine);
			
	
			
			if (remember != null && remember.equals("chk")) {
				// 쿠키 7 일간 보존
				Cookie cookie = new Cookie("rememberedId", resultDTO.getUser_id());
				cookie.setMaxAge(604800);
				response.addCookie(cookie);
			}
			  model.addAttribute("loginSuccess", true);
			  return "redirect:/system/mainpage";
			  
		 }

		else {
			  model.addAttribute("loginFailed", true);
			  return "redirect:/system/login";
		}

	}

	// 로그아웃 하기
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		logger.info("logout 메서드 선언");
		HttpSession session = request.getSession();

		session.invalidate();

		logger.info("logout 완료!!");

		return "redirect:/system/login";
	}

	// 사원정보조회 하기
	@GetMapping(value = "/info")
	public void memberInfoGET(HttpSession session, Model model) {
		logger.debug("/members/info 호출 -> memberInfoGET()실행");

		// ID정보를 받아오기(세션영역)
		String id = (String) session.getAttribute("id");
		logger.debug(" 아이디 정보 : " + id);
		// 서비스 -> id를 사용해서 회원정보 모두 조회 동작
		MemberDTO dto = mService.memberInfo(id);
		logger.debug(" dto : " + dto);
		// DB에서 조회된 결과를 view페이지로 전달 => Model 객체 생성
		model.addAttribute("dto", dto);

		// 이름이 없는경우 전달되는 데이터 클래스타입의 첫글자를 소문자로 바꿔서 이름으로 사용
		model.addAttribute(mService.memberInfo(id));

		// 페이지 이동(/login/info.jsp)
	}

	
	
	// 사원정보수정 하기 - GET
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void getmemberUpdateGET(HttpSession session, Model model) {
		logger.debug("/members/update 호출!");

		// 아이디 정보저장 (세션영역)
		String id = (String) session.getAttribute("id");
		// 서비스 -> 아이디에 해당하는 회원정보 조회
		// 연결된 뷰페이지(/login/update.jsp)에 정보전달
		model.addAttribute(mService.memberInfo(id));

	}

	
	
	
	// 사원정보수정 하기 -POST
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String memberUpdatePOST(MemberDTO dto, HttpServletRequest request) {
		logger.debug("/members/update -> memberUpdatePOST()");
		// 한글처리(인코딩 - 필터에서 처리함!)
		// 전달정보 저장(폼태그 - 파라미터)
		logger.debug("수정할 정보 :" + dto);

		String tel = request.getParameter("user_tel1") + "-" + request.getParameter("user_tel2") + "-"
				+ request.getParameter("user_tel3");
		dto.setUser_tel(tel);

		// 서비스 - 회원정보 수정하는 동작
		mService.memberUpdate(dto);

		// 메인페이지로 이동
		return "redirect:/system/sidehead";
	}

	
	
	
	
	
	// http://localhost:8088/system/login
	// 사원목록리스트 출력- GET , 페이징추가

	@RequestMapping(value = "/memberlist", method = RequestMethod.GET)
	public String memberlistGET(MemberDTO dto, Model model,Criteria cri) throws Exception {
		logger.debug("/members/memberlist 호출!");

		
		
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(mService.totalMemberCount());
		
		logger.debug("확인 :" + pageVO);
		
		model.addAttribute("pageVO", pageVO);
		
		List<MemberDTO> resultDTO = mService.MemberListPage(cri);
		model.addAttribute("memberlist", resultDTO);

		logger.debug("memberlist.jsp 이동!");

		return "/system/memberlist";
	}

	
	
	
	
	
	
	
	
	
	
	
	
//	// 메인페이지 GET
//	// http://localhost:8088/login/main
//
//	@RequestMapping(value = "/main", method = RequestMethod.GET)
//	public String mainGET() {
//		logger.debug(" /main/login 호출 -> mainGET() 실행");
//
//		logger.debug("연결된 뷰페이지(/views/login/main.jsp) 이동 ");
//		return "/system/main";
//	}

	
	
// 12-29 join 작업중....@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	// http://localhost:8088/system/login	
	
	
//	// 사원등록하기-GET
//	@RequestMapping(value = "/join", method = RequestMethod.GET)
//	public void memberJoinGET() {
//		logger.debug("memberJoinGET() 호출");
//		// 연결된 뷰페이지로 이동
//		logger.debug("/views/login/join.jsp");
//	}

	
	
	
// 12-29 join 작업중....@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	
	// 사원등록하기-POST
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String memberJoinActionPOST(MemberDTO dto, HttpServletRequest request) {
		logger.debug("memberJoinPOST() 호출");

		// 주민번호,전화번호는 jumin,tel 으로 받아서 따로 dto 에 세팅 ( ex.앞자리(user_jumin) -
		// 뒷자리(user_jumin2) )

		String jumin = request.getParameter("user_jumin") + "-" + request.getParameter("user_jumin2");
		String tel = request.getParameter("user_tel1") + "-" + request.getParameter("user_tel2") + "-"
				+ request.getParameter("user_tel3");
		String address = request.getParameter("zip-code") +")" + request.getParameter("address") + request.getParameter("address_detail");
		dto.setUser_jumin(jumin);
		dto.setUser_tel(tel);
		dto.setUser_address(address);
		
		// 전달정보 저장
		logger.debug("dto :" + dto);

		// DB에 정보를 저장 ( 기존 JSP => new MemberDAO().method() 호출; / 지금 Service 객체사용 )
		logger.debug("서비스 사원등록 동작을 호출 ! - 시작");
		mService.memberJoin(dto);

		logger.debug("서비스 사원등록 동작을 호출 ! - 끝");

		// 페이지 이동 (로그인-/members/login)
		return "redirect:/system/memberlist";
	}

	
	
	
	
	
	
	
	
	
	
	
//	// admin 사원정보 수정하기 GET
//
//	@RequestMapping(value = "/adminupdate", method = RequestMethod.GET)
//	public void adminUpdateGET(MemberDTO dto, Model model, HttpServletRequest request) throws Exception {
//		logger.debug("/members/adminupdate -> adminUpdateGET()");
//		logger.debug("/members/update 호출!");
//
//		// 아이디 정보저장 (파라미터로 받아온값-> memberlist의 form에 명시! )
//		String id = request.getParameter("user_id");
//		// 서비스 -> 아이디에 해당하는 회원정보 조회
//		// 연결된 뷰페이지(/members/adminupdate.jsp)에 정보전달
//		model.addAttribute(mService.memberInfo(id));
//
//		logger.debug("세션 값 : " + id);
//
//		logger.debug("/members/adminupdate.jsp 페이지이동");
//
//	}
//
//	
	
	
	// admin 사원정보 수정하기 POST

	@RequestMapping(value = "/adminupdate", method = RequestMethod.POST)
	public String adminUpdatePOST(MemberDTO dto, HttpServletRequest request, Model model, MultipartHttpServletRequest multiRequest) throws Exception {
		logger.debug("/members/update -> memberUpdatePOST()@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		// 한글처리(인코딩 - 필터에서 처리함!)
		// 전달정보 저장(폼태그 - 파라미터)
		logger.debug("수정할 정보 :" + dto);
		
		
		String address = request.getParameter("zip-code") +")" + request.getParameter("address") + request.getParameter("address_detail");
		dto.setUser_address(address);


		//파일업로드처리
		Map paramMap = new HashMap();
	    List<String> fileList = fileProcess(multiRequest);
	    paramMap.put("fileList", fileList);

	    
	    

		
		// user_img에 파일 경로 할당
		String firstFilePath = dto.getUser_img();
		if (fileList != null && fileList.size() > 0) {
		    firstFilePath = fileList.get(0).toString();
		}
		dto.setUser_img(firstFilePath);
		
		
		// 서비스 - 회원정보 수정하는 동작
		mService.memberUpdate(dto);

		// 메인페이지로 이동
		return "redirect:/system/memberlist";
	}

	
	
	
	// http://localhost:8088/system/login

//	// admin 사원정보 삭제하기 GET@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//
//	@RequestMapping(value = "/admindelete", method = RequestMethod.GET)
//	public void adminDeleteGET(HttpSession session, Model model, HttpServletRequest request) {
//		logger.debug("/login/admindelete -> memberDeleteGET()");
//		logger.debug("/login/admindelect.jsp 페이지이동");
//
//		String id = request.getParameter("user_id");
//		model.addAttribute(mService.memberInfo(id));
//
//	}
	
	
	
	
	// admin 사원정보 삭제하기 POST@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	@RequestMapping(value = "/admindelete", method = RequestMethod.POST)
	public String adminDeletePOST(MemberDTO dto, HttpServletRequest request, Model model) {
		logger.debug("/login/delete -> memberDeletePOST()");
		// 한글처리(인코딩 - 필터에서 처리함!)
		// 전달정보 저장(폼태그 - 파라미터)
		
		String id = request.getParameter("user_id");
		model.addAttribute(mService.memberInfo(id));
		
		logger.debug("수정할 정보 :" + dto);

		logger.debug("넘어온 url : " + request.getPathInfo());

		// 서비스 - 회원정보 삭제하는 동작
		mService.delete(dto);

		// 메인페이지로 이동
		return "redirect:/system/memberlist";
	}

	
	
	
	
	
	// 공통코드리스트 출력하기 GET

	@RequestMapping(value = "/commoncodelist", method = RequestMethod.GET)
	public String commoncodelistGET(CommonCodeDTO dto, Model model,Criteria cri) throws Exception {
		logger.debug("/login/commoncodelist 호출!");

		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(cService.totalCommonCodeCount());
		
		logger.debug("확인 :" + pageVO);
		
		model.addAttribute("pageVO", pageVO);
		
		List<CommonCodeDTO> resultDTO = cService.CommonCodeListPage(cri);
		model.addAttribute("commoncodelist", resultDTO);

		logger.debug("memberlist.jsp 이동!");

		return "/system/commoncodelist";
	}

//	// 공통코드정보 수정하기 GET
//
//	@RequestMapping(value = "/commoncodeupdate", method = RequestMethod.GET)
//	public void commoncodeUpdateGET(CommonCodeDTO dto, Model model, HttpServletRequest request) {
//		logger.debug("/login/commoncodeupdate -> commoncodeUpdateGET()");
//		logger.debug("/login/update 호출!");
//
//		// 코드정보 저장
//		int code_index = Integer.parseInt(request.getParameter("code_index"));
//
//		// 서비스 -> 코드인덱스에 해당하는 정보 조회
//		// 연결된 뷰페이지에 정보전달
//		model.addAttribute(cService.CommoncodeInfo(code_index));
//
//		logger.debug("세션 값 : " + code_index);
//
//		logger.debug("/login/commoncodeupdate.jsp 페이지이동");
//
//	}

	// 공통코드 수정하기 POST

	@RequestMapping(value = "/commoncodeupdate", method = RequestMethod.POST)
	public String commonCodeUpdatePOST(CommonCodeDTO dto, HttpServletRequest request, Model model) {
		logger.debug("/login/commoncodeupdate -> commoncodeUpdatePOST()");
		
		int code_index = Integer.parseInt(request.getParameter("code_index"));
		model.addAttribute(cService.CommoncodeInfo(code_index));
		
		// 전달정보 저장(폼태그 - 파라미터)
		logger.debug("수정할 정보 :" + dto);

		logger.debug("넘어온 url : " + request.getPathInfo());

		// 서비스 - 공통코드 수정하는 동작
		cService.commoncodeUpdate(dto);

		// 코드리스트로 이동
		return "redirect:/system/commoncodelist";
	}

	
	
//	
//	// 공통코드등록하기-GET
//	@RequestMapping(value = "/commoncodejoin", method = RequestMethod.GET)
//	public void commonCodeJoinGET() {
//		logger.debug("commonCodeJoinGET() 호출");
//		// 연결된 뷰페이지로 이동
//		logger.debug("/views/login/commoncodejoin.jsp");
//	}

	
	
	
	// 공통코드등록하기-POST
	@RequestMapping(value = "/commoncodejoin", method = RequestMethod.POST)
	public String commonCodeJoinPOST(CommonCodeDTO dto) {
		logger.debug("memberJoinPOST() 호출");


		// 전달정보 저장
		logger.debug("dto :" + dto);

		// DB에 정보를 저장 ( 기존 JSP => new MemberDAO().method() 호출; / 지금 Service 객체사용 )
		logger.debug("서비스 코드등록 동작을 호출 ! - 시작");
		cService.insertCommonCode(dto);

		logger.debug("서비스 코드등록 동작을 호출 ! - 끝");

		// 페이지 이동 (로그인-/members/login)
		return "redirect:/system/commoncodelist";
	}

	
	
	
	
//	// 공통코드삭제하기 GET
//
//	@RequestMapping(value = "/commoncodedelete", method = RequestMethod.GET)
//	public void commoncodeDeleteGET(CommonCodeDTO dto, Model model, HttpServletRequest request) {
//		logger.debug("/login/commoncodedelete -> commoncodeDeleteGET()");
//		logger.debug("/login/delete 호출!");
//
//		// 코드정보저장 (파라미터로)
//		int code_index = Integer.parseInt(request.getParameter("code_index"));
//
//		// 서비스 -> index 에 해당하는 코드를조회
//		// 연결된 뷰페이지에 정보전달
//		model.addAttribute(cService.CommoncodeInfo(code_index));
//
//		logger.debug("세션 값 : " + code_index);
//
//		logger.debug("/login/commoncodeupdate.jsp 페이지이동");
//
//	}

	
	
	
	
	// 공통코드삭제하기 POST

	@RequestMapping(value = "/commoncodedelete", method = RequestMethod.POST)
	public String commonCodeDeletePOST(CommonCodeDTO dto, HttpServletRequest request, Model model) {
		logger.debug("/system/commoncodeupdate -> commoncodeUpdatePOST()");

		// 전달정보 저장(폼태그 - 파라미터)
		logger.debug("수정할 정보 :" + dto);

		logger.debug("넘어온 url : " + request.getPathInfo());
		int code_index = Integer.parseInt(request.getParameter("code_index"));
		model.addAttribute(cService.CommoncodeInfo(code_index));

		
		// 서비스 - 회원정보 수정하는 동작
		cService.deleteCommonCode(dto);

		// 메인페이지로 이동
		return "redirect:/system/commoncodelist";
	}

	
	
	
	// 아이디 중복체크 get
	
	  @GetMapping("/checkID")
	  @ResponseBody
	    public String checkIDGet(@RequestParam("user_id") String user_id) {
	        boolean result = mService.checkID(user_id);

	        if (result) {
	            return "duplicate";
	        } else {
	            return "not-duplicate";
	        }
	    }

	 // 아이디 중복체크 post

	  
	    @PostMapping("/checkID")
	    public void checkIDPost(@RequestParam("user_id") String user_id, HttpServletResponse response) throws IOException {
	        boolean result = mService.checkID(user_id);

	        if (result) {
	            response.getWriter().write("duplicate");
	        } else {
	            response.getWriter().write("not-duplicate");
	        }
	    }
	
	
	
	 // http://localhost:8088/system/sidehead
	
	    // 메인2) 사이드헤드 view
		@RequestMapping(value = "/sidehead", method = RequestMethod.GET)
		public String sideGET() {
			logger.debug(" /main/login 호출 -> mainGET() 실행");

			logger.debug("연결된 뷰페이지(/views/login/main.jsp) 이동 ");
			return "/system/sidehead";
		}
	    
		
		
		
		
		
		
		// 파일업로드 메서드 ( 수업참고 )	
		private List<String> fileProcess(MultipartHttpServletRequest multiRequest) throws Exception{
			
			//파일의 이름을 저장
			List<String> fileList = new ArrayList<String>();
			
			//폼태그에서 전달된 파일의 정보를 받아오기
			//+ input 태그 file 의 이름을 모두 가져오기 
			Iterator fileNames =  multiRequest.getFileNames();
			while(fileNames.hasNext()) {
				String fileName = (String)fileNames.next();
				//fileName = > 파라메터명 저장
				logger.debug("fileName : " + fileName);
				
				//전달된파일이름에 해당하는 MultipartFile 정보를 저장
				MultipartFile mFile = multiRequest.getFile(fileName);
				String oFileName = mFile.getOriginalFilename();
				//파라미터이름으로 전달받은 파일의 이름을 불러옴
				logger.debug("oFileName : " + oFileName);
				//업로드 된 실제 파일의 이름을 저장
				fileList.add(oFileName);
				String realPath = servletContext.getRealPath("/resources/img/members");
				File uploadPath  = new File(realPath);
				// 실제 폴더 생성 -> D드라이브에 springupload 라는 폴더생성
				File file  = new File(uploadPath + "/" + oFileName);
				//파일업로드
				if(mFile.getSize() != 0) { //첨부했던 첨부파일이 있을 때 의 의미 (!= 0)
					if(!file.exists()) { // 파일, 폴더가 실제로 존재하는지 체크
						if(file.getParentFile().mkdirs()) {
							file.createNewFile();
							
						}
						
					}// exists
					//해당파일로 보낼 수 있게 하겠다 -> mFile.transferTo(dest)
					mFile.transferTo(file);
				}//getSize
				
				
				
		    }//while
			
			
			
			
			return fileList;
		}
		
		
		
		
		
		//시스템메뉴 출력
		
		

		// 메뉴리스트 출력- GET

		@RequestMapping(value = "/menulist", method = RequestMethod.GET)
		public String menulistGET(MemberDTO dto, Model model) {
			logger.debug("/members/memberlist 호출!");

			List<MemberDTO> resultDTO = mService.getMenuList(dto);
			model.addAttribute("menulist", resultDTO);

			logger.debug("memberlist.jsp 이동!");

			return "/system/menulist";
		}
		
		
//		
//		// 메뉴상태업데이트GET
//		@RequestMapping(value="/menuupdate",method=RequestMethod.GET)
//		public String menuUpdateGET() {
//			logger.debug("/system/menuupdate 호출!");
//			return "/system/menuupdate";
//			
//		}
		
		
		
		// 메뉴상태업데이트POST
		@RequestMapping(value="/menuupdate",method=RequestMethod.POST)
		public String menuUpdatePOST(MemberDTO dto, HttpServletRequest request) {
			logger.debug("/system/menuupdate 호출!");
			String user_auth = request.getParameter("user_auth");
			String menu_status = request.getParameter("menu_status");
			
			dto.setUser_auth(user_auth);
			dto.setMenu_status(menu_status);
			
			mService.updateMenu(dto);
			
			
			return "redirect:/system/menulist";
			
		}
		
		
		
		
		
		
		// 메인페이지 GET
		// http://localhost:8088/login/mainpage

		@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
		public String mainpageGET(Model model,HttpSession session,Criteria cri) throws Exception {
			logger.debug(" /main/login 호출 -> mainGET() 실행");
			
			int totalMembers = mService.totalMember();
			Integer totalProduct = mService.totalPd();
			Integer totalInP = mService.totalIn();
			Integer totalOutP = mService.totalOut();
			Integer totalOk = mService.totalOk();
			Integer totalNo = mService.totalNo();
			Integer firstLine = mService.firstLine();
			Integer secondLine = mService.secondLine();
			Integer thirdLine = mService.thirdLine();
			Integer forthLine = mService.forthLine();
			Integer fifthLine = mService.fifthLine();
			Integer sixthLine = mService.sixthLine();
			Integer sevenLine = mService.sevenLine();
			
			session.setAttribute("total", totalMembers);
			session.setAttribute("totalProduct", totalProduct);
			session.setAttribute("totalIn", totalInP);
			session.setAttribute("totalOut", totalOutP);
			session.setAttribute("totalOk", totalOk);
			session.setAttribute("totalNo", totalNo);
			session.setAttribute("firstLine", firstLine);
			session.setAttribute("secondLine", secondLine);
			session.setAttribute("thirdLine", thirdLine);
			session.setAttribute("forthLine", forthLine);
			session.setAttribute("fifthLine", fifthLine);
			session.setAttribute("sixthLine", sixthLine);
			session.setAttribute("sevenLine", sevenLine);
			
			session.setAttribute("viewcntCheck", true);
			
			
			PageVO pageVO = new PageVO();
			pageVO.setCri(cri);
			pageVO.setTotalCount(bService.totalBoardCount());
			
			logger.debug("확인 :" + pageVO);
			
			model.addAttribute("pageVO", pageVO);
			
			List<BoardDTO> resultDTO = bService.BoardListPage(cri);
			model.addAttribute("boardList", resultDTO);
			
			logger.debug("연결된 뷰페이지(/views/login/main.jsp) 이동 ");
			
			return "/system/mainpage";
		}
		
		
		
		
		
		
		// http://localhost:8088/system/listAll2
		// http://localhost:8088/system/login
		
		
		
		// 게시판 LISTALL get
		@RequestMapping(value="/listAll2",method = RequestMethod.GET)
		public String listAllGET2(Model model,HttpSession session,Criteria cri) throws Exception {
			logger.debug("boardListAll GET 호출!");
			
			
			

			
			
			PageVO pageVO = new PageVO();
			pageVO.setCri(cri);
			pageVO.setTotalCount(bService.totalBoardCount());
			
			logger.debug("확인 :" + pageVO);
			
			model.addAttribute("pageVO", pageVO);
			
			List<BoardDTO> resultDTO = bService.BoardListPage(cri);
			model.addAttribute("boardList", resultDTO);
			
			
			return "/system/listAll2";
		}
		
		
		
		
		
		
		// 사원검색출력 GET

		@RequestMapping(value = "/searchmemberlist", method = RequestMethod.GET)
		public String searchmemberlistGET(@RequestParam(name = "searchOption", required = false, defaultValue = "option1") String searchOption,
		                                  @RequestParam(name = "searchWord", required = false) String searchWord,
		                                  Model model) {
		    logger.debug("/system/searchmemberlist 호출!");
		    List<MemberDTO> resultDTO = mService.searchMemberList(searchOption, searchWord);
		    model.addAttribute("memberlist", resultDTO);

		    logger.debug("commoncodelist.jsp 이동!");

		    return "/system/memberlist";
		}
		
		
		
		
		// 코드검색출력 GET
		
		@RequestMapping(value = "/searchcodelist", method = RequestMethod.GET)
		public String searchcommoncodelistGET(@RequestParam(name = "searchOption", required = false, defaultValue = "option1") String searchOption,
		                                  @RequestParam(name = "searchWord", required = false) String searchWord,
		                                  Model model) {
		    logger.debug("/system/commoncodelist 호출!");
		    List<CommonCodeDTO> resultDTO = cService.searchCommonCodeList(searchOption, searchWord);
		    model.addAttribute("commoncodelist", resultDTO);

		    logger.debug("commoncodelist.jsp 이동!");

		    return "/system/commoncodelist";
		}
		
		
		
		
	    
	    // 게시판 LISTALL get
		@RequestMapping(value="/listAll",method = RequestMethod.GET)
		public String listAllGET(Model model,HttpSession session,Criteria cri) throws Exception {
			logger.debug("boardListAll GET 호출!");
			
			
			session.setAttribute("viewcntCheck", true);
			
			
			
			PageVO pageVO = new PageVO();
			pageVO.setCri(cri);
			pageVO.setTotalCount(bService.totalBoardCount());
			
			logger.debug("확인 :" + pageVO);
			
			model.addAttribute("pageVO", pageVO);
			
			List<BoardDTO> resultDTO = bService.BoardListPage(cri);
			model.addAttribute("boardList", resultDTO);
			
			
			return "/system/listAll";
		}
		
		
		
	
		
		
		
		
		
		
		
		
		// http://localhost:8088/system/login

		//게시판 글 쓰기
		
		// 글쓰기 - POST
		@RequestMapping(value = "/insertBoard", method = RequestMethod.POST)
		public String registPOST(BoardDTO dto) throws Exception {
			logger.debug("폼submit -> registPOST() 호출 ");
			// 한글 인코딩(필터)
			// 전달정보 저장
			logger.debug(" dto : " + dto);

			// 서비스 - DB에 글쓰기(insert) 동작 호출
			bService.boardWrite(dto);
			logger.debug(" 글작성 완료! ");


			logger.debug("/system/listAll 이동");
			return "redirect:/system/listAll";
		}
		
		
		
		// 글 본문보기 - GET
		@RequestMapping(value="/readboard", method = RequestMethod.GET)
		public void readGET(@RequestParam("bno") int bno, Model model, HttpSession session) throws Exception {
			logger.debug("/system/read -> readGET()");
			logger.debug(" index "+ bno);
			
			
			if ((boolean) session.getAttribute("viewcntCheck")) {
				// 서비스 - bno에 해당하는 글 조회수 1증가
				// (페이지 호출당 1번씩 증가/read페이지 새로고침시 증가X)
				bService.incrementViewCnt(bno);

				session.setAttribute("viewcntCheck", false);
			}
			
	
			BoardDTO resultDTO = bService.getBoard(bno);
			model.addAttribute("resultDTO", resultDTO);
			
		}
		
		
		
//		// 게시판 글 수정 GET
//		@RequestMapping(value = "/modify", method = RequestMethod.GET)
//		public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception {
//			logger.debug("/board/modify -> modifyGET()호출");
//			logger.debug(" 수정할 글번호 : " + bno);
//
//			// 기존의 글정보를 가져와서 화면에 출력
//			BoardDTO resultDTO = bService.getBoard(bno);
//			// 글정보를 Model 객체 저장
//			model.addAttribute("resultDTO", resultDTO);
//			// 뷰페이지로 이동
//		}

		
		
		// 게시판 글 수정 - POST
		@RequestMapping(value = "/modify", method = RequestMethod.POST)
		public String modifyPOST(BoardDTO dto,Model model,HttpServletRequest request) throws Exception {
			
			int bno = Integer.parseInt(request.getParameter("bno"));
			model.addAttribute(bService.getBoard(bno));

			
			
			logger.debug(" /modify form -> modifyPOST()");
			// 전달된 정보 저장(수정할 정보)
			logger.debug(" 수정할 정보 " + dto);

			// 서비스 - 정보수정 동작
			int result = bService.boardModify(dto);
			// 처리 완료후 페이지 이동(리스트)
			// + 수정 완료! 리스트에서 출력

			return "redirect:/system/listAll";
		}
		
		
	
		
		
		
		// 게시판 글 정보 삭제 - POST
	
		// 글 삭제
		@RequestMapping(value = "/remove", method = RequestMethod.POST)
		public String removePOST(BoardDTO dto, Model model, HttpServletRequest request) throws Exception {
			logger.debug("/read form -> removePOST()");

			int bno = Integer.parseInt(request.getParameter("bno"));
			model.addAttribute(bService.getBoard(bno));
			
			// 서비스 - 글 삭제 동작
			bService.boardRemove(bno);
			// " 글 삭제 완료! " 메세지 출력

			return "redirect:/system/listAll";
		}
		
		
		
		
		// 글검색출력 GET

		@RequestMapping(value = "/searchboardlist", method = RequestMethod.GET)
		public String searchboardlistGET(@RequestParam(name = "searchOption", required = false, defaultValue = "option1") String searchOption,
		                                  @RequestParam(name = "searchWord", required = false) String searchWord,
		                                  Model model) {
		    logger.debug("/system/searchboardlist 호출!");
		    List<BoardDTO> resultDTO = bService.searchBoardList(searchOption, searchWord);
		    model.addAttribute("boardList", resultDTO);

		    logger.debug("commoncodelist.jsp 이동!");

		    return "/system/listAll";
		}
		
		
		
		
		
		
		
		
		
		
	
	
	
	// http://localhost:8088/system/login

}
