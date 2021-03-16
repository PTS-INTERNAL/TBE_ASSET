<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ THANH LÝ TÀI SẢN</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>
<script src="./resources/javascript/message/bootbox.all.js"></script>
<script src="./resources/javascript/message/bootbox.all.min.js"></script>
<script src="./resources/javascript/message/bootbox.js"></script>
<script src="./resources/javascript/message/bootbox.locales.js"></script>
<script src="./resources/javascript/message/bootbox.locales.min.js"></script>
<script src="./resources/javascript/message/bootbox.min.js"></script>
</head>

<style>
.table-data thead th
{
	vertical-align: middle;
}

</style>
<body onload="Pagination()">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="margin-top: 10px; padding: 0px; margin: 10px;">
		<div class="row">
			<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
				<form action="AssetLiquidationManagement" method="POST">
					<table class="table   table-search">
						<thead>
							<tr>
								<td class="title">Đơn Vị</td>
								<td><div class="input-group">
										<span class="input-group-addon"> </span> <input type="text"
											style="height: 30px;" class="form-control inputAssetItem"
											value="${formSearch.getDepartment_name()}"
											readonly="readonly" name="department_name"
											id="department_name"> <input type="text"
											class="form-control inputAssetItem" style="display: none;"
											name="department_cd" value="${formSearch.getDepartment_cd()}"
											id="department_cd">
										<button type="button" class="select"
											style="height: 30px; margin-right: 2px;"
											onclick="return openDialogue('GetListDepartment?param1=department_cd&param2=department_name')">
											<i class="fa fa-th-list"></i>
										</button>
										<button type="button" class="select" style="height: 30px;"
											onclick="return clearText('department_cd','department_name' )">
											<i class="fa fa-eraser" aria-hidden="true"></i>

										</button>
									</div></td>
								<td class="title">Nhóm</td>
								<td>
									<div class="input-group">
										<span class="input-group-addon"> </span> <input type="text"
											style="height: 30px;" class="form-control inputAssetItem"
											readonly="readonly" name="group_asset_na" id="group_asset_na"
											value="${formSearch.getGroup_name()}"> <input
											type="text" class="form-control inputAssetItem"
											style="display: none;" name="group_asset_cd"
											value="${formSearch.getGroup_id()}" id="group_asset_cd">
										<button type="button" class="select"
											style="height: 30px; margin-right: 2px;"
											onclick="return openDialogue('GetListGroupAsset')">
											<i class="fa fa-th-list"></i>
										</button>
										<button type="button" class="select" style="height: 30px;"
											onclick="return clearText('group_asset_na','group_asset_cd' )">
											<i class="fa fa-eraser" aria-hidden="true"></i>
										</button>
									</div>
								</td>
								<td class="title">Model</td>
								<td><input value="${formSearch.getModel()}"
									class="selectList" list="text_model" name="text_model">
									<datalist class="selectList-item" id="text_model">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.getModel()}">
										</c:forEach>
									</datalist></td>
								<td class="title">Số Series</td>
								<td><input value="${formSearch.getSeries()}"
									class="selectList" list="text_series" name="text_series">
									<datalist id="text_series" class="selectList-item">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.getSeries()}">
										</c:forEach>
									</datalist></td>
							</tr>
							<tr>
								<td class="title">Tên Tài Sản</td>
								<td><input class="selectList"
									value="${formSearch.getName()}" list="text_asset_name"
									name="text_asset_name"> <datalist
										class="selectList-item" id="text_asset_name">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.getName()}">
										</c:forEach>
									</datalist></td>
							
								
							
								<td class="title">NGÀY THANH LÝ</td>
								<td colspan="3"><input
									value="${formSearch.getDateStart_Start()}" style="width: 45%"
									type="date" data-date-format="DD/MM/YYYY"
									name="text_start_date_s"> ~ <input
									value="${formSearch.getDateStart_End()}" style="width: 45%"
									type="date" name="text_start_date_e"></td>
								

							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="title-feature">
						<div class="text-right">
							<button type="submit" style="border-radius: 0"
								class="btn btn-primary" name="search">
								<i class="fa fa-search" style="font-size: 24px"
									aria-hidden="true"></i> TÌM KIẾM
							</button>
							<button type="submit" style="border-radius: 0"
								class="btn btn-primary" name="register" >
								<i style="font-size: 24px" class="fa">&#xf067;</i>THÊM MỚI
							</button>
							<button type="submit" style="border-radius: 0" name="reportPDF"
								class="btn btn-primary">
								<i class="fa fa-file-pdf-o" style="font-size: 24px"></i>XUẤT PDF
							</button>
							<button type="submit" style="border-radius: 0" name="back"
								class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>QUAY
								LẠI
							</button>
						</div>
					</div>
				</form>
				<jsp:include page="viewcommon/message.jsp"></jsp:include>
  				<c:if test="${lstAsset.size() > 0}">  
 					<table style="margin-top:10px; margin-bottom:10px;">
						<tr>
							<td style="width: 30px; height: 10px; background-color: orange; border: 1px solid"></td>
							<td style="font-weight: 700; padding-left:10px;padding-right:10px;">ĐANG ĐỀ XUẤT</td>
							<td style="width: 30px; height: 10px; background-color: green;border: 1px solid"></td>
							<td style="font-weight: 700; padding-left:10px;padding-right:10px;">ĐÃ THANH LÝ</td>
							<td style="width: 30px; height: 10px; background-color: red;border: 1px solid"></td>
							<td style="font-weight: 700; padding-left:10px;padding-right:10px;">KHÔNG DUYỆT THANH LÝ</td>
						</tr>
					</table>
					<table id="table.data" style="margin-top: 7px"
						class="table   table-data" >
						<thead>
							<tr>
								<th >STT</th>
								<th >NHÓM</th>
								<th >TÊN TÀI SẢN</th>
								<th >SỐ SERIES</th>
								<th >MODEL</th>
								<th >SỐ LƯỢNG</th>
								<th >THỜI ĐIỂM TĂNG</th>
								<th >NGÀY ĐỀ XUẤT</th>
								<th >NGÀY XEM XÉT</th>
								<th >TÌNH TRẠNG</th>
								<th style="width:140px">TRẠNG THÁI</th>
								<th style="width:60px">XEM</th>
							</tr>
						
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:forEach var="p" items="${lstAsset}">
							<tr>

									<td style="text-align: center;"><%=stt%></td>
									<td>${p.getAsset().getGroup_name()}</td>
									<td>${p.getAsset().getName()}</td>
									<td>${p.getAsset().getSeries()}</td>
									<td>${p.getAsset().getModel()}</td>
									<td>${p.getNumber()}</td>
									<td>${p.getAsset().getDateStart()}</td>
									<td>${p.getDateProposal()}</td>
									<td>${p.getDateProposal()}</td>
									<td>${p.getReason()}</td>
									<c:if test="${p.getStatus()=='1'}">
									<td style="background-color: white;">
										
										<p  class="view" style="width:140px;  margin:auto; background-color: orange;" name="view_asset">CHỜ PHÊ DUYỆT</p>
										

									</td>
									</c:if>
									<c:if test="${p.getStatus()=='2'}">
									<td style="background-color: white;">
										<p  class="view" style="width:140px;  margin:auto; background-color: green;" name="view_asset">ĐÃ THANH LÝ</p>
										

									</td>
									</c:if>
									<c:if test="${p.getStatus()=='3'}">
									<td style="background-color: white;">	
										<p class="view" style="width:140px;  margin:auto; background-color: red;" name="view_asset">KHÔNG DUYỆT</p>
									</td>
									</c:if>
									
												
									<td style="background-color: white;">
										<form action="AssetLiquidationView" method="post"
											style="width: 60px;">
											<input style="display: none;" name="liquidation_cd"
												value="${p.getLiquidation_Cd()}">
											<button type="submit" class="view" name="view_asset">XEM</button>
										</form>

									</td>
								</tr>
								<%
									stt++;
								%>
							</c:forEach>
						</tbody>
					</table>
					
					<div class="text-right">
						<%
							if (stt > 19) {
									double countPage = stt / 19;
									if (stt % 19 > 0) {
										countPage += 1;
									}
									int j = 1;
									while (j <= countPage) {
										int startIndex = j * 19 - 18;
										int endIndex = startIndex + 18;
						%>
						<a class="btn btn-default btn-paging" id='pagging.btn<%=j%>'
							onclick="movePage('<%=startIndex%>','<%=endIndex%>', '<%=j%>', '<%=countPage%>')"><%=j%></a>
						<%
							j++;
									}
								}
						%>


					</div>
  				</c:if>  
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"> 
 var titleTable = document.getElementById("titleTable").innerHTML;
