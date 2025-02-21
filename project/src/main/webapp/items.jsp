<%@page import="com.PAF.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inquiry Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Inquiry Management </h1>
<form id="formItem" name="formItem">
 Inquiry Type: 
 <input id="InquiryType" name="InquiryType" type="text" 
 class="form-control form-control-sm">
 <br> Inquiry Description: 
 <input id="InquiryDescription" name="InquiryDescription" type="text" 
 class="form-control form-control-sm">
 <br> 
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Item itemObj = new Item(); 
 out.print(itemObj.readInquiry()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>

