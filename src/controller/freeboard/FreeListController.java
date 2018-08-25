package controller.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FreeBoard.FreeBoardDAO;
import model.FreeBoard.FreeBoardDTO;
import model.dataroom.DataRoomDAO;
import model.dataroom.DataRoomDTO;

public class FreeListController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("포스트방식으로 요청 들어옴");
		// 가]사용자 요청을 받는다
		// 나]요청을 분석한다.
		// 다]모델에서 필요한 로직 호출해서 결과값이 있으면 받기
		FreeBoardDAO dao = new FreeBoardDAO(req.getServletContext());
		List<FreeBoardDTO> list = dao.selectList();
		dao.close();
		// 라]결과값이 있으면 리퀘스트 영역에 저장
		req.setAttribute("list", list);
		req.setAttribute("active", "DATAROOM");
		// 마]결과값을 뿌려주거나 이동할 뷰(JSP페이지) 선택후 포워딩
		// 뷰선택]
		RequestDispatcher dispatcher = req.getRequestDispatcher("/TeamStudy/FreeList.jsp");
		// 포워딩]
		dispatcher.forward(req, resp);
	}////////////////////

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("겟방식으로 요청 들어옴");
		doPost(req, resp);
	}
}
///JSPPro/WebContent/TeamStudy/FreeList.jsp