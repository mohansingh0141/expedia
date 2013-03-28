<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>

	<head>
		<title>Spring MVC Starter Application</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
	</head>

	<body>
		
		<h2>Weather Information</h2>
<form method="POST" action="getWeather">
   <table>
    <tr>
        <td><label >Enter Zip Code Here :</label></td>
        <td><input name="zipcode" /></td>
        <td><input type="submit" value="View Details" />
        
    </tr>
    <c:choose>
    	<c:when test="${not empty error}" >
    		<tr><td>Error : &nbsp;</td><td> <span style=”font-family:georgia;color:#FF0000;”>${error.msg}</span></td></tr>
    	</c:when>
    	<c:when test="${not empty data }">
    		
    		<tr><Td>Zipcode</td><td><span style=”font-family:georgia;color:#006600;”><b>${data.zipcode}</b></span></td></tr>
    		<tr><Td>City</td><td><span style=”font-family:georgia;color:#006600;”><b>${data.city}</b></span></td></tr>
    		<tr><Td>State</td><td><span style=”font-family:georgia;color:#006600;”><b>${data.state}</b></span></td></tr>
    		<tr><Td>Temperature (F)</td><td><span style=”font-family:georgia;color:#006600;”><b>${data.temp}</b></span></td></tr>    
    				
    	</c:when>
    </c:choose>    
	</table>  
</form>
</body>
</html>
