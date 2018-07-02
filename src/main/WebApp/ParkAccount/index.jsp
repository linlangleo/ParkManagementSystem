<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="utf-8" >
		<title>后台管理模板</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">

		<link rel="stylesheet" href="${pageContext.request.contextPath }/ParkAccount/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/ParkAccount/css/global.css" media="all">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/ParkAccount/plugins/font-awesome/css/font-awesome.min.css">

	</head>

	<body>
		<div class="layui-layout layui-layout-admin" style="border-bottom: solid 5px #1aa094;background-color: #393D49;">
			<div class="layui-header header header-demo">
				<div class="layui-main">
					<div class="admin-login-box">
						<a class="logo" style="left: 0;" href="http://beginner.zhengjinfan.cn/demo/beginner_admin/">
							<span style="font-size: 22px;">${sessionScope.logUser.accountType.typeName }账号</span>
						</a>
						<div class="admin-side-toggle">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</div>
						<div class="admin-side-full">
							<i class="fa fa-life-bouy" aria-hidden="true"></i>
						</div>
					</div>
					<ul class="layui-nav admin-header-item">
		<!-- 				<li class="layui-nav-item">
							<a href="javascript:;">清除缓存</a>
						</li>
                        <li class="layui-nav-item" id="pay">
                            <a href="javascript:;">捐赠我</a>
                        </li>
						<li class="layui-nav-item">
							<a href="javascript:;">浏览网站</a>
						</li>
						<li class="layui-nav-item" id="video1">
							<a href="javascript:;">视频</a>
						</li> -->
						<li class="layui-nav-item">
							<a href="javascript:;" class="admin-header-user">
								<img src="${pageContext.request.contextPath }/ParkAccount/images/0.jpg" />
								<span>${sessionScope.logUser.accountName }</span>
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="javascript:;"  id="acountinfo"><i class="fa fa-user-circle" aria-hidden="true"></i> 个人信息</a>
								</dd>
<!-- 								<dd>
									<a href="javascript:;"><i class="fa fa-gear" aria-hidden="true"></i> 设置</a>
								</dd>
								<dd id="lock">
									<a href="javascript:;">
										<i class="fa fa-lock" aria-hidden="true" style="padding-right: 3px;padding-left: 1px;"></i> 锁屏 (Alt+L)
									</a>
								</dd> -->
								<dd>
									<a href="${pageContext.request.contextPath }/login/login.jsp"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
								</dd>
							</dl>
						</li>
					</ul>
					<ul class="layui-nav admin-header-item-mobile">
						<li class="layui-nav-item">
							<a href="${pageContext.request.contextPath }/login/login.jsp"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="layui-side layui-bg-black" id="admin-side">
				<div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
			</div>
			
			<div class="layui-body" style="bottom: 0;border-left: solid 2px #1AA094;" id="admin-body">
				<div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
					<ul class="layui-tab-title">
						<li class="layui-this">
							<i class="fa fa-dashboard" aria-hidden="true"></i>
							<cite>账号信息</cite>
						</li>
					</ul>
					<div class="layui-tab-content" style="min-height: 150px; padding: 5px 0 0 0;">
						<div class="layui-tab-item layui-show">
							<iframe src="${pageContext.request.contextPath }/ParkAccount/main.jsp"></iframe>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-footer footer footer-demo" id="admin-footer">
				<div class="layui-main">
					<p>2016 &copy;
						<a href="http://m.zhengjinfan.cn/">m.zhengjinfan.cn/</a> LGPL license
					</p>
				</div>
			</div>
			<div class="site-tree-mobile layui-hide">
				<i class="layui-icon">&#xe602;</i>
			</div>
			<div class="site-mobile-shade"></div>
			
			<!--锁屏模板 start-->
			<script type="text/template" id="lock-temp">
				<div class="admin-header-lock" id="lock-box">
					<div class="admin-header-lock-img">
						<img src="images/0.jpg"/>
					</div>
					<div class="admin-header-lock-name" id="lockUserName">beginner</div>
					<input type="text" class="admin-header-lock-input" value="输入密码解锁.." name="lockPwd" id="lockPwd" />
					<button class="layui-btn layui-btn-small" id="unlock">解锁</button>
				</div>
			</script>
			<!--锁屏模板 end -->
			
			<script type="text/javascript" src="${pageContext.request.contextPath }/ParkAccount/plugins/layui/layui.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath }/ParkAccount/datas/nav.js"></script>
			<script src="${pageContext.request.contextPath }/ParkAccount/js/index.js"></script>
