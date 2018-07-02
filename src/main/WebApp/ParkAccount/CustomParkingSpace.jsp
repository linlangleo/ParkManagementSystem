<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<title>Tdrag</title>
  	<!-- 导入layui资源 -->
  	<link rel="stylesheet" href="${pageContext.request.contextPath }/ParkAccount/layui/css/layui.css"  media="all">
  	
  	<script src="${pageContext.request.contextPath }/ParkAccount/layui/layui.js" charset="utf-8"></script>
  	
  	<!-- 导入tdrag的拖拽资源 -->
	<link href="${pageContext.request.contextPath }/ParkAccount/tdrag/demo.css" type="text/css" rel="stylesheet">
  	
	<script type="text/javascript" src="${pageContext.request.contextPath }/ParkAccount/tdrag/JavaScript/jquery1.7.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/ParkAccount/tdrag/JavaScript/Tdrag.js"></script>
</head>
<body>
	<div class="layui-form" style="width:750px;position:absolute;">
   		<label class="layui-label" style="position:absolute;">车位编号:</label>
   			<input type="text" class="layui-input" style="width:80px;position:absolute;margin-left:70px;" id="psNumber"/>
		<label class="layui-label" style="position:absolute;margin-left:160px;">区域编号:</label>
			<input type="text" class="layui-input" style="width:80px;position:absolute;margin-left:230px;" id="psdNumber"/>
		<label class="layui-label" style="position:absolute;margin-left:320px;">车位状态:</label>
			<input type="text" class="layui-input" style="width:80px;position:absolute;margin-left:390px;" id="psStatus"/>
			
		<input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="删除车位" style="float:right; display:none;" id="deletePS"/>
		<input type="button" class="layui-btn layui-btn-radius layui-btn-warm" value="移除模型" style="float:right; display:none;" id="removePS"/>
	</div>
	<div class="layui-form" style="margin-left:900px;width:300px;z-index:999; ">
			<select name="newParkingSpace" lay-verify="" lay-filter="newParkingSpace" lay-search>
  				<option value=""  selected>请选择一个停车位添加到(当前楼)</option>
				<!--  循环 -->
				<c:forEach var="itemD" items="${pdList}">
  					<optgroup label="区域编号:${itemD.areaNumber } &nbsp;&nbsp;&nbsp;&nbsp;区域名称:${itemD.areaName }">
						<c:forEach var="itemS" items="${psList}">
							<c:if test="${itemS.parkingDictionary.id == itemD.id }">
  								<option value="${itemS.id }" item="${itemS.parkingSpaceNumber }">车位编号:${itemS.parkingSpaceNumber }</option>
  							</c:if>
						</c:forEach>
  					</optgroup>
				</c:forEach>
<!-- 				<optgroup label="未分区车位"> -->
<%-- 					<c:forEach var="itemS" items="${psList}"> --%>
<%-- 						<c:if test="${itemS.parkingDictionary == null }"> --%>
<%--   								<option value="${itemS.id }">车位编号:${itemS.parkingSpaceNumber }</option> --%>
<%--   						</c:if> --%>
<%-- 					</c:forEach> --%>
<!--   				</optgroup> -->
			</select>
			<input type="button" id="addNewParkingSpace" class="layui-btn layui-btn-radius layui-btn-normal" value="新增车位" style="position:absolute;top:1px;right:350px;"/>  
			<input type="button" id="saveParkingSpace" class="layui-btn layui-btn-radius" value="保存车位信息" style="position:absolute;top:1px;right:200px;"/>
	</div>
	<div id="contain">
		<div class="layui-tab">
  			<ul class="layui-tab-title">
    			<li class="layui-this">负一楼</li>
    			<li>一楼</li>
    			<li>二楼</li>
    			<li>三楼</li>
    			<li>四楼</li>
  			</ul>
  			<div class="layui-tab-content">
    			<!-- 负一楼内容 -->
    			<div class="layui-tab-item layui-show">
    					<div class="example">
      						<div class="boxList" style="width:100%;">
<!--         						<div class="one div8"> <div class="title">测试编码</div></div> -->
      						</div>
    					</div>
				</div>
    			<div class="layui-tab-item">内容2</div>
    			<div class="layui-tab-item">内容3</div>
    			<div class="layui-tab-item">内容4</div>
    			<div class="layui-tab-item">内容5</div>
  			</div>
		</div>
	</div>
</body>

