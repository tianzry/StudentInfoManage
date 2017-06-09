package cn.tianzry.model;

public class PageBean {

	private int page; // ��ҳ��
	private int rows; // ÿҳ�ļ�¼��
	private int start; // ��ʼҳ��
	
	public PageBean(int page, int rows) {
		super();
		this.page = page;
		this.rows = rows;
	}
	
	public int getPage() {
		return page;
	}

	public int getRows() {
		return rows;
	}
	public int getStart() {
		return (page - 1) * rows;
	}

	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
}
