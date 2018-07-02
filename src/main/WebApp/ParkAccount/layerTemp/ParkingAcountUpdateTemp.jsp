<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div style="margin: 15px;">
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">原密码:</label>
			<div class="layui-input-block">
				<input type="text" name="accountName" placeholder="请输入" autocomplete="off" class="layui-input" value="">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">新密码:</label>
			<div class="layui-input-block">
			<input type="text" name="newPassword" placeholder="请输入" autocomplete="off" class="layui-input" value="">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">新密码:</label>
			<div class="layui-input-block">
				<input type="text" name="newPassword1" placeholder="请输入" autocomplete="off" class="layui-input" value="">
			</div>
		</div>
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>