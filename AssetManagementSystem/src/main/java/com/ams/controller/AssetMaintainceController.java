package com.ams.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dao.AssetMaintanceDetailSelectDao;
import com.ams.dao.AssetMaintanceSelectDao;
import com.ams.model.AssetMaintanceModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;
import com.ams.util.SessionConstants;
import com.sun.xml.internal.bind.util.Which;

@Controller
@RequestMapping("/AssetMaintainceManagement")
public class AssetMaintainceController {
	
	String TTITLE = "MÀN HÌNH QUẢN LÝ BẢO TRÌ TÀI SẢN";
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request)
	{
		ModelAndView mv  =new ModelAndView();
		
		AssetMaintanceModel mainSet = new  AssetMaintanceModel();
		mainSet.getAsset().setCompany_CD(Common.getSessionValue(request, SessionConstants.SUB_SYSTEM_CD));
		AssetMaintanceSelectDao mainSelect = new AssetMaintanceSelectDao(mainSet);
		try {
			List<AssetMaintanceModel> lstAsset = mainSelect.excute();
			if(lstAsset.size()>0)
			{
				List<AssetMaintanceModel> lstAssetNew = new ArrayList<AssetMaintanceModel>();
				for (int i = 0; i < lstAsset.size(); i++) {
					AssetMaintanceModel asset = lstAsset.get(i);
					if(asset.getAsset().getMaintaince().trim().length()>0)
					{
						try {
							Date dateLast = Common.convertStringToDate(asset.getMonthMaintaince(), "MM/dd/yyyy");
							try
							{
								int limit = dateLast.getMonth()+1+1 + Integer.parseInt(asset.getAsset().getMaintaince().trim());
								AssetMaintanceModel aset;
								while(limit<=12)
								{
									aset = new AssetMaintanceModel();
									aset.setAsset(asset.getAsset());
									aset.setMonthMaintaince(limit+"");
									
									asset.setMonth(limit+"");
									
									String dateS = Common.getDateCurrent("MM/dd/yyyy");
									Date dateLastes = Common.convertStringToDate(dateS, "MM/dd/yyyy");
									if(limit<dateLastes.getMonth()+1)
									{
										AssetMaintanceDetailSelectDao selectDi = new AssetMaintanceDetailSelectDao(aset);
										List<AssetMaintanceModel> lsttemp = selectDi.excute();
										if(lsttemp.size()>0)
										{
											aset.setStatus("2");
										}
										else
										{
											aset.setStatus("3");
										}
									}
									else
									{
										aset.setStatus("1");
									}
									aset.setKeys(asset.getAsset().getName().trim()+ asset.getAsset().getModel().trim()+asset.getAsset().getSeries().trim());
									lstAssetNew.add(aset);
									limit = limit + Integer.parseInt(asset.getAsset().getMaintaince().trim())+1;
								}				
							}catch (Exception e)
							{
								mv.addObject(ParamsConstants.MESSAGE_ERROR, "LỖI DO KHÔNG TÌM THẤY NGÀY BẢO TRÌ");
							}
									
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				if(lstAssetNew.size()>0)
				{
					String oldKeys = "";
					int indexKeys=-1;
					for(int i=0;i<lstAssetNew.size();i++)
					{
						for(int j=0;j<12;j++)
						{
							
							lstAssetNew.get(i).getLstMonth().add(j, "0");
						}
						
						if(lstAssetNew.get(i).getKeys().trim().equals(oldKeys))
						{
							//lstAsset.get(indexKeys).getLstColumn().remove(lstAsset.get(i).getColumn());
							lstAssetNew.get(indexKeys).getLstMonth().set(Integer.parseInt(lstAssetNew.get(i).getMonthMaintaince())-1, "1");
							
						}
						else
						{
							indexKeys = i;
							oldKeys = lstAssetNew.get(i).getKeys().trim();
							//lstAsset.get(indexKeys).getLstColumn().remove(lstAsset.get(i).getColumn());
							lstAssetNew.get(indexKeys).getLstMonth().set(Integer.parseInt(lstAssetNew.get(i).getMonthMaintaince())-1, "1");

						}
					}
					
					int x=1;
					for(x=1;x<lstAssetNew.size()+1;x++)
					{
						if(x==0)
						{
							x=1;
						}
						if(x<lstAssetNew.size())
						{
							 if(lstAssetNew.get(x).getKeys().trim().equals(lstAssetNew.get(x-1).getKeys().trim()))
							 {
								 lstAssetNew.remove(x);
								 x--;
								
							 }
						}
					}

					
					
					mv.addObject("lst", lstAssetNew);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mv.setViewName("views/AssetMaintainceManagement.jsp");
		
		return mv;
	}

}
