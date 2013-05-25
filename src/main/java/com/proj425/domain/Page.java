package com.proj425.domain;

public class Page {

	private int totalRows; // total count from database
	private int pageSize = 10; // page size
	private int pageIndex; // current page
	private int totalPage;
	private int beginIndex;
	private int endIndex;
	
	

	public Page() {
		super();
	}

	public Page(int totalRows, int pageIndex) {
		super();
		this.totalRows = totalRows;
		this.pageIndex = pageIndex;
	}

	public Page(int totalRows, int pageIndex, int pageSize) {
		super();
		this.totalRows = totalRows;
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		
	}

	// initial all attributes.
	public void init() {
		totalPage = totalRows / pageSize + ((totalRows % pageSize) > 0 ? 1 : 0);

		if (pageIndex > totalPage)
			pageIndex = totalPage;
		if (pageIndex < 1)
			pageIndex = 1;

		beginIndex = (pageIndex - 1) * pageSize;
		endIndex = beginIndex + pageSize;
		if (endIndex > totalRows)
			endIndex = totalRows;

	}

	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getBeginIndex() {
		return beginIndex;
	}
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
