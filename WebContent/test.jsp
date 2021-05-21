<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script src='https://cdn.bootcss.com/echarts/3.7.0/echarts.simple.js'></script> -->
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/echarts.js"></script>
<title>图表联动</title>
</head>
<body>
<form action="getinfo" method="post">


                                                选择图表类型：
                                                
                                                    <select id="tblx" name="tblx" class="form-control"
                                                            style="width: 150px;display: inline">
                                                        <option value='line'>折线图</option>
                                                        <option value='bar'>柱形图</option>
                                                        <option value='pie'>饼状图</option>
                                                    </select>
                                      <br>       

                                               选择数据类型：
                                               
                                                    <select id="type" name="type" class="form-control"
                                                            style="width: 150px;display: inline">
                                                        <option value='addr'>ip</option>
                                                        <option value='cishu'>访问次数</option>
                                                        <option value='type'>类型</option>
                                                    </select>
                                               
				<br>
				请选择查询的数据数目（数据过多可能无法显示）:
				<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="num" name="num">
				<br>

												
												<button type="submit">查询</button>
												



</form>


                            <!--为echarts准备一个容器，画图就画在里面-->
                            <div id="box" style="width: 1200px;height: 400px;margin-left: 4%"></div>
                            
                                <table style="width: 1200px;margin-left: 10%" border="1" >
                                    <thead>
                                    <tr>
                                        <th>信息</th>
                                        <th>数目</th>
                                    </tr>
                                    </thead>
                                    <tbody id="body">
                                    <c:forEach var="item" items="${info}">
                                        <tr>
                                            <td>${item.info}</td>
                                            <td>${item.num}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

</body>
<script type="text/javascript">


	
	var type='${type}';
	//alert(type);
    var mydataX = new Array(0);
    var mydataY = new Array(0);
    var mydata = new Array(0);
    var data = '${json}';
    var json = eval('(' + data + ')');
    for(var i=0;i<json.length;i++){
        mydataX.push(json[i].info);
        mydataY.push(json[i].num);
        var t={'name':json[i].info,'value':json[i].num};
        mydata.push(t);
    }
	
	
	
var myChart=echarts.init(document.getElementById("box"));
//指定图表的配置项和数据

if(type=="pie"){

var option = {
	    title: {
	        text: '饼状数据图',
	        subtext: '纯属虚构',
	        left: 'center'
	    },
	    tooltip: {
	        trigger: 'item',
	        formatter: '{a} <br/>{b} : {c} ({d}%)'
	    },
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        data: mydataX
	    },
	    series: [
	        {
	            name: '访问来源',
	            type: 'pie',
	            radius: '55%',
	            center: ['50%', '60%'],
	            data: mydata,
	            emphasis: {
	                itemStyle: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
}
else if(type=="line"){
	
	
    var option={
            //标题
            title:{
                text:'数据展示 折线图'
            },
            //工具箱
            //保存图片
            toolbox:{
                show:true,
                feature:{
                    saveAsImage:{
                        show:true
                    }
                }
            },
            //图例-每一条数据的名字叫销量
            legend:{
                data:['数量']
            },
            //x轴
            xAxis:{
                data:mydataX
            },
            //y轴没有显式设置，根据值自动生成y轴
            yAxis:{},
            //数据-data是最终要显示的数据
            series:[{
                name:'数量',
                type:'line',
                data:mydataY
            }]
        };
	
	
}else{
	
	option = {
		    xAxis: {
		        type: 'category',
		        data: mydataX
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		        data: mydataY,
		        type: 'bar'
		    }]
		};
	
}

//使用刚刚指定的配置项和数据项显示图表
myChart.setOption(option);

        myChart.on('click', function (params) {
        	
            var name = params.name;
           // alert("选中"+name+"!查询成功");
            var trlist = $("#body").children('tr');
            
            for (var i=0;i<trlist.length;i++) {
                var tdArr = trlist.eq(i).find("td");
                var namec = tdArr.eq(0).text();//    备注
                if(namec==name){
                    trlist.eq(i).css("background-color","red");
                }else {
                    trlist.eq(i).css("background-color","#E8F6FF");
                }
            }
            trlist.eq(0).focus();
            
        });

</script>
</html>

