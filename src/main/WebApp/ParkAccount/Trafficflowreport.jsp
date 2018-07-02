<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/ParkAccount/layui/css/layui.css"  media="all">
  <style>
  .ys{
  width: 250px;
  height: 100px;
  background-color: #01AAED;
  position:relative;
  left:20px;
  font-size:26px;
  }
    .ysd{
  width: 250px;
  height: 100px;
  background-color: #5FB878;
  float:left;
  position:relative;
  left:300px;
  top:-100px;
  font-size:26px;
  }
  
  .zx{
  position:relative;
  left:30px;
  top:35px;
  }
  
  .fontss{
  font-size:30px;
  position:relative;
  color:#fff;
  left:110px;
  top:-10px;
  }
   .fontsb{
  font-size:26px;
  position:relative;
  color:#fff;
  left:115px;
  top:10px;
  }
	.ss{
		position: relative;
		left:800px;;
	}  
  </style> 
  
</head>
<body style="margin:20px;">
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script src="${pageContext.request.contextPath }/ParkAccount/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath }/ParkAccount/js/echarts.min.js" charset="utf-8"></script>

<fieldset class="layui-elem-field layui-field-title">
  <legend>昨日车流</legend>
  		<blockquote class="layui-elem-quote">
		  		<div class=ys>
		  		<div class=zx>
		  		<i class="layui-icon" style="font-size:50px !important; color:#fff; display: inline-block;" >&#xe636;</i>
		 		</div>
		    	<div class="fontss"> 进入车次</div>
		    	<div class="fontsb">
		    	<c:if test="${! empty sessionScope.ParkingTimes}">
		    		${sessionScope.ParkingTimes.enterTimes }
		    	</c:if></div>
		    	</div>
		    	<div class="ysd">
		  		<div class="zx">
		  		<i class="layui-icon" style="font-size:50px !important; color:#fff; display: inline-block;" >&#xe636;</i>
		 		</div>
		    	<div class="fontss"> 出去车次</div>
		    	<div class="fontsb">
		    	<c:if test="${! empty sessionScope.ParkingTimes}">
		    		${sessionScope.ParkingTimes.leaveTimes }
		    	</c:if></div>
			</div>
		</blockquote>
</fieldset>

<fieldset class="layui-elem-field layui-field-title">
  <legend>条件统计</legend>
  <blockquote class="layui-elem-quote" >
  			<div class="demoTable layui-form" >
  			<label class="layui-form-label">按年份查询</label>
  			<div class="layui-input-inline">
				 <select id="year" lay-filter="test" name="modules" >
				    <option value="sb" name="sb" selected="selected"  >请选择</option>
				 <c:forEach var="item" items="${sessionScope.year}">
		          	<option value="${item}"  >${item}</option>
		          </c:forEach>	
		        </select>
		     </div>
	  					<a href="javascript:;" class="layui-btn layui-btn-small ss" onclick="setDateOption(3,0)">
						<i class="layui-icon">&#xe603;</i> 上一个月
						</a>
						<a href="javascript:;" class="layui-btn layui-btn-small ss" onclick="setDateOption(2,0)">
						<i class="layui-icon">&#xe61a;</i> 本月
						</a>
						<a href="javascript:;" class="layui-btn layui-btn-small ss" onclick="setDateOption(1,0)">
						<i class="layui-icon">&#xe61a;</i> 当前
						</a>
			</div>
  </blockquote>
</fieldset>
<script>
layui.use('form', function(){
	  var form = layui.form;
	  form.render();
	  form.render('select');
	  form.on('select(test)', function(data){
		  console.log(data.elem); //得到select原始DOM对象
		  console.log(data.value); //得到被选中的值
		  console.log(data.othis); //得到美化后的DOM对象
		  if(data.value=="sb")
 		  {
			  setDateOption(1,0);
 		  }else
 		  {
 			 setDateOption(4,data.value);
 		  }
		  
		});
	  //各种基于事件的操作，下面会有进一步介绍
});
</script>


<div id="main" style="height:500px"></div>
   <script type="text/javascript">
        // 使用
       /*  require(
        	[
                'echarts',
        		'echarts/chart/line'
            ],
            function (ec) { */
                // 基于准备好的dom，初始化echarts图表
                // 显示标题，图例和空的坐标轴
            var myChart = echarts.init(document.getElementById('main'));
            myChart.setOption({
            	 title : {
            	        text: '车流量',
            	    
            	    },
            	    tooltip : {
            	        trigger: 'axis'
            	    },
            	    legend: {
            	        data:['进','出']
            	    },
            	    toolbox: {
            	        show : true,
            	        feature : {
            	            dataView : {show: true, readOnly: false},
            	            magicType : {show: true, type: ['line', 'bar']},
            	            restore : {show: true},
            	            saveAsImage : {show: true}
            	        }
            	    },
            	    calculable : true,
            	    xAxis : [
            	        {
            	            type : 'category',
            	            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
            	        }
            	    ],
            	    yAxis : [
            	        {
            	            type : 'value'
            	        }
            	    ],
            	    series : [
            	        {
            	            name:'进',
            	            type:'line',
            	            data:[],
            	            markPoint : {
            	                data : [
            	                    {type : 'max', name: '最大值'},
            	                    {type : 'min', name: '最小值'}
            	                ]
            	            },
            	            markLine : {
            	                data : [
            	                    {type : 'average', name: '平均值'}
            	                ]
            	            }
            	        },
            	        {
            	            name:'出',
            	            type:'line',
            	            data:[],
            	            markPoint : {
            	                data : [
            	                    {type : 'max', name: '最大值'},
            	                    {type : 'min', name: '最小值'}
            	                ]
            	            },
            	            markLine : {
            	                data : [
            	                    {type : 'average', name: '平均值'}
            	                ]
            	            }
            	        }
            	    ]
                // 为echarts对象加载数据 
                }); 
             myChart.showLoading(); //加载 动画
            //异步请求停车车流数据
            //当前
            function setDateOption(eventType,year){
            	 $.get('/ParkingTimes/findByTrafficflowreport',{eventType:eventType,year:year},function (data) {
		                // 填入数据
		                myChart.hideLoading();
		                myChart.setOption({
		                    xAxis: {
		                        data: data.categories
		                    },
		                    series: [{
		                        // 根据名字对应到相应的系列
		                        name: '进',
		                        data: data.go
		                    },{
		                    	// 根据名字对应到相应的系列
		                        name: '出',
		                        data: data.come
		                    }]
		                })
		             })
             }
            setDateOption(1,0);
    </script>
</body>
</html>