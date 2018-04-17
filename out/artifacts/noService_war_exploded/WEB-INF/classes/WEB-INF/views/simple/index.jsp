<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.8.2.min.js'/>"></script>
<html>
  <head>
  		
  </head>
  
</body>
<script type="text/javascript">
$(function() {
	$.ajax(  
		    {  
		    	url : 'http://18098351988.oicp.net/noService/json/data',  
		        type:'GET',  
		        dataType : 'jsonp',  
		        jsonp:"callback", 
		        data:'{dada:da,da:aa}',  
		        success  : function(data) {  
		           alert(data[0].name);
		        },  
		        error : function() {  
		            alert('跨域请求失败!');  
		        }  
		    } 
		);  
	
	 
});

</script>  
</html>