<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div style="margin: 15px;">
	<form class="layui-form">
		<input type="hidden" name="id" value="${sessionScope.parkingInfo.id }">
		
		<div class="layui-form-item">
			<label class="layui-form-label">停车场名称</label>
			<div class="layui-input-block">
				<input type="text" name="parkingName" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingInfo.parkingName}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">停车场位置</label>
			<div class="layui-input-block">
				<input type="text" name="parkingAddress" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingInfo.parkingAddress}">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">收费金额</label>
			<div class="layui-input-block">
				<input type="text" name="charge" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingInfo.charge}">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">车位数量</label>
			<div class="layui-input-block">
				<input name="parkingSpaceCount" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingInfo.parkingSpaceCount}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">区县</label>
			<div class="layui-input-block">
				<input type="text" readonly="readonly" name="districtOrCounty.districtOrCounty" placeholder="创建后选择" autocomplete="off" class="layui-input" value="${sessionScope.parkingInfo.districtOrCounty.districtOrCounty}">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">街道</label>
			<div class="layui-input-block">
				<input type="text" readonly="readonly" name="street.streetName" placeholder="创建后选择" autocomplete="off" class="layui-input" value="${sessionScope.parkingInfo.street.streetName}">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">是否开启共享平台</label>
			<div class="layui-input-block">
				<input type="text" name="ifOpenSharingPlatform" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingInfo.ifOpenSharingPlatform}">
			</div>
		</div>
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>