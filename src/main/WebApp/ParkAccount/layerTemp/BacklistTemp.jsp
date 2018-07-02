<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ParkAccount/layui/css/layui.css" media="all">
<script src="${pageContext.request.contextPath }/ParkAccount/layui/layui.js" charset="utf-8"></script>

<div style="margin: 15px;">
	<form class="layui-form" lay-filter="BlackList">
	<input type="hidden" name="id" value="${sessionScope.back.id}">
	
		<div class="layui-form-item">
			<label class="layui-form-label">反馈车主：</label>
		</div>
		<div class="layui-input-block">
			<input type="checkbox" id="fk" name="realName" value="${sessionScope.back.carOwnerUser.id}" lay-filter="fk" title="${sessionScope.back.carOwnerUser.realName}">
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">共享车主 ：</label>
		</div>
		<div class="layui-input-block">
			<input type="checkbox" id="gx" name="SrealName" value="${sessionScope.back.sharingPlatform.sharer.id}" lay-filter="gx" title="${sessionScope.back.sharingPlatform.sharer.realName}">
		</div>
		<input id="save" lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>
<script>
layui.use('form', function(){
  var form = layui.form;
  //监听提交
  form.on('submit(save)', function(data){
	  var rName = document.getElementById("fk").value;
	  var sName = document.getElementById("gx").value;
	  
	  if(rName!=null&&!=0){
		  
	  }
	  
	  return false;
  });
});
</script>