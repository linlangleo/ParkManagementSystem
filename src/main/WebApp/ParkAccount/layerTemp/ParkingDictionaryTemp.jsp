<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<div style="margin: 15px;">
	<form class="layui-form">
		<input type="hidden" name="id" value="${sessionScope.parkingDictionary.id }">
		<div class="layui-form-item">
			<label class="layui-form-label">区域编号</label>
			<div class="layui-input-block">
				<input type="text" name="areaNumber" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingDictionary.areaNumber }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">区域名</label>
			<div class="layui-input-block">
				<input type="text" name="areaName" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingDictionary.areaName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">停车场编号</label>
			<div class="layui-input-block">
				<input type="text" name="parkingId" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingDictionary.parkingInfo.parkingName }">
			</div>
		</div>
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>