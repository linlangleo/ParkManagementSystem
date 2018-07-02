<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div style="margin: 15px;">
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">账号名:</label>
			<div class="layui-input-block">
				<input type="text" name="accountName" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkAcount.accountName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">账号类型</label>
			<div class="layui-input-block">
			<input type="text" name="accountType" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkAcount.accountType.typeName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">创建时间</label>
			<div class="layui-input-block">
				<input type="text" name="registrationDate" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkAcount.registrationDate }">
			</div>
		</div>
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>