document.getElementById("titleTable").innerHTML = titleTable + " " + new Date().getFullYear();
 
	function abc() {
		bootbox
				.confirm({
					title : "Destroy planet?",
					message : "Do you want to activate the Deathstar now? This cannot be undone.",
					buttons : {
						cancel : {
							label : '<i class="fa fa-times"></i> Cancel'
						},
						confirm : {
							label : '<i class="fa fa-check"></i> Confirm'
						}
					},
					callback : function(result) {
						Example.show('This was logged in the callback: '
								+ result);
					}
				});
	}
	//Hàm click button chuyển trang
	function movePage(start, end, indexCurrent, countPage) {
		var x = document.getElementById("table.data").rows.length;
		for (var i = 2; i < x; i++) {
			var index = i + 1;
			if (i >= start && i <= end) {
				document.getElementById("table.data").rows[i].style.display = '';
			} else {
				document.getElementById("table.data").rows[i].style.display = 'none';
			}

		}
		for (var i = 1; i <= countPage; i++) {
			x = document.getElementById("pagging.btn" + i);
			x.style.backgroundColor = "";
			x.style.color = "black";
		}
		x = document.getElementById("pagging.btn" + indexCurrent);
		x.style.backgroundColor = "red";
		x.style.color = "white";

	}
	//Hàm phân trang cho dữ liệu
	function Pagination() {
		var x = document.getElementById("pagging.btn1");
		x.style.backgroundColor = "red";
		x.style.color = "white";
		x = document.getElementById("table.data").rows.length;
		if (x > 10) {
			for (var i = 19; i < x; i++) {
				document.getElementById("table.data").rows[i + 1].style.display = 'none';
			}

		}
	}

	//hàm chọn dữ liệu
	function GetSelected() {
		//Reference the Table.
		var grid = document.getElementById("table.data");

		//Reference the CheckBoxes in Table.
		var checkBoxes = document.getElementsByName("checkboxrow");

		//Loop through the CheckBoxes.
		for (var i = 0; i < checkBoxes.length; i++) {
			var color = "white";
			if (checkBoxes[i].checked) {
				color = "#bdc6e2";
			}

			var row = checkBoxes[i].parentNode.parentNode;
			for (var j = 0; j < row.cells.length; j++) {
				row.cells[j].style.backgroundColor = color;
			}
		}

	}
	$(document).ready(function() {
		Pagination();
		$('#example').DataTable({
			columnDefs : [ {
				orderable : false,
				className : 'select-checkbox',
				targets : 0
			} ],
			select : {
				style : 'os',
				selector : 'td:first-child'
			},
			order : [ [ 1, 'asc' ] ]
		});
	});

	$(function() {
		$('#datetimepicker1').datetimepicker();
	});
</script>
</html>