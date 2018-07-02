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
<style type="text/css">
		.my-skin .layui-layer-btn1{
			background-color: red;
			color:#fff;
			border:1px solid #fff;
		}
</style>

<body style="margin:20px;">
 
		<!-- 功能栏 -->
		<!-- <fieldset class="layui-elem-field layui-field-title">
			  <legend>功能栏</legend>
  			<div class="layui-field-box">内容区域
 				<blockquote class="layui-elem-quote">
					<a href="javascript:;" class="layui-btn layui-btn-small" onclick="layerOut('add')">
						<i class="layui-icon">&#xe608;</i> 添加信息
					</a>
					<a href="#" class="layui-btn layui-btn-small" id="import">
						<i class="layui-icon">&#xe608;</i> 导入信息
					</a>
					<a href="#" class="layui-btn layui-btn-small">
						<i class="fa fa-shopping-cart" aria-hidden="true"></i> 导出信息
					</a>
					<a href="#" class="layui-btn layui-btn-small" id="getSelected">
						<i class="fa fa-shopping-cart" aria-hidden="true"></i> 获取全选信息
					</a>
					
				</blockquote>
  			</div>
		</fieldset> -->
			
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
  	<legend>停车场申请信息</legend>
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
    ,url: '/ParkingInfo/getUpdateParkinginfo'
    ,where:{
			currentPageNo:currentPageNo, 
			pageSize:pageSize, 
			/* parkingSpaceNumber: $('#parkingSpaceNumber_query').val(),
        	'parkingDictionary.areaName': $('#parkingDictionary_areaName_query').val() */
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
      ,{field:'creationDate', type:'date',title: '提交时间', sort: true}
      ,{fixed: 'right', title: '操作', align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
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
  
/*   var $ = layui.$, active = {
    reload: function(){
      //执行重载
      table.reload('testReload', {
//         page: {
//           	curr: 1 //重新从第 1 页开始
//         },
        where: {
        	parkingSpaceNumber: $('#parkingSpaceNumber_query').val(),
        	'parkingDictionary.areaName': $('#parkingDictionary_areaName_query').val()
        }
      });
    }
  }; */
  
/*   $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
 */
	//监听操作栏
	table.on('tool(user)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		
// 		obj.update({//局部更新
// 	 		    parkingSpaceNumber: data2.parkingSpaceNumber,
// 	 	});
		if(layEvent === 'detail'){ //查看
			layerOut(layEvent, data.id);
		}/* else if(layEvent === 'del'){ //删除
		 	 layer.confirm('真的删除行么', function(index){
				//向服务端发送删除指令
				$.post('/parkingspace/delparkingspace', {id:data.id}, function(data){
					var result;
					(data.result == "true") ? result="删除成功": result="删除失败";
					layer.alert(result, {skin: 'layui-layer-molv'}, function(){
						layer.closeAll();
						//更新
						active["reload"] ? active["reload"].call(this) : '';//重载表格
					});
				})
// 		 		obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
			 })
		} */else if(layEvent === 'edit'){ //编辑
			layerOut(layEvent, data.id, obj);
		    //同步更新缓存对应的值，在layerOut()里面更新
		}
	})
  
});
}
pageStart();
//----------------------------------------------表格显示数据+分页------------------------------End

//----------------------------------实体的编辑框弹出：新增，修改，查看-----------------------Start
function layerOut(eventType, id, obj){//obj是传进来，给修改成功后：要更新的行Dom对象
	//操作栏
	layui.use(['layer', 'form'], function(){
		var $ = layui.$;
	 	var layer = layui.layer;
		var Examination=0;	 	
	 
			 	if(eventType=="detail")
				{
			 		
			 		$.get('/ParkingInfo/getParkingInfoUpdateByid', {eventType:eventType,id:id}, function(form) {
				 		layer.open({
							type: 1,
							title: '停车场信息',
							content: form,
							btn: ['确定', '取消'],
			// 				shade: false,
							scrollbar: false,
							offset: ['100px', '30%'],
							area: ['600px', '400px'],
							zIndex: 19950924,
							maxmin: true,
							success: function(layero, index) {
								//弹出窗口成功后渲染表单
								var form = layui.form;
								form.render();
							}
		 				})
		  			 })
				}else
				{
					$.get('/ParkingInfo/getParkingInfoUpdateByid', {eventType:eventType,id:id}, function(form) {
				 		layer.open({
							type: 1,
							title: '审批停车场信息',
							content: form,
							btn: ['通过','拒绝', '取消'],
							btn1: function(index, layero){
							    //按钮【按钮一】的回调
							    Examination=4;
								 $('form.layui-form').find('input[lay-filter=save]').click(); 
							},btn2: function(index, layero){
								//按钮二的回调
								Examination=3;
								 $('form.layui-form').find('input[lay-filter=save]').click(); 
							},
			// 				shade: false,
							skin:'my-skin',
							scrollbar: false,
							offset: ['100px', '30%'],
							area: ['600px', '400px'],
							zIndex: 19950924,
							maxmin: true,
							/* ,yes: function(index) {
							//触发表单的提交事件
							$('form.layui-form').find('button[lay-filter=save]').click();
						}, */
						success: function(layero, index) {
							//弹出窗口成功后渲染表单
							var form = layui.form;
							form.render();
							//重写提交事件
							form.on('submit()', function(data) {
								alert(JSON.stringify(data.field)); 
								//加入参数
								data.field.Examination=Examination;
								
			 						$.post("/ParkingInfo/UpdateParkingTemporaryInfo",data.field, function(data){
			 							var result;
			 							(data.result == "true") ? result="审批完成":result="审批失败";
			 	 						parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
			 	 							parent.layer.closeAll();
			 	 							layer.closeAll();
			 	 							location.reload();//审批后刷新页面
			 	 						});
			 						})
								})
								return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。									
							}
						})
					})  
				}	
		})
	/* }) */
}
//-------------------------------实体的编辑框弹出：新增，修改，查看-----------------------------End

</script>

<!-- 工具条 -->
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">审批</a>
 
</script>
<!--  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a> -->
<!-- 复杂表头属性绑定 -->
 <script type="text/html" id="tpl_ParkingTemporaryInfo_districtOrCounty_districtOrCounty">
	{{# if(d.districtOrCounty != null){ }}
		{{d.districtOrCounty.districtOrCounty}}
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