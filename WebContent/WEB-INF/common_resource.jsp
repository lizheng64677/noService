<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!-- easyUI -->
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/easyUI1.4.2/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/easyUI1.4.2/themes/icon.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common.css'/>">
<script type="text/javascript">
var SITE_BASE_PATH = '<c:url value="/"/>';
</script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.8.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/easyUI1.4.2/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/easyUI1.4.2/locale/easyui-lang-zh_CN.js'/>"></script>
<script type="text/javascript">
$.ajaxSetup({
    type: 'POST',
    complete: function(xhr,status) {
        var sessionStatus = xhr.getResponseHeader('sessionstatus');
        if(sessionStatus == 'timeout') {
	       	$.messager.confirm('确认', 'session已失效, 请重新登录!', function(data){
	       		if(!data){
	       			return;
	       		}
	       		var p = window;
	            while(p != p.parent){
	                p = p.parent;
	            }
	       		p.location.href="<c:url value='/login/doLogin'/>";
	       	});	
       }
  	}
});
</script>
