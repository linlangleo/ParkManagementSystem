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
  <link rel="stylesheet" href="${pageContext.request.contextPath }/AdministratorAccount/layui/css/layui.css"  media="all">
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/AdministratorAccount/css/base.css">
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/AdministratorAccount/css/toppage.css">
</head>
<body style="margin:20px;">

 <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
  <legend>排名<span style="color:#FF5722; ">Top5</span></legend>
</fieldset>
 
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">进入</li>
    <li>出去</li>
    <li>交易额</li>
  </ul>
  <div class="layui-tab-content" style="height: 100px;">
    <div class="layui-tab-item layui-show">
			<div class="Wrapper TopIndexCentWrap pt10">
			    <!--MainList01-begin-->
			    <div class="MainList01 pt20 pb10 clearfix">
			        <div class="MListWrap w265 mr40">
			            <div class="LsHead"><h3 class="h3Tit bg-blue">昨日进停车场排名</h3></div>
			            <div class="LsCent265">
			                <ul id="tabs1">
			                </ul>
			            </div>
			        </div>
			        <div class="MListWrap w327 mr40">
			            <div class="LsHead"><h3 class="h3Tit bg-blue">上月进停车场排名</h3></div>
			            <div class="LsCent327">
			                <ul id="tabs2">
			                </ul>
			            </div>
			        </div>
			        <div class="MListWrap w327">
			            <div class="LsHead"><h3 class="h3Tit bg-blue">上一年进停车场排名</h3></div>
			            <div class="LsCent327">
			                <ul id="tabs3">
			                </ul>
			            </div>
			        </div>
			    </div>
			</div>
    </div>
    <div class="layui-tab-item">
    	<div class="Wrapper TopIndexCentWrap pt10">
			    <!--MainList01-begin-->
			    <div class="MainList01 pt20 pb10 clearfix">
			        <div class="MListWrap w265 mr40">
			            <div class="LsHead"><h3 class="h3Tit bg-blue">昨日出停车场排名</h3></div>
			            <div class="LsCent265">
			                <ul id="tab1">
			                </ul>
			            </div>
			        </div>
			        <div class="MListWrap w327 mr40">
			            <div class="LsHead"><h3 class="h3Tit bg-blue">上月出停车场排名</h3></div>
			            <div class="LsCent327">
			                <ul id="tab2">
			                </ul>
			            </div>
			        </div>
			        <div class="MListWrap w327">
			            <div class="LsHead"><h3 class="h3Tit bg-blue">上一年出停车场排名</h3></div>
			            <div class="LsCent327">
			                <ul id="tab3">
			                </ul>
			            </div>
			        </div>
			    </div>
			</div>
    </div>
    <div class="layui-tab-item">
    	<div class="Wrapper TopIndexCentWrap pt10">
			    <!--MainList01-begin-->
			    <div class="MainList01 pt20 pb10 clearfix">
			        <div class="MListWrap w265 mr40">
			            <div class="LsHead"><h3 class="h3Tit bg-blue">昨日停车场交易额排名</h3></div>
			            <div class="LsCent265">
			                <ul id="tabc1">
			                </ul>
			            </div>
			        </div>
			        <div class="MListWrap w327 mr40">
			            <div class="LsHead"><h3 class="h3Tit bg-blue">上月停车场交易额排名</h3></div>
			            <div class="LsCent327">
			                <ul id="tabc2">
			                </ul>
			            </div>
			        </div>
			        <div class="MListWrap w327">
			            <div class="LsHead"><h3 class="h3Tit bg-blue">上一年停车场交易额排名</h3></div>
			            <div class="LsCent327">
			                <ul id="tabc3">
			                </ul>
			            </div>
			        </div>
			    </div>
			</div>
    </div>
  </div>
</div> 

<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script src="${pageContext.request.contextPath }/AdministratorAccount/layui/layui.js" charset="utf-8"></script>

<script>
	layui.use(['layer', 'form','element'], function(){
	  	var layer = layui.layer
	 	 ,form = layui.form
	 	 ,element=layui.element;
	  	
	  	element.init();
	  	//选项卡切换
	  	element.on('tab(docDemoTabBrief)', function(data){
	  		setTopObject(data.index);
	  	});
	});
</script>
<!--  -->

