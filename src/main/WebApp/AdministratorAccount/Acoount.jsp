<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/ParkAccount/layui/css/layui.css"  media="all">
</head>
<body style="margin:20px;">
 
		<!-- 功能栏 -->
		<fieldset class="layui-elem-field layui-field-title">
			  <legend>功能栏</legend>
  			<div class="layui-field-box">内容区域
 				<blockquote class="layui-elem-quote">
				<!-- 	<a href="javascript:;" class="layui-btn layui-btn-small" onclick="layerOut('add')">
						<i class="layui-icon">&#xe608;</i> 添加信息
					</a>
					<a href="#" class="layui-btn layui-btn-small" id="import">
						<i class="layui-icon">&#xe608;</i> 导入信息
					</a> -->
					<a href="#" class="layui-btn layui-btn-small">
						<i class="fa fa-shopping-cart" aria-hidden="true"></i> 导出信息
					</a>
				<!-- 	<a href="#" class="layui-btn layui-btn-small" id="getSelected">
						<i class="fa fa-shopping-cart" aria-hidden="true"></i> 获取全选信息
					</a> -->
				</blockquote>
  			</div>
		</fieldset>
			
		<!-- 查询条件栏 -->
		<!-- <fieldset class="layui-elem-field layui-field-title">
			  <legend>查询条件栏</legend>
			  
  			<div class="layui-field-box">内容区域
  			
 				<blockquote class="layui-elem-quote">
					<div class="demoTable">
	  					停车位编号：
  						<div class="layui-inline">
		    				<input class="layui-input" id="parkingSpaceNumber_query" autocomplete="off">
  						</div>
  						区域编号名称：
  						<div class="layui-inline">
		    				<input class="layui-input" id="parkingDictionary_areaName_query" autocomplete="off">
  						</div>
	  					<button class="layui-btn" data-type="reload">
	  						<i class="layui-icon">&#xe615;</i> 搜索
	  					</button>
					</div>
				</blockquote>
  			</div>
		</fieldset>
		 -->
 
  <fieldset class="layui-elem-field layui-field-title">
  	<legend>账号信息</legend>
 </fieldset>
  <table class="layui-hide" id="tables" lay-filter="parkinginfo"></table> 
  

 
<!--  <div id="psPage"></div>  -->
               

<%-- <script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script> --%>
<script src="${pageContext.request.contextPath }/ParkAccount/layui/layui.js" charset="utf-8"></script>
<script>
//----------------------------------------------表格显示数据------------------------------Start

//账号信息表格
layui.use('table',function(){
	var $=layui.$;
	  var table = layui.table;
	  table.render({
		    elem: '#tables'
		    ,height: 'full-600'
		    ,url: '/ParkingInfo/getparkinginfo'
		    ,method: 'post' //如果无需自定义HTTP类型，可不加该参数
		    ,even: true
		    ,cols: [[
		      {checkbox: true, fixed: 'left'}
		      ,{field:'id', title: 'ID', sort: true, fixed: 'left'}
		      ,{field:'accountName', title: '账号名称'}
		      ,{field:'registrationDate', type:'date',title: '账号注册时间'}
		      ,{fixed: 'right', title: '操作', align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
		    ]]
	  });		
		//监听操作栏
		table.on('tool(parkinginfo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			var tr = obj.tr; //获得当前行 tr 的DOM对象
			if(layEvent === 'detail'){ //查看
				layerOut(layEvent, data.id);
			}else if(layEvent === 'edit'){ //编辑
				layerOut(layEvent, data.id,obj);
			    //同步更新缓存对应的值，在layerOut()里面更新
			} 
		})
});

//----------------------------------------------表格显示数据+分页------------------------------End

