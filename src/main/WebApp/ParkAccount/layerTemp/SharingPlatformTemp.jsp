<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<div style="margin: 15px;">
	<form class="layui-form">
		<input type="hidden" name="id" value="${sessionScope.sharingPlatform.id }">
		<div class="layui-form-item">
			<label class="layui-form-label">停车场名称</label>
			<div class="layui-input-block">
				<input type="text" readonly=true name="parkingId" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.sharingPlatform.parkingInfo.parkingName }">
			</div>
		</div>
		<!-- 修改后 -->
		<div class="layui-form-item">
			<label class="layui-form-label">停车位编号</label>
			<div class="layui-input-block">
				<input type="text" readonly=true name="parkingSpaceId" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.sharingPlatform.parkingSpace.parkingSpaceNumber }">
				<select name="parkingSpace.id">
					<c:forEach var="item" items="${sessionScope.psList }">
						<option value="${item.id }" <c:if test="${item.id == sessionScope.sharingPlatform.parkingSpace.id }">selected=true</c:if> >${item.parkingSpaceNumber }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">车位共享状态</label>
			<div class="layui-input-block">
				<select name="sharingStatus.id">
					<c:forEach var="item" items="${sessionScope.ssList }">
						<option value="${item.id }" <c:if test="${item.id == sessionScope.sharingPlatform.sharingStatus.id}">selected=true</c:if> >${item.statuSname }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">共享者车主名称</label>
			<div class="layui-input-block">
				<input type="text" readonly=true name="sharerId" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.sharingPlatform.sharer.nickName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">使用者车主名称</label>
			<div class="layui-input-block">
				<input type="text" readonly=true name="userId" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.sharingPlatform.user.userName  }">
			</div>
		</div>
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>