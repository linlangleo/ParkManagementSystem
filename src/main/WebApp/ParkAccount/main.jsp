<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/ParkAccount/video/css/toolkit.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/ParkAccount/video/css/font-awesome.min.css">
		<style>
			body{font-size:84%;}
		* {
				padding: 0;
				margin: 0;
				font-family: "microsoft yahei";
			}
			body{
				/* background-image: url(/ParkAccount/video/img/kejilan1.jpg); */
				background-size:cover ;
			}
			ul li {
				list-style-type: none;
			}
			
			.box {
				width: 200px;
				/*border: 1px solid red;*/
			}
			
			ul {
				margin-left: 20px;
				/*border: 1px solid blue;*/
			}
			.nav > .list_title{
				color: #FFFFFF;
				font-size: 18px;
				font-weight: 600;
			}
			.list_area >ul{
				margin: 15px 0 0 0;
			}
			.list_area ul li i{
				cursor: pointer;
			}
			
			.list_area ul li{
				margin: 10px 0;
			}
			.list_area ul li span{
				font-size: 15px;
				margin-left: 5px;
			}
			.draglist span{
				cursor: move;
			}
			.list_ul{
				min-height: 350px;
			}
			.dragremind ul{
				margin: 0;
				width: 100%;
			}
			.dragremind ul li {
			/* 	float: left; */
				background: url(/ParkAccount/video/img/video_bg.png) no-repeat center center;
				background-size:25% ;
			}
			.dragremind ul li .video{
				/* float: left; */
				border: 1px solid #FFFFFF;
				box-shadow: 0px 2px 1px #FFFFFF;
				width: 100%;
				overflow: hidden;
				height: 300px;
			}
			.dragremind ul li h5{
				margin: 0;
				background: url(/ParkAccount/video/img/timgbg.png) repeat-x;
				 background-size:auto 100%;
				height: 35px;
				line-height: 35px;
				color: #FFFFff;
				text-align: center;
			}
			.dragremind ul li h5 img{
				height: 95%;
			}
			.dragremind ul li h5 a{
				color: #a9a6a6;
				font-size: 16px;
				font-weight: 600;
				font-style: normal;
				line-height: 35px;
				float: right;
				padding-right: 8px;
				cursor: pointer;
			}
			.dragremind ul li .videobox{
				height:100%;
				width: 100%;
			}
			.videobox video{
				width: 100%;
				height: 100%;
			}
			.dragremind{padding-top:2em;
			 clear:both;
			 position: relative;
			 }
			@media (min-width: 768px){
			.blw .bld { width: 220px; margin-bottom: 0; }
			}
			
			
			
			
			
			.dragremind ul li h5 i{
				float: left;
				margin-left: 8px;
				line-height: 35px;
			}
			
			
		</style>  
		  
	<link rel="stylesheet" href="${pageContext.request.contextPath }/AdministratorAccount/plugins/layui/css/layui.css" media="all" />
	<script src="${pageContext.request.contextPath }/ParkAccount/layui/layui.js" charset="utf-8"></script>
	
	</head>
	<body>
	  <div class="bw">
	    <div class="dh">
	      <div class="en ble">
	        <nav class="bll">
	          
	          <div class="collapse bki" id="nav-toggleable-md">
	            <ul class="nav lq nav-stacked st list_ul">
	              <li class="asv list_title">环境视频监控</li>
	              <li class="lp">
	                <a class="ln active">街道分布区域</a>
	              </li>
	              <li class="lp">
	              	<div class="list_area menuUl">
						
					</div>
	              </li> 
	            </ul>
	            <hr class="bmi aah">
	          </div>
	        </nav>
	      </div>
	      <div class="et bmj">
	        <div class="bls">
	  <div class="blt">
	    <h2 class="blu">实时监控区域</h2>
	  </div>
	
	  <div class="lf blw">
	    <div class="asr bld">
			<span id="clock"></span>	     
	    </div>
	  </div>
	</div>
	
	
	<div class="dragremind dh ard aav">
		<ul class="">
		<%-- 	<li class="dustbin eo aaq ahj videobox1">
				<div class="video">
					<h5><i class="icon-desktop" aria-hidden="true"></i><img src="${pageContext.request.contextPath }/ParkAccount/video/img/timgbg.jpg"/><span>未放入视频</span><a>X</a></h5>
					<img src="${pageContext.request.contextPath }/ParkAccount/video/img/kejilan1.jpg"/>
				</div>
				
			</li>
			<li class="dustbin eo aaq ahj videobox1">
				<div class="video">
					<h5><img src="${pageContext.request.contextPath }/ParkAccount/video/img/timgbg.jpg"/><span>未放入视频</span><a>X</a></h5>
				</div>
			</li>
			<li class="dustbin eo aaq ahj videobox1">
				<div class="video">
					<h5><img src="${pageContext.request.contextPath }/ParkAccount/video/img/timgbg.jpg"/><span>未放入视频</span><a>X</a></h5>
				</div>
			</li> --%>
		
			<li class="dustbin eo aaq ahj videobox1">
				<div class="video" style="width:800px;height:450px;">
