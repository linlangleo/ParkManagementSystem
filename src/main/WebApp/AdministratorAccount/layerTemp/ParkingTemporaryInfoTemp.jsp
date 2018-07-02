<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div style="margin: 15px;">
	<form class="layui-form">
		<input type="hidden" name="id" value="${sessionScope.TemporaryInfo.id }">
		<input type="hidden" name="parkingInfo.id" value="${sessionScope.TemporaryInfo.parkingInfo.id }">
		<div class="layui-form-item">
			<label class="layui-form-label">停车场名称:</label>
			<div class="layui-input-block">
				<input type="text" name="parkingName" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.TemporaryInfo.parkingName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">停车场位置:</label>
			<div class="layui-input-block">
			<input type="text" name="parkingAddress" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.TemporaryInfo.parkingAddress }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">收费金额(/h):</label>
			<div class="layui-input-block">
			<input type="text" name="charge" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.TemporaryInfo.charge }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">车位数量</label>
			<div class="layui-input-block">
				<input type="text" name="parkingSpaceCount"  autocomplete="off" class="layui-input" value="${sessionScope.TemporaryInfo.parkingSpaceCount }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">区/县:</label>
			<div class="layui-input-block">
				<input type="text" name="districtOrCounty。districtOrCounty" placeholder=""   autocomplete="off" class="layui-input"  
				<c:if test="${not empty session.TemporaryInfo.districtOrCounty}">
					value="${sessionScope.TemporaryInfo.districtOrCounty。districtOrCounty }"
				</c:if>
				>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">街道:</label>
			<div class="layui-input-block">
				<input type="text" name="street。streetName"    autocomplete="off" class="layui-input" 
				<c:if test="${not empty session.TemporaryInfo.street}">
					value="${sessionScope.TemporaryInfo.street。streetName }"
				</c:if>
				>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">审批状态</label>
			<div class="layui-input-block">
				<input type="text" name="examineStatusInfo.examineName"  autocomplete="off" class="layui-input" value="${sessionScope.TemporaryInfo.examineStatusInfo.examineName }">
			</div>
		</div>
	<%-- 	<div class="layui-form-item">
			<label class="layui-form-label">共享平台开/关:</label>
			<div class="layui-input-block">
				<input type="text" name="ifOpenSharingPlatform"  readonly=true  autocomplete="off" class="layui-input" value="${sessionScope.parkinfo.ifOpenSharingPlatform }">
			</div>
		</div> --%>
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>