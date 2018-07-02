<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div style="margin: 15px;">
	<form class="layui-form">
		<input type="hidden" name="id" value="${sessionScope.parkinfo.id }">
		<div class="layui-form-item">
			<label class="layui-form-label">停车场名称:</label>
			<div class="layui-input-block">
				<input type="text" name="parkingName" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkinfo.parkingName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">停车场位置:</label>
			<div class="layui-input-block">
			<input type="text" name="parkingAddress" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkinfo.parkingAddress }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">收费金额(/h):</label>
			<div class="layui-input-block">
			<input type="text" name="charge" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkinfo.charge }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">车位数量</label>
			<div class="layui-input-block">
				<input type="text" name="parkingSpaceCount"  autocomplete="off" class="layui-input" value="${sessionScope.parkinfo.parkingSpaceCount }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">区/县:</label>
			<div class="layui-input-block">
				<select name="districtOrCounty.id">
				<option value="${sessionScope.parkinfo.districtOrCounty.id }">${sessionScope.parkinfo.districtOrCounty.districtOrCountyName }</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">街道:</label>
			<div class="layui-input-block">
				<select name="street.id">
				<option value="${sessionScope.parkinfo.street.id }">${sessionScope.parkinfo.street.streetName}</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">共享平台开/关:</label>
			<div class="layui-input-block">
				<input type="text" name="ifOpenSharingPlatform"  readonly=true  autocomplete="off" class="layui-input" value="${sessionScope.parkinfo.ifOpenSharingPlatform }">
			</div>
		</div>
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>