<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div style="margin: 15px;">
	<form class="layui-form">
		<input type="hidden" name="id" value="${sessionScope.parkingSpace.id }">
		<div class="layui-form-item">
			<label class="layui-form-label">停车位编号</label>
			<div class="layui-input-block">
				<input type="text" name="parkingSpaceNumber" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.parkingSpace.parkingSpaceNumber }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">区域编号名称</label>
			<div class="layui-input-block">
				<select name="parkingDictionary.id">
					<c:forEach var="item" items="${sessionScope.pdList }">
						<option value="${item.id }" <c:if test="${item.id == sessionScope.parkingSpace.parkingDictionary.id }">selected=true</c:if> >${item.areaName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">停车位状态名称</label>
			<div class="layui-input-block">
				<select name="parkingSpaceStatus.id">
					<c:forEach var="item" items="${sessionScope.pssList }">
						<option value="${item.id }" <c:if test="${item.id == sessionScope.parkingSpace.parkingSpaceStatus.id }">selected=true</c:if> >${item.parkingSpaceStatusName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">所有者用户名</label>
			<div class="layui-input-block">
				<input type="text" name="parkingSpaceOwnerId" placeholder="默认本停车场" readonly=true autocomplete="off" class="layui-input" value="${sessionScope.parkingSpace.carOwnerUser.userName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">停车场名称</label>
			<div class="layui-input-block">
				<input type="text" name="parkingId" placeholder="默认本停车场" readonly=true  autocomplete="off" class="layui-input" value="${sessionScope.parkingSpace.parkingInfo.parkingName }">
			</div>
		</div>
<!-- 		<div class="layui-form-item"> -->
<!-- 			<label class="layui-form-label">密码框</label> -->
<!-- 			<div class="layui-input-inline"> -->
<!-- 				<input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input"> -->
<!-- 			</div> -->
<!-- 			<div class="layui-form-mid layui-word-aux">辅助文字</div> -->
<!-- 		</div> -->
<!-- 		<div class="layui-form-item"> -->
<!-- 			<label class="layui-form-label">选择框</label> -->
<!-- 			<div class="layui-input-block"> -->
<!-- 				<select name="city" lay-verify="required"> -->
<!-- 					<option value=""></option> -->
<!-- 					<option value="0">北京</option> -->
<!-- 					<option value="1">上海</option> -->
<!-- 					<option value="2">广州</option> -->
<!-- 					<option value="3">深圳</option> -->
<!-- 					<option value="4">杭州</option> -->
<!-- 				</select> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="layui-form-item"> -->
<!-- 			<label class="layui-form-label">复选框</label> -->
<!-- 			<div class="layui-input-block"> -->
<!-- 				<input type="checkbox" name="like[write]" title="写作"> -->
<!-- 				<input type="checkbox" name="like[read]" title="阅读" checked> -->
<!-- 				<input type="checkbox" name="like[dai]" title="发呆"> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="layui-form-item"> -->
<!-- 			<label class="layui-form-label">开关</label> -->
<!-- 			<div class="layui-input-block"> -->
<!-- 				<input type="checkbox" name="switch" lay-skin="switch"> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="layui-form-item"> -->
<!-- 			<label class="layui-form-label">单选框</label> -->
<!-- 			<div class="layui-input-block"> -->
<!-- 				<input type="radio" name="sex" value="男" title="男"> -->
<!-- 				<input type="radio" name="sex" value="女" title="女" checked> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="layui-form-item layui-form-text"> -->
<!-- 			<label class="layui-form-label">文本域</label> -->
<!-- 			<div class="layui-input-block"> -->
<!-- 				<textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>