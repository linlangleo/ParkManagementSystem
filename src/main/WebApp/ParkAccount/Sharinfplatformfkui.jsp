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
					<a href="javascript:;" class="layui-btn layui-btn-small" onclick="layerOut('add')">
						<i class="layui-icon">&#xe608;</i> 导出信息
					</a> 
<!-- 					<a href="javascript:;" class="layui-btn layui-btn-small" onclick="layerback('data')">
						<i class="layui-icon">&#xe608;</i> 加入黑名单
					</a> -->
				
				</blockquote>
  			</div>
		</fieldset>
		
			
		<!-- 查询条件栏 -->
		<fieldset class="layui-elem-field layui-field-title">
			  <legend>查询条件栏</legend>
			  
  			<div class="layui-field-box"><!-- 内容区域 -->
  			
 				<blockquote class="layui-elem-quote">
					<div class="demoTable">
	  					搜索停车位编号：
  						<div class="layui-inline">
		    				<input class="layui-input" name="title" id="demoReload" autocomplete="off">
  						</div>
	  					<button class="layui-btn" data-type="reload">
	  						<i class="layui-icon">&#xe615;</i> 搜索
	  					</button>
					</div>
				</blockquote>
  			</div>
		</fieldset>
		
 
 <fieldset class="layui-elem-field layui-field-title">
  	<legend>数据列表</legend>
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
    ,url: '/feedbackanddispute/getallfdbypage'
    ,where:{currentPageNo:currentPageNo, pageSize:pageSize, title: $('#demoReload').val()} //传参*/  
    ,method: 'post' //如果无需自定义HTTP类型，可不加该参数
    ,id: 'testReload'
//     ,page: {theme: '#c00',  limits:[5,10,25,50], groups:5, limit:25/* , curr:7 */, id: 'abc1'}
    ,even: true
    ,cols: [[
        {checkbox: true, fixed: true}
        ,{field:'id', title: 'ID', width:80, sort: true, fixed: 'left'}
        ,{field:'feedbackCarOwnerId', templet:'#tpl_carOwnerUser_realName', title: '反馈车主', width:200}
        ,{field:'title', title: '标题', width:200, sort: true}
        ,{field:'description', title: '描述', width:200,sort: true}
        ,{field:'submitDate', title: '提交时间', sort: true, width:200}
        ,{field:'sharingPlatformId', templet:'#tpl_sharingPlatform_parkingInfo_parkingName', title: '所在停车场', width:200}
        ,{field:'sharingPlatformId', templet:'#tpl_sharingPlatform_sharer_realName', title: '共享车主', width:200}
        ,{fixed: 'right', title: '操作', align:'center', toolbar: '#barDemo'}
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
      var demoReload = $('#demoReload');
      //执行重载
      table.reload('testReload', {
//         page: {
//           	curr: 1 //重新从第 1 页开始
//         },
        where: {
        	title: demoReload.val()
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
		}else if(layEvent === 'del'){ //删除
		 	 layer.confirm('真的删除行么', function(index){
				//向服务端发送删除指令
				$.post('/feedbackanddispute/delfeedbackanddispute', {id:data.id}, function(data){
					var result;
					(data.result == "true") ? result="删除成功": result="删除失败";
					layer.alert(result, {skin: 'layui-layer-molv'}, function(){
						layer.closeAll();
						//更新
		 				active["reload"] ? active["reload"].call(this) : '';//重载表格
					});
				})
//		 		obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
			 })
		}else if(layEvent === 'edit'){ //编辑
			layerOut(layEvent, data.id, obj);
		    //同步更新缓存对应的值，在layerOut()里面更新
		}else if(layEvent === 'black'){
			var layer = layui.layer;
			$.post('/feedbackanddispute/blackList', {id:data.id}, function(data){
			layer.open({
				  id:10092,						//定义一个ID,用于区分
				  type: 2, 						//弹窗类型
				  btn: ['确定', '取消'],
				  area: ['400px', '400px'],		//宽高设置
				  anim: 0,						//弹出动画
				  resize:false,
				  scrollbar: false,				//浏览器是否出现滚动条
				  content: '/ParkAccount/layerTemp/BacklistTemp.jsp' 
				});
			})
		}
	})
//表格复选框监听事件	
	table.on('checkbox(user)', function(data){
	 	if(data.data!=null){
	 		return data;	
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

	 	//用于判断修改或者新增是否成功
//	 	var NewOfAdd, NewOfEdit;
	 		
	 	$.post('/feedbackanddispute/layeroptions', {eventType:eventType, id:id}, function(form) {
	 		layer.open({
				type: 1,
				title: '停车位信息',
				content: form,
				btn: ['确定', '取消'],
				shade: false,
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
					if(eventType == "detail"){
						return ;
					}
					form.on('submit()', function(data) {
//						console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
//						console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
//						console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
//						调用父窗口的layer对象
//						parent.layer.open({})

//						这里可以写ajax方法提交表单
						if(eventType=="add"){
	 						$.post("/feedbackanddispute/addfeedbackanddispute", data.field, function(data){
	 							var result;
	 							(data.result == "true") ? result="新增成功": result="新增失败";
	 	 						parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
	 	 							parent.layer.closeAll();
	 	 							layer.closeAll();
		 	 						location.reload();//新增后刷新页面
	 	 						});
	 						})
						}else if(eventType == "edit"){
	 						$.post("/feedbackanddispute/updatefeedbackanddispute",data.field,function(data1){
	 							var result;
	 							(data1.result == "true") ? result="修改成功": result="修改失败";
	 	 						parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
	 	 							parent.layer.closeAll();
	 	 							layer.closeAll();
	 	 						});
		 	 			 		//进行局部修改
	 	 						$.post("/feedbackanddispute/getfeedbackanddisputebyid", data.field, function(data2){
	 	 							$(obj.tr).find("td div:eq(4)").html("<a style='color:red'>"+data2.title+"</a>");
	 	 							$(obj.tr).find("td div:eq(5)").html("<a style='color:red'>"+data2.description+"</a>");
	 	 						})
	 						})
						}
						return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。									
					});
				},
//				end: function() {
//				}
			});   
		})
	})
}


