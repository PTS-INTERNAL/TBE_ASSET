package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.CheckingAssetNew;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class AssetLiquidationInsertDao {
	AssetLiquidationModel asset = null;
	
	public AssetLiquidationInsertDao( AssetLiquidationModel asset )
	{
		this.asset = asset;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		//System.out.println(getSql());
			sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS"));//ASSET_CD
			sqlStatement.setString(2, asset.getNumber());
			sqlStatement.setString(3, asset.getAsset().getId());
			sqlStatement.setString(4, asset.getAsset().getCompany_CD());
			sqlStatement.setString(5, asset.getAsset().getGroup_id());
			sqlStatement.setString(6, asset.getAsset().getRFID());
			sqlStatement.setString(7, asset.getAsset().getName());
			sqlStatement.setString(8, asset.getAsset().getModel());
			sqlStatement.setString(9, asset.getAsset().getSeries());
			sqlStatement.setString(10,asset.getAsset().getAccountant_CD());
			sqlStatement.setString(11,asset.getAsset().getDateStart());
			sqlStatement.setString(12,Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
			sqlStatement.setString(13,asset.getAsset().getPrice());//ASSET_CD
			sqlStatement.setString(14, asset.getAsset().getNote());
			sqlStatement.setString(15, "0");
			sqlStatement.setString(16, asset.getAsset().getUser_insert_cd());
			sqlStatement.setString(17, Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
			sqlStatement.setString(18, asset.getAsset().getUser_insert_cd());
			sqlStatement.setString(19, Common.getDateCurrent(ParamsConstants.CD_FULL_DATE));
			sqlStatement.setString(20, asset.getReason());
			sqlStatement.setString(21, asset.getNote());

			sqlStatement.setString(22, "1");


			
			result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO ASSETS_LIQUIDATION ");
		sql.append("       (	LIQUIDATION_CD ");
		sql.append("       		,NUMBER_MANA ");
		sql.append("       		,ASSET_CD ");
		sql.append("        	,CMPN_CD ");
		sql.append("        	,GROUP_CD ");
		sql.append("        	,ASSET_RFID ");
		sql.append("        	,ASSET_NAME ");
		sql.append("        	,ASSET_MODEL ");
		sql.append("    		,ASSET_SERIES ");
		sql.append("     		,ASSET_ACCOUNTANT ");
		sql.append("      		,ASSET_DATE_INVEST ");
		sql.append("      		,ASSET_DATE_LIQUI ");
		sql.append("      		,ASSET_PRICE ");
		sql.append("      		,ASSET_NOTE ");
		sql.append("      		,DELETE_FG ");
		sql.append("      		,USER_INSERT ");
		sql.append("     		,INSERT_DT ");
		sql.append("      		,USER_UPDATE ");
		sql.append("      		,UPDATE_DT ");
		sql.append("      		,REASON ");
		sql.append("      		,NOTE ");

		sql.append("      		,STATUS )");

		sql.append("  VALUES ");
		sql.append("         (	 ? ");
		sql.append("        	,? ");
		sql.append("         	,? ");
		sql.append("        	,? ");
		sql.append("        	,? ");
		sql.append("        	,? ");
		sql.append("        	,? ");
		sql.append("         	,? ");
		sql.append("       		,? ");
		sql.append("       		,? ");
		sql.append("       		,? ");
		sql.append("       		,? ");
		sql.append("       		,? ");
		sql.append("      		,? ");
		sql.append("        	,? ");
		sql.append("        	,? ");
		sql.append("        	,? ");
		sql.append("        	,? ");
		sql.append("        	,? ");
		sql.append("        	,? ");
		sql.append("        	,? ");

		sql.append("       		,?	) ");
		return sql.toString();
	}


}
