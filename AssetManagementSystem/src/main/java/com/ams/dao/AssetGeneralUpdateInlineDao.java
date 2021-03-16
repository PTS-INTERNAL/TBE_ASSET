package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;



public class AssetGeneralUpdateInlineDao {
	public AssetGeneralUpdateInlineDao()
	{
		
	}
	
	
	
	public int excuteData(AssetGeneralModel asset) throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql(asset));
		//System.out.println(getSql(asset));
		
			
		result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getIDSetup()
	{
		DateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmSS");
		Date date = new Date();
        String ID = sdf.format(date);
		return ID;
	}
	
	public String getSql(AssetGeneralModel asset)
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE");
		sql.append(" 	ASSETS_GENERAL");
		sql.append(" SET ");
		sql.append(" 	INLINE = ").append("N'" + asset.getInline() + "'");
		
		sql.append(" WHERE ");
		sql.append(" 	ASSET_RFID = ").append("'" + asset.getRFID() + "'");
		return sql.toString();
	}

}
