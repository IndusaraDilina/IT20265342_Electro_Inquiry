package com.PAF;

import java.sql.*; 
public class Item 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = 
 DriverManager.getConnection( 
 "jdbc:mysql://127.0.0.1:3306/inquiry", "root", "Indusara_123"); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 
public String readInquiry() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>InquiryType</th> " + "<th>InquiryDescription</th> "
 				+ "<th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from inquiry_table"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String InquiryID = Integer.toString(rs.getInt("InquiryID")); 
 String InquiryType = rs.getString("InquiryType");
 String InquiryDescription = rs.getString("InquiryDescription"); 
 // Add into the html table
output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + InquiryID
 + "'>" + InquiryType + "</td>"; 
 output += "<td>" + InquiryDescription + "</td>";  
 // buttons
output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
 + InquiryID + "'>" + "</td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the inquiry."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String insertInquiry(String type, String description) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for inserting."; 
 } 
 // create a prepared statement
 String query = " insert into inquiry_table (`InquiryID`,'InquiryType','InquiryDescription')"
 + " values (?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, type); 
		 preparedStmt.setString(3, description); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newInquiry = readInquiry(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newInquiry + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the inquiry.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		public String updateInquiry(String ID, String type, String description) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE inquiry_table SET InquiryType=?,InquiryDescription=? WHERE InquiryID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, type); 
		 preparedStmt.setString(2, description); 
		 preparedStmt.setInt(5, Integer.parseInt(ID));
		// execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readInquiry(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the inquiry.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		public String deleteInquiry(String InquiryID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from inquiry_table where InquiryID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(InquiryID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readInquiry(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the inquiry.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}
