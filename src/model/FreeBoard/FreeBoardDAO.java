package model.FreeBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import model.dataroom.DataRoomDTO;

public class FreeBoardDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	public FreeBoardDAO(ServletContext context) {
		try {
			// 드라이버 로딩]
			Class.forName(context.getInitParameter("ORACLE_DRIVER"));
			// 데이타베이스 연결]
			conn = DriverManager.getConnection(context.getInitParameter("ORACLE_URL"), "SAMPLE", "sample");
			System.out.println("데이타베이스 연결 성공");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * // DB connect - connection pool 이용 try { // try 1 Context ctx = new
	 * InitialContext(); DataSource source = (DataSource)
	 * ctx.lookup(context.getInitParameter("JNDI_ROOT") + "/jdbc/jsp"); conn =
	 * source.getConnection(); } catch (NamingException | SQLException e) { // try 1
	 * ~ catch // TODO Auto-generated catch block e.printStackTrace(); } // try 1 ~
	 * catch }
	 */
	///////////////////////////////////////////////////////////////////////
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<FreeBoardDTO> selectList() {
		/// public List<FreeBoardDTO> selectList(Map map) {
		// 리스트출력용
		List<FreeBoardDTO> list = new Vector<FreeBoardDTO>();
		System.out.println("rs1들어옴");
		String sql = "select * from freeboard order by enterdate desc";
		// 페이징 적용 전 쿼리
		// String sql = "select * from(select T.*,rownum R from(select * from dataroom
		// order by postdate desc)T) where R between ? and ?";
		try {
			psmt = conn.prepareStatement(sql);
			// 시작 및 끝 행 번호 설정
			// psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			// psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
			rs = psmt.executeQuery();
			System.out.println("rs2들어옴");
			while (rs.next()) {
				System.out.println("rs3들어옴");
				FreeBoardDTO dto = new FreeBoardDTO();

				dto.setNo(rs.getString(1));
				dto.setCategory(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setName(rs.getString(5));
				dto.setPass(rs.getString(6));
				dto.setEnterDate(rs.getDate(7));
				dto.setViewCount(rs.getString(8));
				list.add(dto);
				/*
				 * create table freeboard( no number(10) not null, category nvarchar2(20), title
				 * varchar2(100) not null, content nvarchar2(2000) not null, name nvarchar2(20)
				 * not null, pass varchar2(20) not null, enterdate date not null, viewcount
				 * number default 0 not null );
				 */
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		return list;
	}

	public int insert(FreeBoardDTO dto) {
		int affected = 0;
		String sql = "INSERT INTO freeboard(no,category, title, content,name,pass,enterdate) VALUES(seq_free.nextval,?,?,?,?,?,sysdate)";
		// insert into freeboard
		// values(seq_free.nextval,'a','a','a','a','1',sysdate,'1');
		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dto.getCategory());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getName());
			psmt.setString(5, dto.getPass());

			affected = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insert 실패");
		}

		return affected;
	}//////////////////////////////////

	public FreeBoardDTO selectOne(String no) {
		// 상세보기용
		FreeBoardDTO dto = null;
		String sql = "select * from freeboard where no=?";
		System.out.println("메소드 들어감1");
		try {
			System.out.println("메소드 들어감2");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			System.out.println("메소드 들어감3");
			if (rs.next()) {
				System.out.println("메소드 들어감4");
				dto = new FreeBoardDTO();
				dto.setNo(no);
				dto.setCategory(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setName(rs.getString(5));
				dto.setEnterDate(rs.getDate(5));


			
				
				System.out.println(dto.getContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}

		return dto;

	}
	
	public int update(FreeBoardDTO dto) {
		int affected=0;
		String sql="update freeboard set name=?,title=?,pass=?,content=?,category=? where no=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getPass());
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getCategory());
			psmt.setString(6, dto.getNo());
			affected=psmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return affected;
	}
}
