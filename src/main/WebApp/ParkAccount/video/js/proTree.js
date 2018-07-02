(function($) {
			var Tree = function(element, options) {
				this.element = element;
				//json数组
				this.JSONArr = options.arr;
				//单个文件图标
				this.simIcon = options.simIcon || "";
				//多个文件打开图标
				this.mouIconOpen = options.mouIconOpen || "";
				//多个文件关闭图标
				this.mouIconClose = options.mouIconClose || "";
				//回调函数
				this.callback = options.callback || function() {};
				//初始化
				this.init();

			}
			//初始化事件
			Tree.prototype.init = function() {
				//事件
				this.JSONTreeArr = this.proJSON(this.JSONArr, 0);
				this.treeHTML = this.proHTML(this.JSONTreeArr);
				this.element.append(this.treeHTML);
				this.bindEvent();
			}
			//生成树形JSON数据
			Tree.prototype.proJSON = function(oldArr, pid) {
				var newArr = [];
				var self = this;
				oldArr.map(function(item) {
					if(item.pid == pid) {
						var obj = {
							id: item.id,
							name: item.name,
							src:item.src
						}
						var child = self.proJSON(oldArr, item.id);
						if(child.length > 0) {
							obj.child = child
						}
						newArr.push(obj)
					}

				})
				return newArr;
				//console.log(newArr)

			};
			//生成树形HTML
			Tree.prototype.proHTML = function(arr) {
				var ulHtml = "<ul class='menuUl'>";
				var self = this;
				arr.map(function(item) {
					if(item.src){
						var lihtml = "<li class=\"draglist\" title=\'拖拽我\' draggable=\"true\" videoscr=\"video/video/ssp.mp4\">";
					}
					else{
						var lihtml = "<li>";
					}
					if(item.child && item.child.length > 0) {
						lihtml += "<i ischek='true' class='" + self.mouIconOpen + "'></i>" +
							"<span id='" + item.id + "'>" + item.name + "</span>"
						var _ul = self.proHTML(item.child);
						lihtml += _ul + "</li>";
					} else {
						//if()
						lihtml += "<i class='" + self.simIcon + "'></i>" +
							"<span id='" + item.id + "'>" + item.name + "</span>";
					}
					ulHtml += lihtml;
				})
				ulHtml += "</ul>";
				return ulHtml;
				//拖拽事件	
			}
			Tree.prototype.bindEvent = function() {
				var self = this;
				this.element.find(".menuUl li i").click(function() {
					var ischek = $(this).attr("ischek");
					if(ischek == 'true') {
						var menuUl = $(this).closest("li").children(".menuUl");
						$(this).removeClass(self.mouIconOpen).addClass(self.mouIconClose)
						menuUl.hide();
						$(this).attr("ischek", 'false');
					} else if(ischek == 'false') {
						var menuUl = $(this).closest("li").children(".menuUl");
						menuUl.show();
						$(this).removeClass(self.mouIconClose).addClass(self.mouIconOpen)
						$(this).attr("ischek", 'true')
					}
				});

				this.element.find(".menuUl li span").click(function() {
					var id = $(this).attr("id");
					var name = $(this).text();
					self.callback(id, name)
				})
			}
			//给jquery对象拓展一个树形对象
			$.fn.extend({
				ProTree: function(options) {
					return new Tree($(this), options)
				}
			})
		})(jQuery);
		