<%-- 					<h5 align=center><img src="${pageContext.request.contextPath }/ParkAccount/video/img/timgbg.jpg"/><span>未放入视频</span><a>X</a></h5> --%>
						
					<iframe height="800px" width="650px"  src="http://10.15.206.116:8080/jsfs.html"></iframe>
				</div>
			</li>
			
			<li class="dustbin eo aaq ahj videobox1">
				<div class="video" style="width:800px;height:450px;">
<%-- 					<h5 align=center><img src="${pageContext.request.contextPath }/ParkAccount/video/img/timgbg.jpg"/><span>未放入视频</span><a>X</a></h5> --%>
						
					<iframe height="800px" width="650px"  src="http://10.15.206.116:8080/jsfs.html"></iframe>
				</div>
			</li>
		</ul>
	</div>
	<hr class="aah">
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath }/ParkAccount/video/js/jquery-2.1.4.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/ParkAccount/video/js/proTree.js" ></script>
<script type="text/javascript">
	    //显示列表
		//后台传入的 标题列表
		var arr = [{							//第一层：0， 第二层：1， 
				id: 1,
				name: "本停车场",
				pid: 0
			},{
				id: 2,
				name: "东区",
				pid: 1
			},
			 {
				id: 10,
				name: "东南门",
				src:1,
				pid:2
			},
			{
				id: 11,
				name: "东北门",
				src:1,
				pid:2
			},
			{
				id: 3,
				name: "西区",
				pid: 1
			}, {
				id: 4,
				name: "西北门",
				src:1,
				pid: 3
			}, {
				id: 9,
				name: "北门",
				src:1,
				pid: 1
			}

		];
        //标题的图标是集成bootstrap 的图标  更改 请参考bootstrap的字体图标替换自己想要的图标
		$(".list_area").ProTree({
			arr: arr,
			simIcon: "icon-play-circle icon-large",//单个标题字体图标 不传默认glyphicon-file
			mouIconOpen: "icon-folder-open icon-large",//含多个标题的打开字体图标  不传默认glyphicon-folder-open
			mouIconClose:"icon-folder-close icon-large"//含多个标题的关闭的字体图标  不传默认glyphicon-folder-close
//			callback: function(id,name) {
//				alert("你选择的id是" + id + "，名字是" + name);
//			}

		})
		
		var eleDustbin = $(".dustbin"),//选择垃圾箱第一个内容
			eleDrags = $(".draglist"),//拖拽结束后
			lDrags = eleDrags.length,
			eleRemind = $(".dragremind")[0],
			eleDrag = null;
		
		for (var i=0; i<lDrags; i+=1) {
			eleDrags[i].onselectstart = function() {
				return false;
			};
			/*拖拽开始*/
		    //拖拽效果
			eleDrags[i].ondragstart = function(ev) {
				ev.dataTransfer.effectAllowed = "move";
				ev.dataTransfer.setData("text", ev.target.innerHTML);
				ev.dataTransfer.setDragImage(ev.target, 0, 0);
				eleDrag = ev.target;
				return true;
			};
			/*拖拽结束*/
			eleDrags[i].ondragend = function(ev) {
				ev.dataTransfer.clearData("text");
				eleDrag = null;
				return false
			};
		}
		
		for (var i=0; i<eleDustbin.length; i++){
			
			$(".dragremind ul" ).on("click","li",function(){
				
				console.log(123)
				//var addresourse="<source src=\"\" "+"type='video/ogg'></source>"
				console.log($(this).find("img"))
				$(this).find("img").remove()
				$(this).find("h5 span").text("未放入视频");
				//$(this).attr("disable","disabled")
			})
			
			
			eleDustbin[i].ondragover = function(ev) {
			/*拖拽元素在目标元素头上移动的时候*/
			
			ev.preventDefault();
			return true;
		};
		eleDustbin[i].ondragenter = function(ev) {
			 /*拖拽元素进入目标元素头上的时候*/
			this.style.color = "#ffffff";
			console.log($(this).attr("class"));
			return true;
		};
		eleDustbin[i].ondrop = function(ev) {
			 /*拖拽元素进入目标元素头上，同时鼠标松开的时候*/
			if (eleDrag) {
				//eleRemind.innerHTML = '<strong>"' + eleDrag.innerHTML + '"</strong>被扔进了垃圾箱';
				var videosrc=$(eleDrag).attr("videoscr");
				var videocontent=$(eleDrag).text();
				//$(this).find("video").attr("src",videosrc);
				//$(this).attr("",videosrc)
				$(this).find("h5 span").text(videocontent)
				$(this).find("h5 a").attr("disable",true);
				$(this).find("h5 a").css("color","#FFFFFF")
				var addli="<video class=\"dustbin videobox1\" autoplay=\"autoplay\" height=\"260\" loop=\"loop\"><source src="+videosrc+" "+"type='video/ogg'></source></video>"
				$(this).find("video").remove();
				$(this).find("h5").after(addli);
				
				console.log(eleDrag)
				console.log(videosrc)
				console.log(videocontent)
				//eleDrag.parentNode.removeChild(eleDrag);
			}
			this.style.color = "#000000";
			return false;
		};
					
		}
		//时间
		function realSysTime(clock){ 
		var now=new Date(); //创建Date对象 
		var year=now.getFullYear(); //获取年份 
		var month=now.getMonth(); //获取月份 
		var date=now.getDate(); //获取日期 
		var day=now.getDay(); //获取星期 
		var hour=now.getHours(); //获取小时 
		var minu=now.getMinutes(); //获取分钟 
		var sec=now.getSeconds(); //获取秒钟 
		if(sec<10){
			sec = "0" + sec;
		}
		if(minu<10){
			minu = "0" + minu;
		}
		month=month+1; 
		var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 
		var week=arr_week[day]; //获取中文的星期 
		var time=year+"年"+month+"月"+date+"日 "+week+" "+hour+":"+minu+":"+sec; //组合系统时间 
		clock.innerHTML=time; //显示系统时间 
		} 
		window.onload=function(){ 
		window.setInterval("realSysTime(clock)",1000); //实时获取并显示系统时间 
		}
		</script>
<script> 
layui.use(['layer','laypage'], function(){
	var $ = layui.$;
  var layer = layui.layer;
	
  var socket;  
    
  if(!window.WebSocket){  
      window.WebSocket = window.MozWebSocket;  
  }  
   
  if(window.WebSocket){  
      socket = new WebSocket("ws://localhost:9999"); 
        
      socket.onmessage = function(event){             
//             appendln("receive:" + event.data);   
            layer.msg(event.data, {time: 10000, offset: 'r', icon:4});
      };
   
      socket.onopen = function(event){  
//           alert("WebSocket is opened");  
            appendln("WebSocket is opened");  
      };  
   
      socket.onclose = function(event){  
//           alert("WebSocket is closed");  
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
})   
  </script> 
		
