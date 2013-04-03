<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>

	<head>
		<title>Spring MVC Starter Application</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
	</head>

	<body>
		
		<h2>Weather Information</h2>
<form:form method="POST" commandName="zipObj" action="getWeather" >
   <table>
    <tr>
        <td><label >Enter Zip Code Here :</label></td>
        <td><form:input path="zipValue" /></td>
        <td><input type="submit" value="View Details" /> 
        <td><form:errors path="zipValue" /></td>
       
        
    </tr>
    <c:choose> 
    	<c:when test="${not empty error}" >
     		<tr><td>Error : &nbsp;</td><td>${error.msg}</td></tr>
     	</c:when>
    	   	
    	<c:when test="${not empty data }">
    		
    		<tr><Td>Zipcode</td><td><b>${data.zipcode}</b></td></tr>
    		<tr><Td>City</td><td><b>${data.city}</b></td></tr>
    		<tr><Td>State</td><td><b>${data.state}</b></td></tr>
    		<tr><Td>Temperature (F)</td><td><b>${data.temp}</b></td></tr>    
    				
    	</c:when>
    </c:choose>    
	</table>  
</form:form>
</body>
</html>
