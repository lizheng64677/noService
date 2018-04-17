
function initWx(){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initData?clicentType=0",
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= 'wechar_微信端数据统计' xAxisName='x轴' yAxisName='PV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Column3D.swf", "myChartId0","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initWx");
		}

	})

}


function initAndriod(){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initData?clicentType=2",
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= 'Andriod_安卓端数据统计' xAxisName='x轴' yAxisName='PV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Column3D.swf", "myChartId","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initAndriod");

		}

	})

}



function initIos(){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initData?clicentType=1",
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= 'IOS_苹果端数据统计' xAxisName='x轴' yAxisName='PV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Column3D.swf", "myChartId0","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initIos");

		}

	})
}


function initAll(){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initAllData?clicentType=2",
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= '各终端数据统计' xAxisName='x轴' yAxisName='PV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Column3D.swf", "myChartId","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initAll");

		}

	})


}









function initWxUv(){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initDataUv?clicentType=0",
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= 'wechar_微信端数据统计' xAxisName='x轴' yAxisName='UV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Column3D.swf", "myChartId0","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initWxUv");
		}

	})

}


function initAndriodUv(){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initDataUv?clicentType=2",
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= 'Andriod_安卓端数据统计' xAxisName='x轴' yAxisName='UV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Column3D.swf", "myChartId","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initAndriodUv");

		}

	})

}



function initIosUv(){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initDataUv?clicentType=1",
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= 'IOS_苹果端数据统计' xAxisName='x轴' yAxisName='UV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Column3D.swf", "myChartId0","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initIosUv");

		}

	})
}


function initAllUv(){

	$.ajax({
		type:"post",
		url:"/noService/sysLog/initAllDataUv?clicentType=2",
		dataType:"text",
		success:function(json){
			var zzsrXML = "<graph caption= '各终端数据统计' xAxisName='x轴' yAxisName='UV数' showNames='1' decimalPrecision='0' formatNumberScale='0' baseFontSize='12'>";
			zzsrXML = zzsrXML+ json +"</graph>";
			var myChart= new FusionCharts("/noService/resources/js/FusionCharts/Column3D.swf", "myChartId","493", "270");
			myChart.setDataXML(zzsrXML);
			myChart.render("initAllUv");

		}

	})


}

