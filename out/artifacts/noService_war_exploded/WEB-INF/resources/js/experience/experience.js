/**
 *活动管理后台——页面处理
 */

//js时间格式转换 
var formatDate=function(date, format) {   
	if (!date) return;   
	if (!format) format = "yyyy-MM-dd";   
	switch(typeof date) {   
	case "string":   
		date = new Date(date.replace(/-/, "/"));   
		break;   
	case "number":   
		date = new Date(date);   
		break;   
	}    
	if (!date instanceof Date) return;   
	var dict = {   
			"yyyy": date.getFullYear(),   
			"M": date.getMonth() + 1,   
			"d": date.getDate(),   
			"H": date.getHours(),   
			"m": date.getMinutes(),   
			"s": date.getSeconds(),   
			"MM": ("" + (date.getMonth() + 101)).substr(1),   
			"dd": ("" + (date.getDate() + 100)).substr(1),   
			"HH": ("" + (date.getHours() + 100)).substr(1),   
			"mm": ("" + (date.getMinutes() + 100)).substr(1),   
			"ss": ("" + (date.getSeconds() + 100)).substr(1)   
	};       
	return format.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g, function() {   
		return dict[arguments[0]];   
	});                   
}   

//时间比对 判断抢购时间单位发券时间 是否匹配 
var validTime=function(){

	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	var unitDay = $("#unitDay").val();
	var $begin=formatDate(beginTime,"yyyy/MM/dd");
	var $end=formatDate(endTime,"yyyy/MM/dd");
	var _beginTime = (new Date($begin)).getTime();
	var _endTime = (new Date($end)).getTime();

	var thisDay=(_endTime-_beginTime) / (1000 * 3600 * 24)+1;//取出天数 
	if((thisDay!=0 && unitDay!=0) &&thisDay%unitDay==0){

		return true;
	}else{

		return false;
	}

}