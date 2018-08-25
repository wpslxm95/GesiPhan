package controller.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 리퀘스트 영역에 저장
		req.setAttribute("active", "INDEX");

		// 메인페이지로 이동
		req.getRequestDispatcher("/TeamStudy/FreeHome.jsp").forward(req, resp);
	}
}
///JSPPro/WebContent/TeamStudy/FreeHome.jsp
///JSPPro/WebContent/TeamStudy/FreeHome.jsp