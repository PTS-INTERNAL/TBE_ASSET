package com.ams.model;

import java.util.ArrayList;
import java.util.List;

public class AssetMaintanceModel {
	private AssetGeneralModel asset;
	private String monthMaintaince;
	private String userInsert;
	private String insertDT;
	private String userUpdate;
	private String status;
	private String keys;
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	private List<String> lstMonth;
	public List<String> getLstMonth() {
		return lstMonth;
	}
	public void setLstMonth(List<String> lstMonth) {
		this.lstMonth = lstMonth;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	private String day;
	private String month;
	private String year;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AssetMaintanceModel()
	{
		this.asset = new AssetGeneralModel();
		this.lstMonth = new ArrayList<String>();
	}
	public AssetGeneralModel getAsset() {
		return asset;
	}
	public void setAsset(AssetGeneralModel asset) {
		this.asset = asset;
	}
	public String getMonthMaintaince() {
		return monthMaintaince;
	}
	public void setMonthMaintaince(String monthMaintaince) {
		this.monthMaintaince = monthMaintaince;
	}
	public String getUserInsert() {
		return userInsert;
	}
	public void setUserInsert(String userInsert) {
		this.userInsert = userInsert;
	}
	public String getInsertDT() {
		return insertDT;
	}
	public void setInsertDT(String insertDT) {
		this.insertDT = insertDT;
	}
	public String getUserUpdate() {
		return userUpdate;
	}
	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}
	public String getUpdateDT() {
		return updateDT;
	}
	public void setUpdateDT(String updateDT) {
		this.updateDT = updateDT;
	}
	private String updateDT;
	
}