function layerOuts(eventType, id, obj){//obj是传进来，给修改成功后：要更新的行Dom对象
	//操作栏
	layui.use(['layer', 'form'], function(){
		var $ = layui.$;
	 	var layer = layui.layer;
	 	
	 	$.post('/feedbackanddispute/backlist', {eventType:eventType, id:id}, function(form) {
	 		layer.open({
				type: 1,
				title: '黑名单信息',
				content: form,
				btn: ['拉黑', '取消'],
				shade: false,
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
					if(eventType == "detail"){
						return ;
					}
					form.on('submit()', function(data) {
						if(eventType=="back"){
							$.post("/feedbackanddispute/addblackList", data.field, function(data){
	 							var result;
	 							(data.result == "true") ? result="新增成功": result="新增失败";
	 	 						parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
	 	 							parent.layer.closeAll();
	 	 							layer.closeAll();
		 	 						location.reload();//新增后刷新页面
	 	 						});
	 						})
						}
						return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。									
					});
				},
			});   
		});
	})
}
//-------------------------------实体的编辑框弹出：新增，修改，查看-----------------------------End

//-------------------//
//点击黑名单按钮触发的事件
function layerback(data){

}

//-------------------//

</script>

<!-- 工具条 -->
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="black">黑名单</a>
</script>

<script type="text/html" id="tpl_carOwnerUser_realName">
	{{# if(d.carOwnerUser != null){ }}
		{{d.carOwnerUser.realName}}
	{{# } }}
</script>

<script type="text/html" id="tpl_sharingPlatform_parkingInfo_parkingName">
	{{# if(d.sharingPlatform != null){ }}
		{{# if(d.sharingPlatform.parkingInfo != null){ }}
			{{d.sharingPlatform.parkingInfo.parkingName}}
		{{# } }}
	{{# } }}
</script>

<script type="text/html" id="tpl_sharingPlatform_sharer_realName">
	{{# if(d.sharingPlatform != null){ }}
		{{# if(d.sharingPlatform.sharer != null){ }}
			{{d.sharingPlatform.sharer.realName}}
		{{# } }}
	{{# } }}
</script>
</body>
</html>