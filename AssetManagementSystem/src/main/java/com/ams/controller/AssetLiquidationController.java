package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetLiquidationModel;
import com.ams.dao.AssetLiquidationSelectDao;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;

@Controller
@RequestMapping("/AssetLiquidationManagement")
public class AssetLiquidationController {
	
	String TTITLE = "MÀN HÌNH QUẢN LÝ THANH LÝ TÀI SẢN";
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		
		ModelAndView mv  =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TTITLE);
		AssetLiquidationModel asset = new AssetLiquidationModel();
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		AssetLiquidationSelectDao select = new AssetLiquidationSelectDao(asset);
		try {
			List<AssetLiquidationModel> lstAsset;
			
				lstAsset = select.excute();
		
			
			if(lstAsset.size()>0)
			{
				mv.addObject("lstAsset", lstAsset);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	
		mv.setViewName("views/AssetLiquidationManagement.jsp");
		
		return mv;
	}
	
	@RequestMapping(params="search", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request)
	{
		ModelAndView mv  =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TTITLE);
		AssetLiquidationModel asset = new AssetLiquidationModel();
		asset.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		AssetLiquidationSelectDao select = new AssetLiquidationSelectDao(asset);
		try {
			List<AssetLiquidationModel> lstAsset;
			
				lstAsset = select.excute();
		
			
			if(lstAsset.size()>0)
			{
				mv.addObject("lstAsset", lstAsset);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	
		mv.setViewName("views/AssetLiquidationManagement.jsp");
		
		return mv;
	}
	
	@RequestMapping(params="register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request)
	{
		ModelAndView mv  =new ModelAndView();
		mv.addObject(ParamsConstants.TITLE_SCREEN, TTITLE);
		mv.setViewName("redirect:/AssetLiquidationRegister");
		
		return mv;
	}
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/FeatureSystem");
			
		return mv;
	}

}
