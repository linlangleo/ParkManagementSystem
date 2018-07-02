<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/ParkAccount/layui/css/layui.css"  media="all">
</head>
<body style="margin-left:20px; margin-top:20px;"> 

  <div class="admin-main">
			<blockquote class="layui-elem-quote">
             搜索车位：
           <div class="layui-inline">
            <input class="layui-input" name="id" id="demoReload" autocomplete="off">
           </div>
            <button class="layui-btn" data-type="reload">搜索</button>
            
			</blockquote>
		
		</div>          
			<fieldset class="layui-elem-field">
				<legend>数据列表</legend>
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table">
						<thead>
							<tr>
								<th>选择</th>
								<th>停车位编号</th>
								<th>区域编号</th>
								<th>停车位状态</th>
								<th>所有者id</th>
								<th>操作</th>
							</tr>
							
						</thead>
						<tbody id="content">
						</tbody>
					</table>
				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="paged" class="page">
				</div>
			</div>
		
		<!--模板-->
		<script type="text/html" id="tpl">
			{{# layui.each(d.list, function(index, item){ }}
			<tr>
				<td><input type="checkbox" lay-skin="primary"></td>
				<td>{{ item.name }}</td>
				<td>{{ item.age }}</td>
				<td>{{ item.createtime }}</td>
                <td>{{ item.createtime }}</td>
				<td>
					<a href="javascript:;" data-name="{{ item.name }}" data-opt="edit" class="layui-btn layui-btn-mini">编辑</a>
					<a href="javascript:;" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
				</td>
			</tr>
			{{# }); }}
		</script>
          
<script src="${pageContext.request.contextPath }/ParkAccount/plugins/layui/layui.js" charset="utf-8"></script>
<script>

layui.config({
	base: 'js/'
});

layui.use(['paging', 'form'], function() {
	var $ = layui.jquery,
		paging = layui.paging(),
		layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
		layer = layui.layer, //获取当前窗口的layer对象
		form = layui.form();

    paging.init({
        openWait: true,
        url: 'datas/laytpl_laypage_data.json?v=' + new Date().getTime(), //地址
		elem: '#content', //内容容器
		params: { //发送到服务端的参数
		},
		type: 'GET',
		tempElem: '#tpl', //模块容器
		pageConfig: { //分页参数配置
			elem: '#paged', //分页容器
			pageSize: 3 //分页大小
		},
		success: function() { //渲染成功的回调
			//alert('渲染成功');
		},
		fail: function(msg) { //获取数据失败的回调
			//alert('获取数据失败')
		},
		complate: function() { //完成的回调
			//alert('处理完成');
			//重新渲染复选框
			form.render('checkbox');
			form.on('checkbox(allselector)', function(data) {
				var elem = data.elem;

				$('#content').children('tr').each(function() {
					var $that = $(this);
					//全选或反选
					$that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
					form.render('checkbox');
				});
			});

			//绑定所有编辑按钮事件						
			$('#content').children('tr').each(function() {
				var $that = $(this);
				$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
					layer.msg($(this).data('name'));
				});

			});

		},
	});
	//获取所有选择的列
	$('#getSelected').on('click', function() {
		var names = '';
		$('#content').children('tr').each(function() {
			var $that = $(this);
			var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
			if($cbx) {
				var n = $that.children('td:last-child').children('a[data-opt=edit]').data('name');
				names += n + ',';
			}
		});
		layer.msg('你选择的名称有：' + names);
	});

	$('#search').on('click', function() {
		parent.layer.alert('你点击了搜索按钮')
	});


}); 




</script>



</body>
</html>