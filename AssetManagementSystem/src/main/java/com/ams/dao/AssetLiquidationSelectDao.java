package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.CompanyModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class AssetLiquidationSelectDao {
	AssetLiquidationModel asset;
	
	public AssetLiquidationSelectDao()
	{
		
	}
	

	public AssetLiquidationSelectDao(AssetLiquidationModel asset)
	{
		this.asset = asset;
	}
	
	public List<AssetLiquidationModel> excute() throws SQLException, ParseException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<AssetLiquidationModel> lstAss =new ArrayList<AssetLiquidationModel>();
		while (result.next()) {
			AssetLiquidationModel ass = new AssetLiquidationModel();
			ass.setLiquidation_Cd(result.getString("LIQUIDATION_CD"));
			ass.setNumber(result.getString("NUMBER_MANA"));
			ass.getAsset().setId(result.getString("ASSET_CD"));
			ass.getAsset().setCompany_CD(result.getString("CMPN_CD"));
			ass.getAsset().setGroup_id(result.getString("GROUP_CD"));
			ass.getAsset().setGroup_name(result.getString("GROUP_NAME"));
			ass.getAsset().setRFID(result.getString("ASSET_RFID"));
			ass.getAsset().setName(result.getString("ASSET_NAME"));
			ass.getAsset().setModel(result.getString("ASSET_MODEL"));
			ass.getAsset().setSeries(result.getString("ASSET_SERIES"));
			ass.getAsset().setAccountant_CD(result.getString("ASSET_ACCOUNTANT"));
			ass.getAsset().setDateStart(result.getString("ASSET_DATE_INVEST"));
			ass.getAsset().setPrice(result.getString("ASSET_PRICE"));
			ass.getAsset().setNote(result.getString("ASSET_DATE_LIQUI"));
			
			ass.setDateProposal(Common.ConvertStringToDateStr(result.getString("INSERT_DT"),"yyyyMMdd","dd/MM/yyyy"));
			ass.setReason(result.getString("REASON"));
			ass.setStatus(result.getString("STATUS"));
			ass.setNote(result.getString("NOTE"));
			if(Common.isNotEmpty(result.getString("APPROVE_DT")))
			{
				ass.setApproveDT(Common.ConvertStringToDateStr(result.getString("APPROVE_DT"),"yyyyMMdd","dd/MM/yyyy"));

			}
			else
			{
				ass.setApproveDT(result.getString("APPROVE_DT"));

			}
			ass.setUserApprove(result.getString("APP_USER"));
			ass.setUserInsert(result.getString("PRO_USER"));
			ass.setInsertDT(result.getString("INSERT_DT"));
			ass.setReason_not_allow(result.getString("REASON_NOT_ALLOW"));
			ass.getAsset().setRFID(result.getString("ASSET_RFID"));


			
			lstAss.add(ass);
		}
		return lstAss;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT LIQUIDATION_CD ");
		sql.append("   ,NUMBER_MANA ");
		sql.append("   ,ASSET_CD ");
		sql.append("   ,AL.CMPN_CD ");
		sql.append("   ,AL.STATUS ");
		sql.append("   ,GROUP_CD ");
		sql.append("   ,GA.GROUP_NAME ");
		sql.append("   ,ASSET_RFID ");
		sql.append("   ,ASSET_NAME ");
		sql.append("   ,ASSET_MODEL ");
		sql.append("   ,ASSET_SERIES ");
		sql.append("   ,ASSET_ACCOUNTANT ");
		sql.append("   ,ASSET_DATE_INVEST ");
		sql.append("   ,ASSET_DATE_LIQUI ");
		sql.append("   ,ASSET_PRICE ");
		sql.append("   ,ASSET_NOTE ");
		sql.append("   ,DELETE_FG ");
		sql.append("   ,AL.ASSET_RFID ");
		sql.append("   ,AL.USER_INSERT , ");
		sql.append("   AL.INSERT_DT , ");
		sql.append("   AL.USER_UPDATE , ");
		sql.append("   AL.UPDATE_DT , ");
		sql.append(" REASON_NOT_ALLOW, ");
		sql.append("   ISNULL(AL.APPROVE_DT, '') AS APPROVE_DT, ");
		sql.append("   AL.REASON , ");
		sql.append("   AL.NOTE, ");
		sql.append("   US.USER_NAME AS PRO_USER, ");
		sql.append("   ISNULL(US2.USER_NAME,'') AS APP_USER ");
		sql.append(" FROM ASSET_MANAGEMENT.dbo.ASSETS_LIQUIDATION AL ");
		sql.append(" LEFT JOIN GROUP_ASSET GA  ON GA.GROUP_ID = AL.GROUP_CD ");
		sql.append(" INNER JOIN USER_SYSTEM US ON  US.USER_EMPLOYEE_CD = AL.USER_INSERT  ");
		sql.append(" 		LEFT JOIN USER_SYSTEM US2 ON US2.USER_EMPLOYEE_CD = AL.USER_APPROVE  ");
		sql.append(" WHERE 1=1 ");	
		if(asset != null)
		{
			if(Common.isNotEmpty(asset.getAsset().getCompany_CD()))
			{
				sql.append(" AND AL.CMPN_CD =  ").append("'" +asset.getAsset().getCompany_CD()+ "'");	
			}
			if(Common.isNotEmpty(asset.getLiquidation_Cd()))
			{
				sql.append(" AND AL.LIQUIDATION_CD =  ").append("'" +asset.getLiquidation_Cd()+ "'");	
			}
		}
		return sql.toString();
	}
	
}
