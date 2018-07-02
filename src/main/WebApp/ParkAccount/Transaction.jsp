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
  			<div class="layui-field-box">
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
 
  <fieldset class="layui-elem-field layui-field-title">
  	<legend>购买车位信息</legend>
 </fieldset>
  <table class="layui-hide" id="tables" lay-filter="buyParkingSpace"></table> 
  
   
 <fieldset class="layui-elem-field layui-field-title">
  	<legend>租用车位信息</legend>
 </fieldset>
 <table class="layui-hide" id="table" lay-filter="user"></table> 
 
<script src="${pageContext.request.contextPath }/ParkAccount/layui/layui.js" charset="utf-8"></script>
<script>
//----------------------------------------------表格显示数据------------------------------Start
//购买审批记录
   layui.use('table',function(){
		var $=layui.$;
		  var table = layui.table;
		  table.render({
			  
		 
			    elem: '#tables'
			    ,height: 'full-600'
			    ,url: '/Transaction/selectgouspTransaction'
			    ,method: 'post' //如果无需自定义HTTP类型，可不加该参数
			    ,id: 'testReload'
			    ,even: true
			    ,cols: [[
			      {checkbox: true, fixed: 'left'}
			      ,{field:'parkingInfo', title: '停车场名称',templet:'#tpl_ParkingAccount_parkinfo_parkingName'}
			      ,{field:'parkingSpace', title: '停车位编号',templet:'#tpl_parkingSpace_parkingSpace_parkingSpaceNumber'}
			      ,{field:'carOwnerUser',title: '车主名称',templet:'#tpl_CarOwnerUser_carOwnerUser_userName'}
			      ,{field:'transactionType', title: '交易类型',templet:'#tpl_TransactionType_transactionType_typeName'}
			      ,{field:'startDate',title: '开始时间'}
			      ,{field:'endDate', title: '结束时间'}
			      ,{field:'transactionAmount',title: '交易金额'}
			      ,{field:'transactionStatusList',title: '交易状态',templet:'#tpl_TransactionStatus_transactionStatusList_transactionStatusName'}
			      ,{fixed: 'right', title: '操作', align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
			    ]]
		  });		
		  var $ = layui.$, active = {
				    reload: function(){
				      //执行重载
				      table.reload('testReload', {
				        where: {
				        }
				      });
				    }
				  };
				  
	
		  
		 //购买车位
			table.on('tool(buyParkingSpace)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			var tr = obj.tr; //获得当前行 tr 的DOM对象
			if(layEvent === 'detail'){ //查看
				layerOut(layEvent, data.id);
			}else if(layEvent === 'del'){ //删除
			 	 layer.confirm('真的拒绝购买?', function(index){
					//向服务端发送删除指令
					$.post('/Transaction/updateTransaction', {id:data.id}, function(data){
						var result;
						(data.result == "true") ? result="拒绝成功": result="拒绝失败";
						layer.alert(result, {skin: 'layui-layer-molv'}, function(){
							layer.closeAll();
							//更新
							active["reload"] ? active["reload"].call(this) : '';//重载表格
							location.reload();//新增后刷新页面
						});
					})
//	 		 		obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
		         
				 })
			}
			else if(layEvent === 'ojbk'){ 
			 	 layer.confirm('确认付款购买？', function(index){
					//向服务端发送删除指令
					$.post('/Transaction/updateTransactionok', {id:data.id}, function(data){
						var result;
						(data.result == "true") ? result="购买成功": result="拒绝失败";
						layer.alert(result, {skin: 'layui-layer-molv'}, function(){
							layer.closeAll();
							//更新
							active["reload"] ? active["reload"].call(this) : '';//重载表格
							location.reload();//新增后刷新页面
						});
					})
				 })
			}
		}) 
		  
	}); 
	 
	
	