<script>
// 注意：选项卡 依赖 element 模块，否则无法进行功能性操作
layui.use(['element', 'form', 'layer'], function(){
  var element = layui.element;
  var form = layui.form;
	var layer = layui.layer;
  
	//点击停车位模型后，所有的事件绑定，方法
	function modelMethod(){
		//拖拽效果
		 $(".div8").Tdrag({
		        scope:".boxList",
		        grid:[14,29],
		        handle:".title"
		    });
		//点击效果
		$(".one").click(function(event){
			var id=$(this).attr("value");
			
			//绑定信息
			$.post("/parkingspace/getparkingspacebyid", {id:$(this).attr("value")}, function(data){
				//信息的绑定
				$("#removePS").css("display","block");
				$("#deletePS").css("display","block");
				$("#psNumber").val(data.parkingSpaceNumber);
				$("#psdNumber").val(data.parkingDictionary.areaNumber);
				$("#psStatus").val(data.parkingSpaceStatus.parkingSpaceStatusName);
			})
			//添加选取框
			$(".tezml").removeClass("tezml");
			$(this).addClass("tezml");
			
	       	event.stopPropagation();//阻止冒泡
			$("#contain").on("click", function() { //对document绑定一个方法,点击其他地方取消模型选取
				//取消信息的绑定
				$("#removePS").css("display","none");
				$("#deletePS").css("display","none");
				$("#psNumber").val("");
				$("#psdNumber").val("");
				$("#psStatus").val("");

 				//取消选取框
				$(".tezml").removeClass("tezml");
				$(document).off("click");
			});

			//两个删除的绑定事件
			$("#removePS").off("click");
			$("#removePS").on("click", function(){
				$(".one[value="+id+"]").remove();//移除模型
				//下拉框可以重新选择
				$("select[name=newParkingSpace]").find("[value="+id+"]").removeAttr("disabled");//把id匹配上
	 			form.render("select");
	 			
	 	       	return;
			});
			$("#deletePS").off("click");
			$("#deletePS").on("click", function(){
				//加上ajax的删除操作
				
				
				$(".one[value="+id+"]").remove();//移除模型
				//下拉框可以重新选择
				$("select[name=newParkingSpace]").find("[value="+data.value+"]").removeAttr("disabled");
	 			form.render("select");

	 	       	return;	
			});
		})
	}
	
	//新增停车位
  form.on('select(newParkingSpace)', function(data){
// 	  console.log(data.elem); //得到select原始DOM对象
// 	  console.log(data.value); //得到被选中的值
// 	  console.log(data.othis); //得到美化后的DOM对象
 		if(data.value != null && data.value != ""){
 			$(data.elem).find("[value="+data.value+"]").attr("disabled", "disabled");
 			form.render("select");

			var newParkingSpace = $("<div class='one div8' value="+data.value+"><div class='title'>"+$(data.elem).find("[value="+data.value+"]").attr("item")+"</div></div>");
 			$('.boxList').append(newParkingSpace);
 			
 			modelMethod();
 		}
	});   

//新增停车位的方法
$("#addNewParkingSpace").on("click", function(){
	$.get('/parkingspace/layeroptions', {eventType:'add'}, function(form) {
 		layer.open({
			type: 1,
			title: '停车位信息',
			content: form,
			btn: ['确定', '取消'],
//				shade: false,
			scrollbar: false,
			offset: ['100px', '30%'],
			area: ['600px', '400px'],
			zIndex: 19950924,
			maxmin: true,
			yes: function(index) {
				//触发表单的提交事件
				$('form.layui-form').find('input[lay-filter=save]').click();
			},
			success: function(layero, index) {
				//弹出窗口成功后渲染表单
				var form = layui.form;
				form.render();
				
				//重写提交事件
				form.on('submit()', function(data) {
//						这里可以写ajax方法提交表单
 						$.post("/parkingspace/addparkingspace", data.field, function(data){
 							var result;
 							(data.result == "true") ? result="新增成功": result="新增失败";
 	 						parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
 	 							parent.layer.closeAll();
 	 							layer.closeAll();

								//放入新车位的模型
								var newParkingSpace = $("<div class='one div8' value="+data.newParkingSpace.id+"><div class='title'>"+data.newParkingSpace.parkingSpaceNumber+"</div></div>");
 								$('.boxList').append(newParkingSpace);
 	 							modelMethod();//车位模型的事件绑定
 	 							
 	 							//放入到Select里面
 	 							$("select[name=newParkingSpace]").append("<option disabled value="+data.newParkingSpace.id+" item="+data.newParkingSpace.parkingSpaceNumber+">车位编号:"+data.newParkingSpace.parkingSpaceNumber+"</option>");
 	 				 			form.render("select");
 	 						});
 						})
					return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。									
				});
			}
		});   
	})
})

//保存方法
$("#saveParkingSpace").on("click", function(){
	//取消选取框
	$(".tezml").removeClass("tezml");
	$(document).off("click");
	
	$.post('/customparkingspaceinfo/savecustomparkingspace', {parkingSpaceInfo:$("#contain").html()}, function(data) {
		if(data.result == "true"){
			layer.alert("保存成功", {skin: 'layui-layer-molv'}, function(){
					layer.closeAll();
			});
		}else if(data.result == "false"){
			layer.alert("保存失败", {skin: 'layui-layer-molv'}, function(){
				layer.closeAll();
			});
		}else{
			layer.alert("未知错误", {skin: 'layui-layer-molv'}, function(){
				layer.closeAll();
			});
		}
	})
})
	
//-----------------------------------------导入数据---------Star
	$.post('/customparkingspaceinfo/getinfobyid', {}, function(data) {
		$("#contain").html(data.parkingSpaceInfo);
		modelMethod();//车位模型的事件绑定
	})
//-----------------------------------------导入数据---------End

});

</script>

<script>
jQuery(function(){
	 //第八个例子的拖拽
    $(".div8").Tdrag({
        scope:".boxList",
        grid:[14,29],
//         pos:false,
//         dragChange:true,
//         changeMode:"point",//point & sort
        handle:".title"
    });
    //禁止
    $(".disable_cloose").on("click",function(){
        $.disable_cloose()
    });
    //开启
    $(".disable_open").on("click",function(){
        $.disable_open()
    });
})
</script>
</html>