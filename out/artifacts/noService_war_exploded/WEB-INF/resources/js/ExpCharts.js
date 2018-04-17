
function initWx(expType){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initExpData?clicentType=0&expType="+expType,
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= 'wechar_微信端数据统计' xAxisName='' yAxisName='PV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Bar2D.swf", "myChartId0","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initWx");
		}

	})

}


function initAndriod(expType){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initExpData?clicentType=2&expType="+expType,
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= 'Andriod_安卓端数据统计' xAxisName='' yAxisName='PV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Bar2D.swf", "myChartId","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initAndriod");

		}

	})

}



function initIos(expType){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initExpData?clicentType=1&expType="+expType,
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= 'IOS_苹果端数据统计' xAxisName='' yAxisName='PV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Bar2D.swf", "myChartId0","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initIos");

		}

	})
}


function initAll(expType){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initExpAllData?clicentType=2&expType="+expType,
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= '各终端数据统计' xAxisName='' yAxisName='PV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Area2D.swf", "myChartId","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initAll");

		}

	})


}









function initWxUv(expType){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initExpDataUv?clicentType=0&expType="+expType,
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= 'wechar_微信端数据统计' xAxisName='' yAxisName='UV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Bar2D.swf", "myChartId0","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initWxUv");
		}

	})

}


function initAndriodUv(expType){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initExpDataUv?clicentType=2&expType="+expType,
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= 'Andriod_安卓端数据统计' xAxisName='' yAxisName='UV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Bar2D.swf", "myChartId","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initAndriodUv");

		}

	})

}



function initIosUv(expType){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initExpDataUv?clicentType=1&expType="+expType,
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= 'IOS_苹果端数据统计' xAxisName='' yAxisName='UV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Bar2D.swf", "myChartId0","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initIosUv");

		}

	})
}


function initAllUv(expType){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initExpAllDataUv?expType="+expType,
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= '各终端数据统计' xAxisName='' yAxisName='UV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Area2D.swf", "myChartId","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initAllUv");

		}

	})


}

