package model.FreeBoard;

public class FreeBoardDTO {

	private String no;
	private String category;
	private String title;
	private String content;
	private String viewCount;
	private java.sql.Date enterDate;
	private String pass;
	//포르그램의 효율으루 위한 추가 속성(멤버변수)
	private String name;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getViewCount() {
		return viewCount;
	}
	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}
	public java.sql.Date getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(java.sql.Date enterDate) {
		this.enterDate = enterDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
}
