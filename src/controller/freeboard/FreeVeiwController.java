package controller.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FreeBoard.FreeBoardDAO;
import model.FreeBoard.FreeBoardDTO;


public class FreeVeiwController extends HttpServlet {
	// 2]서비스혹은 두겟/두포스트 호출
	// Service는 Get방식이면 doGet호출 Post면 doPost호출
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 3]요청분석
		String no = req.getParameter("no");
		// 모델 호출 및 결과값 받기
		FreeBoardDAO dao = new FreeBoardDAO(req.getServletContext());
		FreeBoardDTO record = dao.selectOne(no);
		// 내용 줄바꿈
		// record.setContent(record.getContent().replace("\r\n", "<br/>"));
		dao.close();
		// 5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("record", record);
		// 6]뷰 선택후 포워딩
		req.getRequestDispatcher("/TeamStudy/FreeView.jsp").forward(req, resp);

	}
}
