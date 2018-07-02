<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div style="margin: 15px;">
	<form class="layui-form">
		<input type="hidden" name="id" value="${sessionScope.sharingplatformapplication.id }">
		<div class="layui-form-item">
			<label class="layui-form-label">停车场id</label>
			<div class="layui-input-block">
				<input type="text"  readonly="readonly"  placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.sharingplatformapplication.parkingInfo.parkingName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">电话</label>
			<div class="layui-input-block">
				<input type="text" name="title" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.sharingplatformapplication.phone }">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-block">
				<input type="text" name="description" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.sharingplatformapplication.email }">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">申请理由</label>
			<div class="layui-input-block">
				<input type="text"  readonly="readonly" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.sharingplatformapplication.applicationReason}">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">提交时间</label>
			<div class="layui-input-block">
				<input  readonly="readonly"  placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" value="${sessionScope.sharingplatformapplication.creationDate}">
			</div>
		</div>
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>