layui.use(['table','laypage'], function(){
	var $ = layui.$;
  	var table = layui.table,
 	  laypage = layui.laypage;
	//方法级渲染
  table.render({
    elem: '#table'
    ,height: 'full-300'
    ,url: '/Transaction/selectspTransaction'
    ,method: 'post' //如果无需自定义HTTP类型，可不加该参数
    ,id: 'testReload'
//     ,page: {theme: '#c00',  limits:[5,10,25,50], groups:5, limit:25/* , curr:7 */, id: 'abc1'}
    ,even: true
    ,cols: [[
      {checkbox: true, fixed: 'left'}
      ,{field:'parkingInfo', title: '停车场名称',templet:'#tpl_ParkingAccount_parkinfo_parkingName'}
      ,{field:'parkingSpace', title: '停车位编号',templet:'#tpl_parkingSpace_parkingSpace_parkingSpaceNumber'}
      ,{field:'carOwnerUser',title: '车主名称',templet:'#tpl_CarOwnerUser_carOwnerUser_userName'}
      ,{field:'transactionType', title: '交易类型',templet:'#tpl_TransactionType_transactionType_typeName'}
      ,{field:'startDate',title: '开始时间'}
      ,{field:'endDate', title: '结束时间'}
      ,{field:'transactionAmount',title: '交易金额'}
      ,{field:'transactionStatusList',title: '交易状态',templet:'#tpl_TransactionStatus_transactionStatusList_transactionStatusName'}
      ,{fixed: 'right', title: '操作', align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
    ]]

  });
  
  var $ = layui.$, active = {
    reload: function(){
      //执行重载
      table.reload('testReload', {
        where: {
        }
      });
    }
  };
  

	//租用车位
	table.on('tool(user)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent === 'detail'){ //查看
			layerOut(layEvent, data.id);
		}else if(layEvent === 'del'){ //删除
		 	 layer.confirm('真的拒绝行么', function(index){
				//向服务端发送删除指令
				$.post('/Transaction/updateTransaction', {id:data.id}, function(data){
					var result;
					(data.result == "true") ? result="拒绝成功": result="拒绝失败";
					layer.alert(result, {skin: 'layui-layer-molv'}, function(){
						layer.closeAll();
						//更新
						active["reload"] ? active["reload"].call(this) : '';//重载表格
						location.reload();//新增后刷新页面
					});
				})
// 		 		obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
	         
			 })
		}
		else if(layEvent === 'ojbk'){ //删除
		 	 layer.confirm('确认开通服务？', function(index){
				//向服务端发送删除指令
				$.post('/Transaction/updateTransactiontype', {id:data.id}, function(data){
					var result;
					(data.result == "true") ? result="申请已通过": result="拒绝失败";
					layer.alert(result, {skin: 'layui-layer-molv'}, function(){
						layer.closeAll();
						//更新
						active["reload"] ? active["reload"].call(this) : '';//重载表格
						location.reload();//新增后刷新页面
					});
				})
			 })
		}
	})
	
	
	/* //购买车位
		table.on('tool(buyParkingSpace)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent === 'detail'){ //查看
			layerOut(layEvent, data.id);
		}else if(layEvent === 'del'){ //删除
		 	 layer.confirm('真的拒绝行么', function(index){
				//向服务端发送删除指令
				$.post('/Transaction/updateTransaction', {id:data.id}, function(data){
					var result;
					(data.result == "true") ? result="拒绝成功": result="拒绝失败";
					layer.alert(result, {skin: 'layui-layer-molv'}, function(){
						layer.closeAll();
						//更新
						active["reload"] ? active["reload"].call(this) : '';//重载表格
					});
				})
// 		 		obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
	         
			 })
		}
		else if(layEvent === 'ojbk'){ 
		 	 layer.confirm('确认付款购买？', function(index){
				//向服务端发送删除指令
				$.post('/Transaction/updateTransactionok', {id:data.id}, function(data){
					var result;
					(data.result == "true") ? result="购买成功": result="拒绝失败";
					layer.alert(result, {skin: 'layui-layer-molv'}, function(){
						layer.closeAll();
						//更新
						active["reload"] ? active["reload"].call(this) : '';//重载表格
					});
				})
			 })
		}
	}) */
	
	
	
  
});
/* }
pageStart(); */
//----------------------------------------------表格显示数据+分页------------------------------End

//----------------------------------实体的编辑框弹出：新增，修改，查看-----------------------Start
function layerOut(eventType, id,tab,obj){//obj是传进来，给修改成功后：要更新的行Dom对象
	//操作栏
	layui.use(['layer', 'form'], function(){
		var $ = layui.$;
	 	var layer = layui.layer;
	 	
	 	$.get('/Transaction/layeroptions', {eventType:eventType, id:id}, function(form) {
	 		layer.open({
				type: 1,
				title: '车位交易信息',
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
					

				},
			});   
		})
	})
}
//-------------------------------实体的编辑框弹出：新增，修改，查看-----------------------------End

</script>

<!-- 工具条 -->
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-xs" lay-event="ojbk">通过</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">拒绝</a>
</script>

<!-- 复杂表头属性绑定 -->
 <script type="text/html" id="tpl_ParkingAccount_parkinfo_parkingName">
	{{# if(d.parkingInfo != null){ }}
		{{d.parkingInfo.parkingName}}
	{{# } }}
</script>
 <script type="text/html" id="tpl_parkingSpace_parkingSpace_parkingSpaceNumber">
	{{# if(d.parkingSpace != null){ }}
		{{d.parkingSpace.parkingSpaceNumber}}
	{{# } }}
</script>
 <script type="text/html" id="tpl_CarOwnerUser_carOwnerUser_userName">
	{{# if(d.carOwnerUser != null){ }}
		{{d.carOwnerUser.userName}}
	{{# } }}
</script>
 <script type="text/html" id="tpl_TransactionType_transactionType_typeName">
	{{# if(d.transactionType != null){ }}
		{{d.transactionType.typeName}}
	{{# } }}
</script>
 <script type="text/html" id="tpl_TransactionStatus_transactionStatusList_transactionStatusName">
	{{# if(d.transactionStatusList != null)
		{ }}
		{{d.transactionStatusList[0].transactionStatusName}}
	{{# } }}
</script>
</body>
</html>