//----------------------------------实体的编辑框弹出：新增，修改，查看-----------------------Start
function layerOut(eventType, id, obj){//obj是传进来，给修改成功后：要更新的行Dom对象
	//操作栏
	layui.use(['layer', 'form'], function(){
		var $ = layui.$;
	 	var layer = layui.layer;
	 		$.get('/ParkingAccount/viewParkingAcount',{eventType:eventType}, function(form) {
		 		layer.open({
					type: 1,
					title: '账号信息',
					content: form,
					btn: ['确定', '取消'],
	// 				shade: false,
					scrollbar: false,
					offset: ['100px', '30%'],
					area: ['600px', '400px'],
					zIndex: 19950924,
					maxmin: true,
					yes: function(index) {
						if(eventType == "detail"){
							layer.closeAll();
							return;
						}
						//触发表单的提交事件
						$('form.layui-form').find('input[lay-filter=save]').click();
					},
					success: function(layero, index) {
						//弹出窗口成功后渲染表单
						var form = layui.form;
						form.render();
						if(eventType == "detail"){//查看无需重写提交事件
	 						return ;
						}
						//重写提交事件
						form.on('submit()', function(data) {
							  $.get('/ParkingAccount/parkingPasswordValidation', {oldPassword:$("input[name=accountName]").val(),newPassword:$("input[name=newPassword]").val(),newPassword1:$("input[name=newPassword1]").val()}, function(data) {
									var result=data.result;
								    if(result==true)
									{
								    	$.post("/ParkingAccount/parkingPasswordUpdate",{newPassword:$("input[name=newPassword]").val()}, function(data){
				 							var result;
				 							(data.result == "true") ? result="修改成功": result="修改失败";
				 	 						parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
				 	 							parent.layer.closeAll();
				 	 							layer.closeAll();
				 	 							$.post("/ParkingAccount/logout",function(data){
				 	 								window.parent.location.href=data.url; 
				 	 							});
				 	 						});
				 						})
									}else{
									    parent.layer.alert(result, {skin: 'layui-layer-molv'},function(){
			 	 							parent.layer.closeAll();
			 	 							layer.closeAll(); 
			 	 						});	
									}
								});
								}) 
							return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。									
						}
					});
	// 				end: function() {
	// 				}
				});   
	})
}
//-------------------------------实体的编辑框弹出：新增，修改，查看-----------------------------End

</script>

<!-- 工具条 -->
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</script>

<!-- 复杂表头属性绑定 -->
 <script type="text/html" id="tpl_ParkingAccount_parkinfo_id">
	{{# if(d.parkingInfo != null){ }}
		{{d.parkingInfo.id}}
	{{# } }}
</script>
 <script type="text/html" id="tpl_ParkingAccount_parkinfo_parkingName">
	{{# if(d.parkingInfo != null){ }}
		{{d.parkingInfo.parkingName}}
	{{# } }}
</script>
 <script type="text/html" id="tpl_ParkingAccount_parkinfo_parkingAddress">
	{{# if(d.parkingInfo != null){ }}
		{{d.parkingInfo.parkingAddress}}
	{{# } }}
</script>
 <script type="text/html" id="tpl__ParkingAccount_parkinfo_charge">
	{{# if(d.parkingInfo != null){ }}
		{{d.parkingInfo.charge}}
	{{# } }}
</script>
 <script type="text/html" id="tpl_ParkingAccount_parkinfo_parkingSpaceCount">
	{{# if(d.parkingInfo != null){ }}
		{{d.parkingInfo.parkingSpaceCount}}
	{{# } }}
</script>
<script type="text/html" id="tpl_ParkingAccount_parkinfo_ifOpenSharingPlatform">
	{{# if(d.parkingInfo != null){ }}
		{{d.parkingInfo.ifOpenSharingPlatform}}
	{{# } }}
</script>

 <script type="text/html" id="tpl_districtOrCounty_districtOrCounty">
	{{# if(d.districtOrCounty != null){ }}
		{{d.districtOrCounty.districtOrCounty}}
	{{# } }}
</script>
 <script type="text/html" id="tpl_street_streetname">
	{{# if(d.street != null){ }}
		{{d.street.streetName}}
	{{# } }}
</script>
</body>
</html>