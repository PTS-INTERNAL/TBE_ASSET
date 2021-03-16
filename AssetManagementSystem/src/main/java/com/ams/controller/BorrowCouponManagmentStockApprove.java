package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.BorrowAssetSelectDao;
import com.ams.dao.LoanAssetInsertDao;
import com.ams.dao.assetgeneral.AssetGeneralUpdateStatusByRfidDao;
import com.ams.dao.borrow.BorrowCouponCountSelectDao;
import com.ams.dao.borrow.BorrowCouponSelectDao;
import com.ams.dao.borrow.BorrowCouponUpdateApproveAccountantDao;
import com.ams.dao.borrow.BorrowCouponUpdateApproveDeptDao;
import com.ams.dao.borrow.BorrowCouponUpdateApproveStockDao;
import com.ams.dao.borrow.BorrowCouponUpdateDeleteDao;
import com.ams.dao.borrow.BorrowCouponUpdateDisApproveAccountantDao;
import com.ams.dao.borrow.BorrowCouponUpdateDisApproveDeptDao;
import com.ams.dao.borrow.BorrowCouponUpdateDisApproveStockDao;
import com.ams.dao.borrow.BorrowCouponUpdateLoanCdDao;
import com.ams.dao.borrow.BorrowCouponUpdateStatusDao;
import com.ams.dao.loan.LoanCouponCountSelectDao;
import com.ams.dao.loan.LoanCouponInsertDao;
import com.ams.dao.loan.LoanCouponUpdateBorrowCdDao;
import com.ams.helper.PdfChoMuon;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowAssetModel;
import com.ams.model.BorrowCouponModel;
import com.ams.model.ExportBorrowMove;
import com.ams.model.LoanAssetModel;
import com.ams.model.LoanCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.ams.util.SystemControl;

@Controller
@RequestMapping("/BorrowCouponManagmentStockApprove")
public class BorrowCouponManagmentStockApprove {
	String TITLE = "MÀN HÌNH DUYỆT PHIẾU CHO MƯỢN TÀI SẢN SẢN CỦA KHO VẬN";
	@RequestMapping(params = "init",method = RequestMethod.POST)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String coupon_cd = request.getParameter("coupon_cd");

		BorrowCouponModel brCoupn = new BorrowCouponModel();
		brCoupn.setCoupon_cd(coupon_cd);

