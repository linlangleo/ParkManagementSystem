<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div style="margin: 15px;">
	<form class="layui-form">
		<input type="hidden" name="id" value="${sessionScope.feedbackAndDispute.id }">
		<div class="layui-form-item">
			<label class="layui-form-label">反馈车主名称</label>
			<div class="layui-input-block">
				<input type="text"  readonly="readonly"  placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.feedbackAndDispute.carOwnerUser.realName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">标题</label>
			<div class="layui-input-block">
				<input type="text" name="title" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.feedbackAndDispute.title }">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">描述</label>
			<div class="layui-input-block">
				<input type="text" name="description" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.feedbackAndDispute.description }">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">提交时间</label>
			<div class="layui-input-block">
				<input  readonly="readonly"  placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" value="${sessionScope.feedbackAndDispute.submitDate }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">停车场平台</label>
			<div class="layui-input-block">
				<input type="text"  readonly="readonly" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.feedbackAndDispute.sharingPlatform.parkingInfo.parkingName}">
			</div>
		</div>
		<input lay-filter="save" lay-submit style="display: none;" ></input>
	</form>
</div>