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
  			<div class="layui-field-box"><!-- 内容区域 -->
 				<blockquote class="layui-elem-quote">
					<!-- <a href="javascript:;" class="layui-btn layui-btn-small" onclick="layerOut('add')"> -->
					<!-- 	<i class="layui-icon">&#xe608;</i> 添加信息
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
		</fieldset> -->
		
 
 <fieldset class="layui-elem-field layui-field-title">
  	<legend>修改申请记录</legend>
 </fieldset>
 <table class="layui-hide" id="table" lay-filter="user"></table> 
 <div id="psPage"></div> 
               

<%-- <script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script> --%>
<script src="${pageContext.request.contextPath }/ParkAccount/layui/layui.js" charset="utf-8"></script>
<script>
//----------------------------------------------表格显示数据+分页------------------------------Start
function pageStart(currentPageNo, pageSize){
layui.use(['table','laypage'], function(){
	var $ = layui.$;
  var table = layui.table,
 	  laypage = layui.laypage;

	//方法级渲染
  table.render({
    elem: '#table'
    ,height: 'full-300'
    ,url: '/ParkingInfo/getParkingUpdateInfoList'
    ,where:{
			currentPageNo:currentPageNo, 
			pageSize:pageSize, 
			parkingSpaceNumber: $('#parkingSpaceNumber_query').val(),
        	'parkingDictionary.areaName': $('#parkingDictionary_areaName_query').val()
		} //传参*/  
    ,method: 'post' //如果无需自定义HTTP类型，可不加该参数
    ,id: 'testReload'
//     ,page: {theme: '#c00',  limits:[5,10,25,50], groups:5, limit:25/* , curr:7 */, id: 'abc1'}
    ,even: true
    ,cols: [[
        {checkbox: true, fixed: 'left'}
       ,{field:'id', title: 'ID', sort: true, fixed: 'left'}
       ,{field:'parkingName', title: '停车位名称'}
       ,{field:'parkingAddress',title: '停车场位置', sort: true}
       ,{field:'charge',title: '收费金额(/h)'}
       ,{field:'parkingSpaceCount', title: '车位数量'}
       ,{field:'districtOrCounty',templet:'#tpl_ParkingTemporaryInfo_districtOrCounty_districtOrCounty', title: '区/县'}
       ,{field:'street', templet:'#tpl_ParkingTemporaryInfo_street_street', title: '街道'}
       ,{field:'examineStatusInfo', templet:'#tpl_ParkingTemporaryInfo_examineStatusInfo_examineName', title: '审批状态'} 
       ,{field:'creationDate',title: '提交时间', sort: true}
       ,{fixed: 'right', title: '操作', align:'center', toolbar:'#barDemo'} //这里的toolbar值是模板元素的选择器
     ]]
  	,done: function(data){
      //如果是异步请求数据方式，data即为你接口返回的信息。  
      //如果是直接赋值的方式，data即为：{data: [], count: 99} data为当前页数据、count为数据总长度  
      laypage.render({  
          elem:'psPage'  
          ,count:data.count  
          ,curr:data.currentPageNo
          ,groups:5
		  ,limits:[5, 10, 25, 50]
          ,limit:data.pageSize
          ,layout: ['prev', 'page', 'next', 'skip','count','limit']  
          ,jump:function (obj,first) {
              if(!first){  
            	  //obj包含了当前分页的所有参数，比如：
//             	  console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
//             	  console.log(obj.limit); //得到每页显示的条数
                  currentPageNo = obj.curr;
                  pageSize = obj.limit;  
                  pageStart(currentPageNo, pageSize);
              }  
          }  
      })  
  }  
  });
  
		  var $ = layui.$, active = {
		    reload: function(){
		      //执行重载
		      table.reload('testReload', {
		//         page: {
		//           	curr: 1 //重新从第 1 页开始
		//         },
		        where: {
/* 		        	parkingSpaceNumber: $('#parkingSpaceNumber_query').val(),
		        	'parkingDictionary.areaName': $('#parkingDictionary_areaName_query').val() */
		        }
		      });
		    }
		  };
  
	  $('.demoTable .layui-btn').on('click', function(){
	    var type = $(this).data('type');
	    active[type] ? active[type].call(this) : '';
	  });

	//监听操作栏
	table.on('tool(user)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		
		if(layEvent === 'detail'){ //查看
			layerOut(layEvent, data.id);
		}else if(layEvent === 'edit'){ //撤销
			layer.confirm('确认撤销?', function(index){
			$.post("/ParkingInfo/RevokeParkingInfoUp",{id:data.id}, function(data1){
					var result;
					(data1.result == "true") ? result="撤销成功": result="撤销失败";
					parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
						parent.layer.closeAll();
						layer.closeAll();
					});
					$(obj.tr).find("#Revoke").remove();
					//进行局部修改
					 $.post("/ParkingInfo/UpdateJspPark",{id:data.id}, function(data2){
						 $(obj.tr).find("td div:eq(9)").html("<a style='color:red'>"+data2.ex+"</a>");
					})
				})
			})
		}
    }); 
 })
 
}
pageStart();
//----------------------------------------------表格显示数据+分页------------------------------End

