var navs = [{
	"title": "停车位操作",
	"icon": "&#xe628",
	"spread": true,
	"children": [{
		"title": "查看所有车位",
		"icon": "&#xe602;",
		"href": "ParkingSpace.jsp"
	}, /*{
		"title": "操作车位状态",
		"icon": "&#xe602;",
		"href": "form.html"
	},*/ {
		"title": "车位交易审批",
		"icon": "&#xe602;",
		"href": "Transaction.jsp"
	}]
}, {
	"title": "信息记录",
	"icon":"&#xe62d;",
	"spread": false,
	"children": [{
		"title": "系统操作记录",
		"icon": "&#xe602;",
		"href": "operationRecord.jsp"
	}, {
		"title": "停车信息记录",
		"icon": "&#xe602;",
		"href": "parkinginfoRecord.jsp"
	}, {
		"title": "车位交易记录",
		"icon": "&#xe602;",
		"href": "parkingdealRecord.jsp"
	}]
}, {
	"title": "自定义停车场",
	"icon": "&#xe642;",
	"spread": false,
	"children": [{
		"title": "自定义字典",
		"icon": "&#xe602;",
		"href": "ParkingDictionary.jsp"
	},{
        "title": "自定义车位",
        "icon": "&#xe602;",
        "href": "gotoPage/gotoCustomParkingSpace.jsp"
    }]
}, {
	"title": "共享车位",
	"icon": "&#xe613;",
	"href": "",
	"spread": false,
	"children": [{
		"title": "平台管理",
		"icon": "&#xe602;",
		"href": "Sharinfplatshengqi.jsp"
	}, {
		"title": "问题纠纷反馈",
		"icon": "&#xe602;",
		"href": "Sharinfplatformfkui.jsp"
	}]
}, {
	"title": "数据分析",
	"icon": "fa-address-book",
	"href": "",
	"spread": false,
	"children": [{
		"title": "停车车流报表",
		"icon": "&#xe602;",
		"href": "/ParkingTimes/findByYesterday"
	},{
		"title": "停车交易额报表",
		"icon": "&#xe602;",
		"href": "/parkingpayment/findByPayYesterday"
	}]
},{
	"title": "账号管理",
	"icon": "&#xe612;",
	"href": "#",
	"spread": false,
	"children": [{
		"title": "查看账号信息",
		"icon": "&#xe602;",
		"href": "cop.jsp"
	},{
        "title": "修改申请记录",
        "icon": "&#xe602;",
        "href": "parkingInfoUpdateRecord.jsp"
    }]
},{
	"title": "停车场聊天室",
	"icon": "&#xe63a;",
	"href": "WebIM/index.jsp"
}];