<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div style="margin: 15px;">
	<form class="layui-form">
		<input type="hidden" name="id" value="${sessionScope.boundOfPlateNumber.id }">
		
		<input type="hidden" name="plateNumberid" value="${sessionScope.boundOfPlateNumber.plateNumber.id }">
		<input type="hidden" name="carOwnerUserid" value="${sessionScope.boundOfPlateNumber.carOwnerUser.id }">
			<div class="layui-form-item">
			<label class="layui-form-label">账号</label>
			<div class="layui-input-block">
				<input type="text" name="userName" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.boundOfPlateNumber.carOwnerUser.userName }">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-block">
				<input type="text" name="password" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.boundOfPlateNumber.carOwnerUser.password }">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">车主</label>
			<div class="layui-input-block">
				<input type="text" name="realName" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.boundOfPlateNumber.carOwnerUser.realName }">
			</div>
		</div>
		
			<div class="layui-form-item">
			<label class="layui-form-label">绑定车牌</label>
			<div class="layui-input-block">
				<input type="text" name="plateNumber" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.boundOfPlateNumber.plateNumber.plateNumber }">
			</div>
		</div>
		
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>