//----------------------------------实体的编辑框弹出：新增，修改，查看-----------------------Start
function layerOut(eventType, id, obj){//obj是传进来，给修改成功后：要更新的行Dom对象
	//操作栏
	layui.use(['layer', 'form'], function(){
		var $ = layui.$;
	 	var layer = layui.layer;
	 	
	 	$.get('/ParkingInfo/getParkingInfoUpdateByid', {eventType:eventType, id:id}, function(form) {
	 		layer.open({
				type: 1,
				title: '停车位信息',
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
					$('form.layui-form').find('button[lay-filter=save]').click();
				},
				success: function(layero, index) {
					//弹出窗口成功后渲染表单
					var form = layui.form;
					form.render();
					if(eventType == "detail"){//查看无需重写提交事件
 						return ;
					}
					
					//重写提交事件
					/* form.on('submit()', function(data) {
// 						console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
// 						console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
// 						console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
// 						调用父窗口的layer对象
// 						parent.layer.open({})

// 						这里可以写ajax方法提交表单
						/* if(eventType=="add"){
	 						$.post("/parkingspace/addparkingspace", data.field, function(data){
	 							var result;
	 							(data.result == "true") ? result="新增成功": result="新增失败";
	 	 						parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
	 	 							parent.layer.closeAll();
	 	 							layer.closeAll();
		 	 						location.reload();//新增后刷新页面
	 	 						});
	 						}) */
						/* }else */ 
	 						/* $.post("/parkingspace/updateparkingspace", data.field, function(data1){
	 							var result;
	 							(data1.result == "true") ? result="撤销成功": result="撤销失败";
	 	 						parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
	 	 							parent.layer.closeAll();
	 	 							layer.closeAll();
	 	 						});
	 	 						//进行局部修改
	 	 						$.post("/parkingspace/getparkingspacebyid", data.field, function(data2){
	 	 							$(obj.tr).find("td div:eq(3)").html("<a style='color:red'>"+data2.parkingSpaceNumber+"</a>");
	 	 							$(obj.tr).find("td div:eq(4)").html("<a style='color:red'>"+data2.parkingDictionary.areaName+"</a>");
	 	 							$(obj.tr).find("td div:eq(5)").html("<a style='color:red'>"+data2.parkingSpaceStatus.parkingSpaceStatusName+"</a>");
	 	 						})
	 						}) */
						/* if(eventType == "edit"){
						} */
						/*return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。									
					}); */
				},
// 				end: function() {
// 				}
			});   
		})
	})
}
//-------------------------------实体的编辑框弹出：新增，修改，查看-----------------------------End

</script>

<!-- 工具条 -->
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
 	{{#  
		var date1=new Date();
		var date2=new Date(d.creationDate);
		var s1 = date1.getTime(),s2 = date2.getTime();
		var total = (s1 - s2)/1000;
		var day = parseInt(total / (24*60*60));
		if(day<30 && d.examineStatusInfo.id==1 ){ }}
    	<a class="layui-btn layui-btn-xs" id="Revoke" lay-event="edit">撤销</a>
  	{{#  } }}
</script>

<!--
<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a> -->
<!-- 复杂表头属性绑定 -->
 <script type="text/html" id="tpl_ParkingTemporaryInfo_districtOrCounty_districtOrCounty">
	{{# if(d.districtOrCounty != null){ }}
		{{d.districtOrCounty.districtOrCountyName}}
	{{# } }}
</script>
 <script type="text/html" id="tpl_ParkingTemporaryInfo_street_street">
	{{# if(d.street != null){ }}
		{{d.street.streetName}}
	{{# } }}
</script>
 <script type="text/html" id="tpl_ParkingTemporaryInfo_examineStatusInfo_examineName">
	{{# if(d.examineStatusInfo != null){ }}
		{{d.examineStatusInfo.examineName}}
	{{# } }}
</script>
</body>
</html>