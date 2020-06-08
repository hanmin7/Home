package javaTest.src.javaTest.com.yedam.exam;;

public class Board {
	//게시판 프로그램. 클래스, 인터페이스, 구현객체
	private int num;
	private String title;
	private String contents;
	
	Board(int num, String title, String contents) {
		this.num = num;
		this.title = title;
		this.contents = contents;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return num + " | " +title + " : " + contents;
	}
	
	
}
