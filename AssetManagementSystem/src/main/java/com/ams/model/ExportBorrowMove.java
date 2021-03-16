package com.ams.model;

import java.util.List;

public class ExportBorrowMove {
	
	private List<BorrowAssetModel> lstBorrow;
	private BorrowCouponModel borrowCoupon;
	
	public BorrowCouponModel getBorrowCoupon() {
		return borrowCoupon;
	}
	public void setBorrowCoupon(BorrowCouponModel borrowCoupon) {
		this.borrowCoupon = borrowCoupon;
	}
	private String codeExport;
	public String getCodeExport() {
		return codeExport;
	}
	public void setCodeExport(String codeExport) {
		this.codeExport = codeExport;
	}
	private String companyMaster;
	private String companyClient;
	private String confirmComment;
	public List<BorrowAssetModel> getLstBorrow() {
		return lstBorrow;
	}
	public void setLstBorrow(List<BorrowAssetModel> lstBorrow) {
		this.lstBorrow = lstBorrow;
	}
	public String getCompanyMaster() {
		return companyMaster;
	}
	public void setCompanyMaster(String companyMaster) {
		this.companyMaster = companyMaster;
	}
	public String getCompanyClient() {
		return companyClient;
	}
	public void setCompanyClient(String companyClient) {
		this.companyClient = companyClient;
	}
	public String getConfirmComment() {
		return confirmComment;
	}
	public void setConfirmComment(String confirmComment) {
		this.confirmComment = confirmComment;
	}
	public ExportBorrowMove(List<BorrowAssetModel> lstBorrow, String companyMaster, String companyClient,
			String confirmComment, String code) {
		super();
		this.lstBorrow = lstBorrow;
		this.companyMaster = companyMaster;
		this.companyClient = companyClient;
		this.confirmComment = confirmComment;
		this.codeExport = code;
	}
	
	public ExportBorrowMove(List<BorrowAssetModel> lstBorrow, BorrowCouponModel coupon) {
		super();
		this.lstBorrow = lstBorrow;
		this.borrowCoupon = coupon;
	}
	
	
	

}
