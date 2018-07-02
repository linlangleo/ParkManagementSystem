<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<div style="margin: 15px;">
	<form class="layui-form">
		<input type="hidden" name="id" value="${sessionScope.transaction.id }">
		<div class="layui-form-item">
			<label class="layui-form-label">停车场名称</label>
			<div class="layui-input-block">
				<input type="text" name="parkingInfo.parkingName" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.transaction.parkingInfo.parkingName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">停车位编号</label>
			<div class="layui-input-block">
				<input type="text" name="parkingSpace.parkingSpaceNumber" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.transaction.parkingSpace.parkingSpaceNumber }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">车主名称</label>
			<div class="layui-input-block">
				<input type="text" name="carOwnerUser.userName" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.transaction.carOwnerUser.userName }">
			</div>
		</div>
<%-- 		<div class="layui-form-item">
			<label class="layui-form-label">交易类型</label>
			<div class="layui-input-block">
				<select name="transactionType.id">
					<c:forEach var="item" items="${sessionScope.ttList }">
						<option value="${item.id }" <c:if test="${item.id == sessionScope.transaction.transactionType.id }">selected=true</c:if> >${item.typeName }</option>
					</c:forEach>
				</select>
			</div>
		</div> --%>
				<div class="layui-form-item">
			<label class="layui-form-label">开始时间</label>
			<div class="layui-input-block">
				<input type="text" name="startDate" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.transaction.startDate }">
			</div>
		</div>
				<div class="layui-form-item">
			<label class="layui-form-label">结束时间</label>
			<div class="layui-input-block">
				<input type="text" name="endDate" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.transaction.endDate }">
			</div>
		</div>
				<div class="layui-form-item">
			<label class="layui-form-label">交易金额</label>
			<div class="layui-input-block">
				<input type="text" name="transactionAmount" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.transaction.transactionAmount }">
			</div>
		</div>
	<%-- 	<div class="layui-form-item">
			<label class="layui-form-label">交易状态</label>
			<div class="layui-input-block">
				<select name="transactionStatusList.id">
					<c:forEach var="item" items="${sessionScope.tsList }">
						<option value="${item.id }" <c:if test="${item.id == sessionScope.transaction.transactionStatusList.id }">selected=true</c:if> >${item.transactionStatusName }</option>
					</c:forEach>
				</select>
			</div>
		</div> --%>
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>