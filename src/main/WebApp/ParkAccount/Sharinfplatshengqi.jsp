<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/ParkAccount/layui/css/layui.css"
	media="all">
</head>
<body>

	<%-- ${sessionScope.logUser.sharingPlatformAuthorizationList.size()}
 
${sessionScope.logUser.sharingPlatformAuthorizationList.get(0).ifAvailable}

${sessionScope.logUser.sharingPlatformAuthorizationList.get(0).ifClosed}  --%>
	<c:if test="${sessionScope.logUser.sharingPlatformAuthorizationList.size() < 1 }">
		<form class="layui-form" action="">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>功能栏</legend>
				<div class="layui-field-box">
					<blockquote class="layui-elem-quote">
						<i class="fa fa-shopping-cart" aria-hidden="true"></i>您还未开通，请先申请。
					</blockquote>
				</div>
			</fieldset>
			<div class="layui-form-item">
				<label class="layui-form-label">电话</label>
				<div class="layui-input-inline">
					<input type="text" name="phone" required lay-verify="required"
						placeholder="请输入电话" autocomplete="off" class="layui-input"
						value="${sessionScope.sharingplatformapplication.phone }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-inline">
					<input type="text" name="email" required lay-verify="required"
						placeholder="请输入邮箱" autocomplete="off" class="layui-input"
						value="${sessionScope.sharingplatformapplication.email }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">申请理由</label>
				<div class="layui-input-inline">
					<input type="text" name="applicationReason" size="20" required
						lay-verify="required" placeholder="请输入申请理由" autocomplete="off"
						class="layui-input"
						value="${sessionScope.sharingplatformapplication.applicationReason }">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				</div>
			</div>
		</form>
		<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
		<script src="${pageContext.request.contextPath }/ParkAccount/layui/layui.js" charset="utf-8"></script>
		<script>
		
		layui.use('form', function(){
			var $ = layui.$;
			  var form = layui.form;
			//重写提交事件
			form.on('submit(formDemo)', function(data) {
						$.post("/sharingplatformapplication/addsharingplatformapplication", data.field, function(data){
							var result;
							(data.result == "true") ? result="新增成功": result="新增失败";
							parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
								parent.layer.closeAll();
								layer.closeAll();
							});
						})
			});
		});
		</script>
	</c:if>

	<c:if test="${sessionScope.logUser.sharingPlatformAuthorizationList.size() >= 1 && sessionScope.logUser.sharingPlatformAuthorizationList.get(0).ifAvailable =='true'} ">
		<fieldset class="layui-elem-field layui-field-title">
			<legend>功能栏</legend>
			<div class="layui-field-box">
				<blockquote class="layui-elem-quote">
					<i class="fa fa-shopping-cart" aria-hidden="true"></i>有权限，请开通。
				</blockquote>
			</div>
		</fieldset>
		<button>开通</button>
	</c:if>


	<c:if test="${sessionScope.logUser.sharingPlatformAuthorizationList.size()>= 1 && sessionScope.logUser.sharingPlatformAuthorizationList.get(0).ifAvailable =='false' && sessionScope.logUser.sharingPlatformAuthorizationList.get(0).ifClosed =='true'}">
		<form class="layui-form"
			action="">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>功能栏</legend>
				<div class="layui-field-box">
					<blockquote class="layui-elem-quote">
						<i class="fa fa-shopping-cart" aria-hidden="true"></i>您已退出，请重新申请。
					</blockquote>
				</div>
			</fieldset>

			<div class="layui-form-item">
				<label class="layui-form-label">电话</label>
				<div class="layui-input-inline">
					<input type="text" name="phone" required lay-verify="required"
						placeholder="请输入电话" autocomplete="off" class="layui-input"
						value="${sessionScope.sharingplatformapplication.phone }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-inline">
					<input type="text" name="email" required lay-verify="required"
						placeholder="请输入邮箱" autocomplete="off" class="layui-input"
						value="${sessionScope.sharingplatformapplication.email }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">申请理由</label>
				<div class="layui-input-inline">
					<input type="text" name="applicationReason" size="20" required
						lay-verify="required" placeholder="请输入申请理由" autocomplete="off"
						class="layui-input"
						value="${sessionScope.sharingplatformapplication.applicationReason }">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				</div>
			</div>
		</form>
		<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
		<script src="${pageContext.request.contextPath }/ParkAccount/layui/layui.js" charset="utf-8"></script>
		<script>
		
		layui.use('form', function(){
			var $ = layui.$;
			  var form = layui.form;
			//重写提交事件
			form.on('submit(formDemo)', function(data) {
						$.post("/sharingplatformapplication/addsharingplatformapplication", data.field, function(data){
							var result;
							(data.result == "true") ? result="新增成功": result="新增失败";
							parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
								parent.layer.closeAll();
								layer.closeAll();
							});
						})
			});
		});
		</script>
	</c:if>
	<c:if test="${sessionScope.logUser.sharingPlatformAuthorizationList.size()>= 1 && sessionScope.logUser.sharingPlatformAuthorizationList.get(0).ifAvailable =='false' && sessionScope.logUser.sharingPlatformAuthorizationList.get(0).ifClosed =='false'}">
		<fieldset class="layui-elem-field layui-field-title">
			<legend>功能栏</legend>
			<div class="layui-field-box">
				<blockquote class="layui-elem-quote">
					<i class="fa fa-shopping-cart" aria-hidden="true"></i>平台管理
				</blockquote>
			</div>
		</fieldset>
		<iframe height="800" width="100%"  src="Sharinfplatform.jsp"></iframe>
	</c:if>
	
</body>
</html>