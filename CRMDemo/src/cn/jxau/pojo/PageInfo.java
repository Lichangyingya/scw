package cn.jxau.pojo;

import java.util.List;

public class PageInfo {

	private int pageSize;//每页显示条数
	private int pageNumber;//当前页
	private long totalPage;//总页数
	private Long total=0l;//总条数
	private List<?> list;
	private int pageStart;
	private String page;

	public PageInfo() {
	}

	public PageInfo(int pageSize, int pageNumber, long totalPage, Long total, List<?> list,int pageStart) {
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.totalPage = totalPage;
		this.total = total;
		this.list = list;
		this.pageStart = pageStart;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}


	@Override
	public String toString() {
		return "PageInfo{" +
				"pageSize=" + pageSize +
				", pageNumber=" + pageNumber +
				", totalPage=" + totalPage +
				", total=" + total +
				", list=" + list +
				", pageStart=" + pageStart +
				", page='" + page + '\'' +
				'}';
	}
}
