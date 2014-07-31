<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> Calculate your Interest</title>
</head>
<body bgcolor="#f8f8f8">
	<center><h2><font color="#764807" size="6">Calculate Interest</font></h2>
		<hr><br/>
		<form name ="input_form" action="result" method ="post">
			<table >
				<tr>
					<td> Principle </td>
					<td><input type="text" name="principle"></td>
				</tr>
				<tr>
					<td> Interest Percentage </td>
					<td><input type="text" name="per"></td>
				</tr>
				<tr>
					<td> Number of Years </td>
					<td><input type="text" name="years"></td>
				</tr>
				<tr>
					<td colspan="2">
						<center>
							<br/>
							<input type="submit" name="Submit" value="Submit" >
						</center>
					</td>
				</tr>
			</table>
		</form>
		<br/>
		<!-- Result printing code		 -->
		<c:set var="localres" value="${requestScope.result}" />	
		<c:choose>
        	<c:when test="${localres == 'ok'}">
            	<h2> <font color="#0d7c3d"> Calculated Interest </font>  </h2>
				<hr>
				<table cellpadding="5">
					<tr>	
						<td> <b> Principle Amount: </b>  
						<td> <c:out value="${requestScope.principle}" ></c:out> </td> 
						</tr>
					<tr> 
						<td> <b> Interest Percentage: </b>
						<td> <c:out value="${requestScope.per_int}" ></c:out> </td>
					</tr>
					<tr>
						<td> <b> Number of Years: </b> 
						<td> <c:out value="${requestScope.yrs}" ></c:out></td>
					</tr>
					<tr>
						<td> <b> Total Interest Value: </b>
						<td> <c:out value="${requestScope.total_interest}" ></c:out> </td>
					</tr>
					<tr>
						<td> <b> Net Accrued Value: </b> 
						<td> <c:out value="${requestScope.net_value}" ></c:out></td>
					</tr>
				</table>
        	</c:when>
        	<c:when test="${localres == 'error'}">
            	<h4><font color="red">There are validation errors. Please correct your inputs and retry </font></h4>
			 	<hr>
            	<c:set var="err_list" value="${requestScope.err_list}" />
             	<table>
					<tr> 
						 <td> <h4> Principle: </h4> </td>
						 <td> <c:out value="${err_list.get(0)}"/> </td>
					</tr>
					<tr> 
						 <td> <h4> Interest Percentage: </h4> </td>
						 <td> <c:out value="${err_list.get(1)}"/> </td>
					</tr>
					<tr> 
						 <td> <h4> Number of Years: </h4> </td> 
						 <td> <c:out value="${err_list.get(2)}"/> </td>
					</tr>
				</table>
       		 </c:when>
    	</c:choose>
	</center>
</body>
</html>