<%-- 			<script src="${pageContext.request.contextPath }/ParkAccount/js/navbar.js"></script> --%>
			<script>
				layui.use(['layer'], function() {
					var $ = layui.jquery,
						layer = layui.layer;

					$('#video1').on('click', function() {
						layer.open({
							title: 'YouTube',
							maxmin: true,
							type: 2,
							content: 'video.html',
							area: ['800px', '500px']
						});
                    });
                    $('#pay').on('click', function () {
                        layer.open({
                            title: false,
                            type: 1,
                            content: '<img src="images/xx.png" />',
                            area: ['500px', '250px'],
                            shadeClose: true
                        });
                    });
                    
                    $('#acountinfo').on('click',function(){
                    	$.get('/ParkingAccount/Accountinformation', function(from) {
                	 		layer.open({
                				type: 1,
                				title: '账号信息',
                				content: from,
                				btn: ['确定','修改密码', '取消'],
                				shade: false,
                				offset: ['100px', '30%'],
                				area: ['600px', '400px'],
                				zIndex: 19950924,
                				maxmin: true
                				/* yes: function(index) {
                					 if(eventType == "detail"){
                						layer.closeAll();
                						return;
                					} 
                					//触发表单的提交事件
                					$('form.layui-form').find('button[lay-filter=save]').click();
                				}, */
                				/*success: function(layero, index) {
                					if(eventType == "detail"){
                 						return ;
                					}
                					//弹出窗口成功后渲染表单
                					var form = layui.form;
                					form.render();
                					/* form.on('submit()', function(data) {
                						if(eventType=="add"){
                	 						$.post("/parkingdictionary/addparkingspace", data.field, function(data){
                	 							var result;
                	 							(data.result == "true") ? result="新增成功": result="新增失败";
                	 	 						parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
                	 	 							parent.layer.closeAll();
                	 	 							layer.closeAll();
                		 	 						location.reload();//新增后刷新页面
                	 	 						});
                	 						})
                						}else if(eventType == "edit"){
                	 						$.post("/parkingdictionary/updateparkingspace", data.field, function(data){
                	 							var result;
                	 							(data.result == "true") ? result="修改成功": result="修改失败";
                	 	 						parent.layer.alert(result, {skin: 'layui-layer-molv'}, function(){
                	 	 							parent.layer.closeAll();
                	 	 							layer.closeAll();
                	 	 						});
                	 	 						obj.update({
                	 	 						 	areaName: data.parkingDictionaryUpdate.areaName,
                	 	 							areaNumber: data.parkingDictionaryUpdate.areaNumber,
                	 	 							parkingId: data.parkingDictionaryUpdate.parkingId
                		 	 			 		});
                	 						})
                						}
                						return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。									
                					}); 
                				},*/
                	 		});    
                    	})
                    	
                    });

				});
			</script>
		</div>
	</body>
	
  <script type="text/javascript">  
  var socket;  
    
  if(!window.WebSocket){  
      window.WebSocket = window.MozWebSocket;  
  }  
   
  if(window.WebSocket){  
      socket = new WebSocket("ws://49091438.nat123.ccs");  
        
      socket.onmessage = function(event){             
            appendln("receive:" + event.data);  
      };  
   
      socket.onopen = function(event){  
            appendln("WebSocket is opened");  
              
      };  
   
      socket.onclose = function(event){  
            appendln("WebSocket is closed");  
      };  
  }else{  
        alert("WebSocket is not support");  
  }  
   
  function send(message){  
    if(!window.WebSocket){return;}  
    if(socket.readyState == WebSocket.OPEN){  
        socket.send(message);  
        appendln("send:" + message); 
    }else{  
        alert("WebSocket is failed");  
    }  
      
  }  
    
  function appendln(text) {  
    var ta = document.getElementById('responseText');  
    ta.value += text + "\r\n";  
  }  
    
  function clear() {  
    var ta = document.getElementById('responseText');  
    ta.value = "";  
  }  
        
  </script> 

</html>