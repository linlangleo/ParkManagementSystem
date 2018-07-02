package cn.parkmanasys.util;

public class PageSupport {
	//当前页码-来自于用户输入
	private int currentPageNo;
	//页面容量
	private int pageSize;
	//总数量（表）
	private int totalCount;
	//总页数-totalCount/pageSize（+1）
	private int totalPageCount;
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		if(currentPageNo > 0){
			this.currentPageNo = currentPageNo;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize > 0){
			this.pageSize = pageSize;
		}
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		if(totalCount > 0){
			this.totalCount = totalCount;
		}
	}
	public int getTotalPageCount() {
		return this.setTotalPageCountByRs();
	}
	public void setTotalPageCount() {
		this.totalPageCount = this.setTotalPageCountByRs();
	}
	
	
	private int setTotalPageCountByRs(){
		if(this.totalCount % this.pageSize == 0){
			this.totalPageCount = this.totalCount / this.pageSize;
			return this.totalPageCount;
		}else if(this.totalCount % this.pageSize > 0){
			this.totalPageCount = this.totalCount / this.pageSize + 1;
			return this.totalPageCount;
		}else{
			this.totalPageCount = 0;
			return this.totalPageCount;
		}
	}
	
}
