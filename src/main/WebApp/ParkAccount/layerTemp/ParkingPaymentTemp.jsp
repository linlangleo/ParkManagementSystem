<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<div style="margin: 15px;">
	<form class="layui-form">
		
		<input type="hidden" name="id" value="${sessionScope.parkingPayment.id }">

		<div class="layui-form-item">
			<label class="layui-form-label">订单编号</label>
			<div class="layui-input-block">
				<input type="text" name="orderList.id" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingPayment.orderList.id }">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">停车总时长</label>
			<div class="layui-input-block">
				<input type="text" name="totalTime" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingPayment.totalTime }">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">停车总费用</label>
			<div class="layui-input-block">
				<input type="text" name="totalCost" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingPayment.totalCost }">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">停车位状态名称</label>
			<div class="layui-input-block">
				<select name="stopStatus.id">
					<c:forEach var="item" items="${sessionScope.stopList }">
						<option value="${item.id }" <c:if test="${item.id == sessionScope.parkingPayment.stopStatus.id }">selected='true'</c:if> >${item.statusName} </option>
					</c:forEach>
				</select>
				<%-- <input type="text" value="${sessionScope.parkingPayment.stopStatus.id}"/> --%>
			</div>
		</div>
		
		
		<div class="layui-form-item">
			<label class="layui-form-label">进场时间</label>
			<div class="layui-input-block">
				<input type="text" name="startDate" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingPayment.startDate}">
			</div>
		</div>
				<div class="layui-form-item">
			<label class="layui-form-label">出场时间</label>
			<div class="layui-input-block">
				<input type="text" name="endDate" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingPayment.endDate}">
			</div>
		</div>
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>