<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>操作记录</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<script
	src="${pageContext.request.contextPath }/ParkAccount/layui/layui.js"
	charset="utf-8"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/ParkAccount/layui/css/layui.css"
	media="all">
</head>
<body>
<table class="layui-hide" id="table" lay-filter="user"></table>
<div id="psPage"></div> 


<script>
function pageStart(currentPageNo, pageSize){
	layui.use(['table','laypage'], function(){
		var $ = layui.$;
	  	var table = layui.table,
	 	laypage = layui.laypage;

	//方法级渲染表格
	  table.render({
	    elem: '#table'
	    ,height: 'full-300'
	    ,url: '/operationrecord/getoperationInfoPage'
 	    ,where:{currentPageNo:currentPageNo, pageSize:pageSize, title: $('#demoReload').val()} //传参*/   
	    ,method: 'post' //如果无需自定义HTTP类型，可不加该参数
	    ,id: 'testReload'
	    ,even: true
	    ,cols: [[
	        {field:'id', title: 'ID', width:80, sort: true, fixed: 'left'}
/* 	        ,{field:'parkingAccount', templet:'#tpl_parkingAccount_accountName', title: '操作人'} */
	        ,{field:'operationDetail', title: '操作内容'}
	        ,{field:'operationDate', title: '操作时间', sort: true}
	      ]]
	  	,done: function(data){
	      //如果是异步请求数据方式，data即为你接口返回的信息。  
	      //如果是直接赋值的方式，data即为：{data: [], count: 99} data为当前页数据、count为数据总长度  
	      laypage.render({  
	          elem:'psPage'  
	          ,count:data.count  
	          ,curr:data.currentPageNo
	          ,groups:5
			  ,limits:[5, 10, 25, 50]
	          ,limit:data.pageSize
	          ,layout: ['prev', 'page', 'next', 'skip','count','limit']  
	          ,jump:function (obj,first) {
	              if(!first){  
	            	  //obj包含了当前分页的所有参数，比如：
//	             	  console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
//	             	  console.log(obj.limit); //得到每页显示的条数
	                  currentPageNo = obj.curr;
	                  pageSize = obj.limit;  
	                  pageStart(currentPageNo, pageSize);
	              }  
	          }  
	      })  
	  }  
	  });
	  
	  var $ = layui.$, active = {
	    reload: function(){
	      var demoReload = $('#demoReload');
	      //执行重载
	      table.reload('testReload', {
//	         page: {
//	           	curr: 1 //重新从第 1 页开始
//	         },
	        where: {
	        	title: demoReload.val()
	        }
	      });
	    }
	  };
	  $('.demoTable .layui-btn').on('click', function(){
	    var type = $(this).data('type');
	    active[type] ? active[type].call(this) : '';
	  });
	});
}
pageStart();
</script>

 <script type="text/html" id="tpl_parkingAccount_accountName">
	{{# if(d.parkingAccount != null){ }}
		{{d.parkingAccount.accountName}}
	{{# } }}
</script>
</body>
</html>