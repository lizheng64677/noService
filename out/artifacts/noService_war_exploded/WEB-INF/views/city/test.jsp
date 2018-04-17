<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <script type="text/javascript" src="<c:url value='/resources/js/secity.js'/>"></script>
  </head>
  <body>
  	<input type="text" id="input" value=""/>
  	<input type="button" onclick="ui.test();" value="xxxxxx"/>
  			<select name="" id="setAnimal">
			<option value="cat">cat</option>
			<option value="fish">fish</option>
			<option value="bird">bird</option>
		</select>
		      <p id="whatDoesThisAnimalDo"></p>  
		<div class="ul">
		
		</div>
  </body>
  <script type="text/javascript">
$(document).ready(function(){
    var str=["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];

//      Animal.start();
	 $('#input').querycity({"data":str});

});

</script>  
  
</html>