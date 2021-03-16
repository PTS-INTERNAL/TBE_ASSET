package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.GroupAsset;



public class GroupAssetInsertDao {
	GroupAsset form =new GroupAsset();
	public GroupAssetInsertDao(GroupAsset form)
	{
		this.form = form;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		//System.out.println(getSql());
			sqlStatement.setString(1,form.getGroup_id());//COMPANY_CD
			sqlStatement.setString(2,form.getCompany_cd());//COMPANY_NAME
			sqlStatement.setString(3,form.getGroup_name());//COMPANY_ADDRESS
			sqlStatement.setString(4,form.getGroup_desciption());//COMPANY_ADDRESS
			result = sqlStatement.executeUpdate();
		
		
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" GROUP_ASSET ");
		sql.append(" (");
		sql.append(" 	GROUP_ID");
		sql.append(" 	,CMPN_CD");
		sql.append(" 	,GROUP_NAME");
		sql.append(" 	,GROUP_DESCRIPTION");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	 ?");//DEPT_CD
		sql.append(" 	,?");//CMPN_CD
		sql.append(" 	,?");//DEPARTMENT_NAME
		sql.append(" 	,?");//DESCRIPTION
		sql.append(" )");
		
		return sql.toString();
	}
}