		BorrowCouponSelectDao selectBorrow = new BorrowCouponSelectDao(brCoupn);
		List<BorrowCouponModel> lst;
		try {
			lst = selectBorrow.excute();
			if (lst.size() == 1) {
				int i = 0;
				if(Common.isNotEmpty(lst.get(i).getDate_start()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_start(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if(Common.isNotEmpty(lst.get(i).getDate_end_schedule()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_schedule(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_end_schedule(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if(Common.isNotEmpty(lst.get(i).getDate_end_real()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_end_real(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}

				mv.addObject("coupon", lst.get(0));
			} else {
				Common.showMessageError(mv, "KHÔNG TÌM THẤY PHIẾU CHO MƯỢN");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Common.showMessageError(mv, "LỖI TÌM PHIẾU CHO MƯỢN");
		}
		
		
		//Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);
		
		mv.addObject("number_row", number_row.trim());
		BorrowAssetModel itemBorrow = new BorrowAssetModel(); 
		itemBorrow.setBorrowCoupon(brCoupn);
		
		BorrowAssetSelectDao lstBrrow = new BorrowAssetSelectDao(itemBorrow);
		List<BorrowAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			mv.addObject("lstBr", lstSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowCouponManagmentStockApprove.jsp");
		return mv;
	}
	
	@RequestMapping(params = "approveExport",method = RequestMethod.POST)
	public ModelAndView approveExport(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String coupon_cd = request.getParameter("coupon_cd");

		BorrowCouponModel brCoupn = new BorrowCouponModel();
		brCoupn.setCoupon_cd(coupon_cd);

		BorrowCouponSelectDao selectBorrow = new BorrowCouponSelectDao(brCoupn);
		List<BorrowCouponModel> lst;
		try {
			lst = selectBorrow.excute();
			if (lst.size() == 1) {
				int i = 0;
				if(Common.isNotEmpty(lst.get(i).getDate_start()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_start(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if(Common.isNotEmpty(lst.get(i).getDate_end_schedule()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_schedule(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_end_schedule(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if(Common.isNotEmpty(lst.get(i).getDate_end_real()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_end_real(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}

				mv.addObject("coupon", lst.get(0));
			} else {
				Common.showMessageError(mv, "KHÔNG TÌM THẤY PHIẾU CHO MƯỢN");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Common.showMessageError(mv, "LỖI TÌM PHIẾU CHO MƯỢN");
		}
		
		
		//Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);
		
		mv.addObject("number_row", number_row.trim());
		BorrowAssetModel itemBorrow = new BorrowAssetModel(); 
		itemBorrow.setBorrowCoupon(brCoupn);
		
		BorrowAssetSelectDao lstBrrow = new BorrowAssetSelectDao(itemBorrow);
		List<BorrowAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			mv.addObject("lstBr", lstSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		request.getSession().setAttribute("isExport", "7777");
		mv.setViewName("views/BorrowCouponManagmentDeptApproveExport.jsp");
		return mv;
	}
	
	
	@RequestMapping(params = "approveExportPrint",method = RequestMethod.POST)
	public ModelAndView approveExportPrint(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String coupon_cd = request.getParameter("coupon_cd");

		BorrowCouponModel brCoupn = new BorrowCouponModel();
		brCoupn.setCoupon_cd(coupon_cd);

		BorrowCouponSelectDao selectBorrow = new BorrowCouponSelectDao(brCoupn);
		List<BorrowCouponModel> lst = null;
		try {
			lst = selectBorrow.excute();
			if (lst.size() == 1) {
				int i = 0;
				if(Common.isNotEmpty(lst.get(i).getDate_start()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_start(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if(Common.isNotEmpty(lst.get(i).getDate_end_schedule()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_schedule(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_end_schedule(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if(Common.isNotEmpty(lst.get(i).getDate_end_real()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_end_real(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}

				mv.addObject("coupon", lst.get(0));
			} else {
				Common.showMessageError(mv, "KHÔNG TÌM THẤY PHIẾU CHO MƯỢN");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Common.showMessageError(mv, "LỖI TÌM PHIẾU CHO MƯỢN");
		}
		
		
		//Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);
		
		mv.addObject("number_row", number_row.trim());
		BorrowAssetModel itemBorrow = new BorrowAssetModel(); 
		itemBorrow.setBorrowCoupon(brCoupn);
		
		BorrowAssetSelectDao lstBrrow = new BorrowAssetSelectDao(itemBorrow);
		List<BorrowAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			mv.addObject("lstBr", lstSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		ExportBorrowMove export = new ExportBorrowMove(lstSearch, lst.get(0));
		return new ModelAndView(new PdfChoMuon(), "object", export);
	}
	
	
	@RequestMapping(params = "approve",method = RequestMethod.POST)
	public ModelAndView approve(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String coupon_cd = request.getParameter("coupon_cd");

		BorrowCouponModel brCoupn = new BorrowCouponModel();
		brCoupn.setCoupon_cd(coupon_cd);
		
		String status_device = "ok";
		if(Common.isEmpty(status_device))
		{
			Common.showMessageError(mv, "KHÔNG XÁC NHẬN TRẠNG THÁI CỦA TÀI SẢN TRƯỚC KHI DUYỆT");
		} else {
			brCoupn.setStatus("4");
			brCoupn.setApprove_comment(status_device);
			brCoupn.setUpdate_dt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
			SystemControl sys = new SystemControl(request);
			brCoupn.setUpdate_user(sys.EmployeeCD);
			BorrowCouponUpdateApproveStockDao updateApprove = new BorrowCouponUpdateApproveStockDao(brCoupn);
			try {
				updateApprove.excute();
				Common.showMessageNotification(mv, "PHÊ DUYỆT THÀNH CÔNG");
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		BorrowCouponSelectDao selectBorrow = new BorrowCouponSelectDao(brCoupn);
		List<BorrowCouponModel> lst;
		String LoanCouponCd = "";
		try {
			lst = selectBorrow.excute();
			if (lst.size() == 1) {
				
				//INSERT DỮ LIỆU PHIẾU  MƯỢN QUA KHÁCH HÀNG
				BorrowCouponModel brItem = lst.get(0);
				LoanCouponModel loanItem = new LoanCouponModel();
				String cmpn_cd = Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD);
				//Lấy số mã lệnh
				try {
					LoanCouponCountSelectDao BorrowCountSelect = new LoanCouponCountSelectDao(cmpn_cd);
					String  number_no = BorrowCountSelect.getBorrowCouponCount()+"";
					if(number_no.trim().length()==1)
					{
						int No_cd = Integer.parseInt(number_no.trim()) + 1;
						loanItem.setNumber_no("0"+No_cd +"/M");

					} else {
						int No_cd = Integer.parseInt(number_no.trim()) + 1;
						loanItem.setNumber_no(No_cd +"/M");

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				LoanCouponCd = Common.getDateCurrent(ParamsConstants.CD_FULL_FORMAT);
				loanItem.setCoupon_cd(LoanCouponCd);
				loanItem.setCmpn_client(brItem.getCmpn_master());
				loanItem.setCmpn_master(brItem.getCmpn_client());
				loanItem.setDept_client(brItem.getDept_master());
				loanItem.setDept_master(brItem.getDept_client());
				loanItem.setDate_start(brItem.getDate_start());
				loanItem.setDate_end_schedule(brItem.getDate_end_schedule());
				loanItem.setReason(brItem.getReason());
				loanItem.setInsert_user(brItem.getInsert_user());
				loanItem.setInsert_dt(Common.getDateCurrent(ParamsConstants.CD_FULL_FORMAT));
				loanItem.setCoupon_borrow_cd(coupon_cd);
				
				LoanCouponInsertDao insert = new LoanCouponInsertDao(loanItem);
				insert.excute();
				
				LoanCouponUpdateBorrowCdDao exce = new LoanCouponUpdateBorrowCdDao(loanItem);
				exce.excute();
				
				brItem.setCoupon_cd(coupon_cd);
				brItem.setCoupon_loan_cd(LoanCouponCd);
				BorrowCouponUpdateLoanCdDao excel = new  BorrowCouponUpdateLoanCdDao(brItem);
				excel.excute();
				
				//INSERT DỮ LIỆU TÀI SẢN  MƯỢN QUA KHÁCH HÀNG
				
				
				//Hiển thị lại màn hình
				int i = 0;
				if(Common.isNotEmpty(lst.get(i).getDate_start()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_start(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if(Common.isNotEmpty(lst.get(i).getDate_end_schedule()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_schedule(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_end_schedule(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if(Common.isNotEmpty(lst.get(i).getDate_end_real()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_end_real(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}

				mv.addObject("coupon", lst.get(0));
			} else {
				Common.showMessageError(mv, "KHÔNG TÌM THẤY PHIẾU CHO MƯỢN");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Common.showMessageError(mv, "LỖI TÌM PHIẾU CHO MƯỢN");
		}
		
		
		//Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);
		
		mv.addObject("number_row", number_row.trim());
		BorrowAssetModel itemBorrow = new BorrowAssetModel(); 
		itemBorrow.setBorrowCoupon(brCoupn);
		
		BorrowAssetSelectDao lstBrrow = new BorrowAssetSelectDao(itemBorrow);
		List<BorrowAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			mv.addObject("lstBr", lstSearch);
			
			for(int i =0;i<lstSearch.size();i++)
			{
				AssetGeneralModel ass = new AssetGeneralModel();
				ass.setRFID(lstSearch.get(i).getAsset().getRFID());
				ass.setStatus("2");
				
				AssetGeneralUpdateStatusByRfidDao ex = new AssetGeneralUpdateStatusByRfidDao(ass);
				ex.excute();
				
				//INSERT DỮ LIỆU TÀI SẢN  MƯỢN QUA KHÁCH HÀNG
				LoanAssetModel itemModel = new LoanAssetModel();
				itemModel.setAsset(lstSearch.get(i).getAsset());
				itemModel.setDelete_fg("0");
				itemModel.setStatus("1");
				itemModel.getLoanCoupon().setCoupon_cd(LoanCouponCd);
				itemModel.setAsseseries(lstSearch.get(i).getAsseseries());
				itemModel.setUserInsert(lstSearch.get(i).getUserInsert());
				itemModel.setInsertDt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
				
				LoanAssetInsertDao insert = new LoanAssetInsertDao(itemModel);
				insert.excute();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowCouponManagmentStockApprove.jsp");
		return mv;
	}
	
	@RequestMapping(params = "disApprove",method = RequestMethod.POST)
	public ModelAndView disApprove(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String coupon_cd = request.getParameter("coupon_cd");

		BorrowCouponModel brCoupn = new BorrowCouponModel();
		brCoupn.setCoupon_cd(coupon_cd);
		
		String reason_not_allow = request.getParameter("reason_not_allow");
		if(Common.isEmpty(reason_not_allow))
		{
			Common.showMessageError(mv, "VUI LÒNG NHẬP LÝ DO KHÔNG DUYỆT CHO MƯỢN");
		} else {
			brCoupn.setStatus("8");
			brCoupn.setReasonNotAllow(reason_not_allow);
			brCoupn.setUpdate_dt(Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
			SystemControl sys = new SystemControl(request);
			brCoupn.setUpdate_user(sys.EmployeeCD);
			BorrowCouponUpdateDisApproveStockDao updateApprove = new BorrowCouponUpdateDisApproveStockDao(brCoupn);
			try {
				updateApprove.excute();
				Common.showMessageNotification(mv, "XÁC NHẬN KHÔNG DUYỆT THÀNH CÔNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		BorrowCouponSelectDao selectBorrow = new BorrowCouponSelectDao(brCoupn);
		List<BorrowCouponModel> lst;
		try {
			lst = selectBorrow.excute();
			if (lst.size() == 1) {
				int i = 0;
				if(Common.isNotEmpty(lst.get(i).getDate_start()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_start(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_start(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if(Common.isNotEmpty(lst.get(i).getDate_end_schedule()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_schedule(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_end_schedule(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}
				if(Common.isNotEmpty(lst.get(i).getDate_end_real()))
				{
					try {
					String dateEndConvert = Common.ConvertStringToDateStr(lst.get(i).getDate_end_real(), "yyyy-mm-dd","dd/mm/yyyy");
					lst.get(i).setDate_end_real(dateEndConvert);
					} catch (ParseException e1) {
						mv.addObject(ParamsConstants.MESSAGE_ERROR, "DỮ LIỆU TỒN TẠI ĐỊNH DẠNG NGÀY KHÔNG HỢP LỆ");
					}
				}

				mv.addObject("coupon", lst.get(0));
			} else {
				Common.showMessageError(mv, "KHÔNG TÌM THẤY PHIẾU CHO MƯỢN");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Common.showMessageError(mv, "LỖI TÌM PHIẾU CHO MƯỢN");
		}
		
		
		//Set view layout
		SystemControl system = new SystemControl(request);
		String number_row = system.getParameter(ParamsConstants.ROW_BORROW_NUMBER);
		
		mv.addObject("number_row", number_row.trim());
		BorrowAssetModel itemBorrow = new BorrowAssetModel(); 
		itemBorrow.setBorrowCoupon(brCoupn);
		
		BorrowAssetSelectDao lstBrrow = new BorrowAssetSelectDao(itemBorrow);
		List<BorrowAssetModel> lstSearch = new ArrayList<>();	
		try {
			lstSearch = lstBrrow.excute();
			mv.addObject("lstBr", lstSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject(ParamsConstants.TITLE_SCREEN, TITLE);
		mv.setViewName("views/BorrowCouponManagmentStockApprove.jsp");
		return mv;
	}
	
	@RequestMapping(params = "back",method = RequestMethod.POST)
	public String back()
	{
		return "redirect:/StockBorrowCouponManagement";
	}

}
