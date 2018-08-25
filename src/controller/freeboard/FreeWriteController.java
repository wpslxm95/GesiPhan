package controller.freeboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.sun.glass.ui.Application;

import model.PBKDF2;
import model.FreeBoard.FreeBoardDAO;
import model.FreeBoard.FreeBoardDTO;
import model.dataroom.FileUtils;

public class FreeWriteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 3]요청분석- 입력폼 요청
		// 4]모델호출 및 결과값 받기
		// 5]결과값이 있으면 ,리퀘스트 영역에 저장
		req.setAttribute("active", "FREEBOARD");
		// 6]뷰 선택
		/*
		 * 절대경로 지정시 포워딩:컨텍스트 루트 경로 미 포함 리다이렉트:컨텍스트 루트 포함. 단, server.xml에 Context태그의
		 * path속성 값을 지울시에는 신경 쓸 필요없다.
		 */
		// 포워드]
		req.getRequestDispatcher("/TeamStudy/FreeWrite.jsp").forward(req, resp);
		// 리다이렉트]
		// resp.sendRedirect(req.getContextPath()+"/DataRoom13/Write.jsp");
	}///////////////////////
		// [입력 처리 즉 파일 업로드 및 데이타베이스 입력]

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int successFail;
		PrintWriter out = null;
		String name=req.getParameter("name");
		String title=req.getParameter("title");
		String pass=req.getParameter("pass");
		String content=req.getParameter("content");
		String category=req.getParameter("category");
		
		FreeBoardDAO dao = new FreeBoardDAO(req.getServletContext());
		FreeBoardDTO dto = new FreeBoardDTO();
		
		dto.setContent(content);
		dto.setName(name);
		dto.setPass(pass);
		dto.setTitle(title);
		dto.setCategory(category);
		successFail = dao.insert(dto);

		dao.close();
		if(successFail==1){
			//resp.sendRedirect("/TeamStudy/FreeList.jsp");
			req.getRequestDispatcher("/FreeBoard/FreeList.kosmo").forward(req, resp);

		}
		else{
			out.println("<script>");
			out.println("alert('입력 실패했어요');");
			out.println("history.back();");
			out.println("</script>");
			
		}
		/*// 한글처리]
		req.setCharacterEncoding("UTF-8");
		// 3]요청분석- 입력처리 요청
		// 4]모델호출 및 결과값 받기
		// 파일 업로드와 관련된 모델(비지니스 로직) 호출
		MultipartRequest mr = FileUtils.upload(req, req.getServletContext().getRealPath("/Upload"));
		// DB입력 성공시에는 1,실패시 0, 파일용량 초과시에는 -1 저장
		int successFail;
		// 오류시 입력값 보존을 위한 변수 선언]
		String name, title, pass = null, content, category;
		if (mr != null) {// 파일 업로드 성공일때 DB 입력처리]
			// 기타 파라미터 받아서 테이블에 입력처리]
			name = mr.getParameter("name");
			title = mr.getParameter("title");
			// 비밀번호는 암호화]
			try {
				pass = PBKDF2.createHash(mr.getParameter("pass"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			content = mr.getParameter("content");
			category=mr.getParameter("category");
			// 데이타베이스 CRUD작업과 관련된 모델 호출]
			FreeBoardDAO dao = new FreeBoardDAO(req.getServletContext());
			FreeBoardDTO dto = new FreeBoardDTO();

			dto.setContent(content);
			dto.setName(name);
			dto.setPass(pass);
			dto.setTitle(title);
			dto.setCategory(category);
			successFail = dao.insert(dto);

			dao.close();
		} // if
		else
			successFail = -1;
		// 5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
		// 5-1]DB입력 성공 여부 및 파일용량 초과 판단용 속성 저장
		req.setAttribute("successFail", successFail);
		// 5-2]컨트롤러 구분용-입력:INS,수정:EDT,삭제:DEL
		req.setAttribute("WHERE", "INS");
		// 6]뷰로 이동
		if (successFail == 1) {// 파일업로드 및 DB입력 성공
			// [이동방법1]-바로 목록으로 이동:반드시 List.jsp가 아닌 List.kosmo로
			//req.getRequestDispatcher("/FreeBoard/FreeList.kosmo").forward(req, resp);
			// [이동방법2]-메시지 뿌려주는 페이지로 이동후 목록으로 이동
			req.getRequestDispatcher("/TeamStudy/FreeMessage.jsp").forward(req, resp);
		} else {// 파일 용량 초과 혹은 파일업로드는 됬으나 DB입력 실패한 경우
				// [이동방법1]-자스의 history.back()으로 입력폼으로 이동
				// 서블릿에서 브라우저로 직접 출력시 -한글처리]

			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('" + (successFail == 0 ? "DB입력실패" : "파일용량 초과") + "'); ");
			out.println("history.back();");
			out.println("</script>");

			// [이동방법2]메시지 뿌려주는 페이지로 이동후 다시 입력폼으로
			req.getRequestDispatcher("/TeamStudy/FreeMessage.jsp").forward(req, resp);

			// 이동방법3]비밀번호도 기존값 유지하기 위한 방법-역시 입력폼으로 이동
			// ※MultipartRequest가 파라미터를 가로채니까
			// 포워드하더라도 전달안됨 그래서 영역에 저장]
			
			 * if(mr == null) { //"UTF-8"생략시 파라미터로 받은 한글이 깨짐] mr= new MultipartRequest( req,
			 * req.getServletContext().getRealPath("/Upload"),"UTF-8");
			 * 
			 * 
			 * } req.setAttribute("name", mr.getParameter("name"));
			 * req.setAttribute("title", mr.getParameter("title"));
			 * req.setAttribute("content", mr.getParameter("content"));
			 * req.setAttribute("pass", mr.getParameter("pass"));
			 * req.setAttribute("attachedfile",mr.getOriginalFileName("attachedfile"));
			 * req.setAttribute("error", sucOrFail==0?"Input Failure":"Exceed File Size");
			 * req.getRequestDispatcher("/DataRoom13/Write.jsp").forward(req, resp);
			 
		}*/
	}///////////////////////////////

	/*
	 * protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
	 * ServletException, IOException {
	 * 
	 * FreeBoardDAO dao = new FreeBoardDAO(req.getServletContext());
	 * req.setCharacterEncoding("UTF-8");
	 * req.getRequestDispatcher("/FreeBoard/FreeWrite.jsp").forward(req, resp);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Override protected void doPost(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException {
	 * 
	 * FreeBoardDAO dao = new FreeBoardDAO(req.getServletContext()); // 1]한글처리
	 * req.setCharacterEncoding("UTF-8"); // 2]파라미터 받기 String name, title, pass =
	 * null, content; name = req.getParameter("name"); title =
	 * req.getParameter("title"); content = req.getParameter("content"); String
	 * category=req.getParameter("category"); // 3]데이타를 전달할 DTO객체 생성및 데이타 설정
	 * FreeBoardDTO dto = new FreeBoardDTO(); dto.setTitle(title);
	 * dto.setContent(content); dto.setCategory(category); dto.setName(name);
	 * dto.setPass(pass); dto.setTitle(title); dao.insert(dto); // 4]CRUD작업용 DAO계열
	 * 객체 생성 int affected = dao.insert(dto); dao.close();
	 * 
	 * if (affected == 1) {
	 * req.getRequestDispatcher("/FreeBoard/FreeList.kosmo").forward(req, resp);
	 * 
	 * } else { resp.setContentType("text/html; charset=UTF-8"); PrintWriter out =
	 * resp.getWriter(); out.println("<script>");
	 * out.println("alert('파일입력에 실패하였습니다'); "); out.println("history.back();");
	 * out.println("</script>");
	 * 
	 * } } }
	 */
}