<script type="text/javascript">
	  function setTopObject(tab)
	 {
		 $.get('/ParkingTimes/findBytabTop',{tab:tab},function (data) {
			 if(tab==0)
			{
				var li="<li class='LsCtit I7'><span class='Nos'>排名</span><span class='tName'>停车场</span><span class='tRank'>次数</span></li>";
				var ls=1;
				for(var i=0;i<data.park.length;i++)
				{
					var str;
					if(i<3)
					{
						str="<span class='Nos BgRed'>"+(ls++)+"</span>";
					}else{
						str="<span class='Nos'>"+(ls++)+"</span>";
					}
					li+="<li class='LsClist'>"+str+"<span class='tName'>"+data.park[i].parkingInfo.parkingName+"</span><span class='tRank col-red03'>"+data.park[i].enterTimes+"</span></li>";
				}
				$("#tabs1").html(li); 
				li="<li class='LsCtit I7'><span class='Nos'>排名</span><span class='tName' style='width: 210px;'>停车场</span><span class='tRank'>次数</span></li>";
				ls=1;
				 for(var i=0;i<data.parkMoth.length;i++)
				{
					var str;
					if(i<3)
					{
						str="<span class='Nos BgRed'>"+(ls++)+"</span>";
					}else{
						str="<span class='Nos'>"+(ls++)+"</span>";
					}
					li+="<li class='LsClist'>"+str+"<span class='tName' style='width: 205px;''>"+data.parkMoth[i][3].parkingName+"</span><span class='tRank col-red03'>"+data.parkMoth[i][0]+"</span></li>";
				} 
				 $("#tabs2").html(li);  
				 lb=1;
				 li="<li class='LsCtit I7'><span class='Nos'>排名</span><span class='tName' style='width: 205px;''>停车场</span><span class='tRank'>次数</span></li>";
					for(var i=0;i<data.parkYear.length;i++)
					{
						var str;
						if(i<3)
						{
							str="<span class='Nos BgRed'>"+(lb++)+"</span>";
						}else{
							str="<span class='Nos'>"+(lb++)+"</span>";
						}
						li+="<li class='LsClist'>"+str+"<span class='tName' style='width: 200px;'>"+data.parkYear[i][3].parkingName+"</span><span class='tRank col-red03'>"+data.parkYear[i][0]+"</span></li>";
					}  
				$("#tabs3").html(li); 
			}if(tab==1){
				var li="<li class='LsCtit I7'><span class='Nos'>排名</span><span class='tName'>停车场</span><span class='tRank'>次数</span></li>";
				var ls=1;
				for(var i=0;i<data.park.length;i++)
				{
					var str;
					if(i<3)
					{
						str="<span class='Nos BgRed'>"+(ls++)+"</span>";
					}else{
						str="<span class='Nos'>"+(ls++)+"</span>";
					}
					li+="<li class='LsClist'>"+str+"<span class='tName'>"+data.park[i].parkingInfo.parkingName+"</span><span class='tRank col-red03'>"+data.park[i].leaveTimes+"</span></li>";
				}
				$("#tab1").html(li); 
				li="<li class='LsCtit I7'><span class='Nos'>排名</span><span class='tName' style='width: 210px;'>停车场</span><span class='tRank'>次数</span></li>";
				ls=1;
				 for(var i=0;i<data.parkMoth.length;i++)
				{
					var str;
					if(i<3)
					{
						str="<span class='Nos BgRed'>"+(ls++)+"</span>";
					}else{
						str="<span class='Nos'>"+(ls++)+"</span>";
					}
					li+="<li class='LsClist'>"+str+"<span class='tName' style='width: 205px;''>"+data.parkMoth[i][3].parkingName+"</span><span class='tRank col-red03'>"+data.parkMoth[i][1]+"</span></li>";
				} 
				 $("#tab2").html(li);  
				 ls=1;
				 li="<li class='LsCtit I7'><span class='Nos'>排名</span><span class='tName' style='width: 205px;''>停车场</span><span class='tRank'>次数</span></li>";
					for(var i=0;i<data.parkYear.length;i++)
					{
						var str;
						if(i<3)
						{
							str="<span class='Nos BgRed'>"+(ls++)+"</span>";
						}else{
							str="<span class='Nos'>"+(ls++)+"</span>";
						}
						li+="<li class='LsClist'>"+str+"<span class='tName' style='width: 200px;'>"+data.parkYear[i][3].parkingName+"</span><span class='tRank col-red03'>"+data.parkYear[i][1]+"</span></li>";
					}  
				$("#tab3").html(li); 
			}
				if(tab==2){
					var li="<li class='LsCtit I7'><span class='Nos'>排名</span><span class='tName'>停车场</span><span class='tRank'>交易额</span></li>";
					var ls=1;
					for(var i=0;i<data.parkday.length;i++)
					{
						var str;
						if(i<3)
						{
							str="<span class='Nos BgRed'>"+(ls++)+"</span>";
						}else{
							str="<span class='Nos'>"+(ls++)+"</span>";
						}
						li+="<li class='LsClist'>"+str+"<span class='tName'>"+data.parkday[i][2].parkingName+"</span><span class='tRank col-red03'>"+data.parkday[i][0]+"</span></li>";
					}
					$("#tabc1").html(li); 
					li="<li class='LsCtit I7'><span class='Nos'>排名</span><span class='tName' style='width: 210px;'>停车场</span><span class='tRank'>交易额</span></li>";
					ls=1;
					 for(var i=0;i<data.parkMoth.length;i++)
					{
						var str;
						if(i<3)
						{
							str="<span class='Nos BgRed'>"+(ls++)+"</span>";
						}else{
							str="<span class='Nos'>"+(ls++)+"</span>";
						}
						li+="<li class='LsClist'>"+str+"<span class='tName' style='width: 205px;''>"+data.parkMoth[i][2].parkingName+"</span><span class='tRank col-red03'>"+data.parkMoth[i][0]+"</span></li>";
					} 
					 $("#tabc2").html(li);  
					 ls=1;
					 li="<li class='LsCtit I7'><span class='Nos'>排名</span><span class='tName' style='width: 205px;''>停车场</span><span class='tRank'>交易额</span></li>";
						for(var i=0;i<data.parkYear.length;i++)
						{
							var str;
							if(i<3)
							{
								str="<span class='Nos BgRed'>"+(ls++)+"</span>";
							}else{
								str="<span class='Nos'>"+(ls++)+"</span>";
							}
							li+="<li class='LsClist'>"+str+"<span class='tName' style='width: 200px;'>"+data.parkYear[i][2].parkingName+"</span><span class='tRank col-red03'>"+data.parkYear[i][0]+"</span></li>";
						}  
					$("#tabc3").html(li); 
				}
		 });
	 }
	 setTopObject(0);
</script>

</body>
</html>