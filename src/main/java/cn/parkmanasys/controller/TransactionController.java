package cn.parkmanasys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.parkmanasys.entity.Car;
import cn.parkmanasys.entity.CarOwnerUser;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.ParkingSpaceStatus;
import cn.parkmanasys.entity.Peccancy;
import cn.parkmanasys.entity.PlateNumber;
import cn.parkmanasys.entity.Transaction;
import cn.parkmanasys.entity.TransactionStatus;
import cn.parkmanasys.entity.TransactionStatusInfo;
import cn.parkmanasys.entity.TransactionType;
import cn.parkmanasys.service.transaction.TransactionService;
import cn.parkmanasys.service.transactionstatus.TransactionStatusService;
import cn.parkmanasys.service.transactionstatusinfo.TransactionStatusInfoService;
import cn.parkmanasys.service.transactiontype.TransactionTypeService;
import cn.parkmanasys.util.Constants;
import cn.parkmanasys.util.PageSupport;

@Controller
@RequestMapping("/Transaction")
public class TransactionController {
	/**
	 * 引入交易表Service层
	 */
	@Resource
	private TransactionService transactionService;

	/**
	 * 交易状态Service层
	 */
	@Resource
	private TransactionStatusService transactionStatusService;

	/**
	 * 交易类型Service层
	 */
	@Resource
	private TransactionTypeService transactionTypeService;

	@Resource
	private TransactionStatusInfoService transactionStatusInfoService;

	@RequestMapping("/getalltcbypage")
	@ResponseBody
	public Object getAllTransactionByPage(PageSupport psPage, Transaction transaction, HttpSession session) {// 获得所有的停车位数据，并分页。
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");// 获取当前登陆对象

		// 分页
		int currentPageNo = 1;
		int pageSize = Constants.pageSize;

		if (psPage != null) {
			if (psPage.getCurrentPageNo() > 0) {
				currentPageNo = psPage.getCurrentPageNo();
			}
			if (psPage.getPageSize() > 0) {
				pageSize = psPage.getPageSize();
			}
		}

		int totalCount = transactionService.getTransactionCount(transaction, logUser.getParkingInfo().getId());
		psPage.setPageSize(pageSize);
		psPage.setTotalCount(totalCount);
		int totalPageCount = psPage.getTotalPageCount();
		// 判断
		if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		psPage.setCurrentPageNo(currentPageNo);

		List<Transaction> tcList = null;
		try {
			tcList = transactionService.findTransactionInfo(transaction, logUser.getParkingInfo().getId(), psPage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回给layui数据
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", tcList);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", psPage.getTotalCount());// 返回总数量即可
		result.put("currentPageNo", psPage.getCurrentPageNo());// 返回当前页数
		result.put("pageSize", psPage.getPageSize());// 返回页面大小

		return JSONArray.toJSON(result);
	}

	@RequestMapping("/layeroptions")
	public String layerOptions(String eventType, Integer id, HttpSession session) {
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");// 获取当前登陆对象
		// 查询需要的显示信息,下拉框
		List<TransactionStatus> tsList = transactionStatusService.findTransactionStatusInfo();
		List<TransactionType> ttList = transactionTypeService.findTransactionTypeInfo();
		session.setAttribute("tsList", tsList);
		session.setAttribute("ttList", ttList);
		if (eventType.equals("add")) {// 新增
			session.removeAttribute("transaction");
		} else if (eventType.equals("edit") || eventType.equals("detail")) {// 修改或查看：先根据id查出数据
			// 根据id查询实体的信息
			Transaction transaction = null;
			if (id != null) {
				transaction = transactionService.getTransactionById(id);
			}
			session.setAttribute("transaction", transaction);
		}

		return "ParkAccount/layerTemp/TransactionTemp";
	}
	
	
	//修改状态

	@RequestMapping("/updateTransaction")
	@ResponseBody
	public Object updateTransaction(HttpSession session, TransactionStatusInfo TransactionUpdate) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");

		TransactionStatusInfo TransactionStatusInfoUpdate = null;
		try {
			TransactionStatusInfoUpdate = transactionStatusInfoService.updateTransaction(TransactionUpdate);
			result.put("TransactionUpdate", TransactionUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (TransactionUpdate != null) {
			result.put("result", "true");
		}

		return result;
	}

	@RequestMapping("/updateTransactiontype")
	@ResponseBody
	public Object updateTransactiontype(HttpSession session, TransactionStatusInfo TransactionUpdate) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");

		TransactionStatusInfo TransactionStatusInfoUpdate = null;
		try {
			TransactionStatusInfoUpdate = transactionStatusInfoService.updateTransactiontype(TransactionUpdate);
			result.put("TransactionUpdate", TransactionUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (TransactionUpdate != null) {
			result.put("result", "true");
		}

		return result;
	}

	@RequestMapping("/updateTransactionok")
	@ResponseBody
	public Object updateTransactionok(HttpSession session, TransactionStatusInfo TransactionUpdate) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");

		TransactionStatusInfo TransactionStatusInfoUpdate = null;
		try {
			TransactionStatusInfoUpdate = transactionStatusInfoService.updateTransactionok(TransactionUpdate);
			result.put("TransactionUpdate", TransactionUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (TransactionUpdate != null) {
			result.put("result", "true");
		}

		return result;
	}

	// 操作

	// 租用车位交易记录信息
	@RequestMapping("/selectTransaction")
	@ResponseBody
	public Object selectTransaction(PageSupport psPage, Transaction transaction, HttpSession session) {// 获得所有的停车位数据，并分页。
		List<Transaction> psList = null;
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");// 获取当前登陆对象
		try {
			psList = transactionService.selectTransaction(logUser.getParkingInfo().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", psList);
		result.put("code", 0);
		result.put("msg", "");
		return JSONArray.toJSON(result);
	}

	// 租用车位审批记录
	@RequestMapping("/selectspTransaction")
	@ResponseBody
	public Object selectspTransaction(PageSupport psPage, Transaction transaction, HttpSession session) {// 获得所有的停车位数据，并分页。
		List<Transaction> psList = null;
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");// 获取当前登陆对象
		try {
			psList = transactionService.selectspTransaction(logUser.getParkingInfo().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", psList);
		result.put("code", 0);
		result.put("msg", "");
		return JSONArray.toJSON(result);
	}

	// 购买车位交易记录信息
	@RequestMapping("/selectgouTransaction")
	@ResponseBody
	public Object selectgouTransaction(PageSupport psPage, Transaction transaction, HttpSession session,Integer id) {// 获得所有的停车位数据，并分页。
		List<Transaction> psList = null;
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");// 获取当前登陆对象
		try {
			psList = transactionService.selectgouTransaction(logUser.getParkingInfo().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", psList);
		result.put("code", 0);
		result.put("msg", "");
		return JSONArray.toJSON(result);
	}

	

	// 购买车位审批记录
	@RequestMapping("/selectgouspTransaction")
	@ResponseBody
	public Object selectgouspTransaction(PageSupport psPage, Transaction transaction, HttpSession session) {// 获得所有的停车位数据，并分页。
		List<Transaction> psList = null;
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");// 获取当前登陆对象
		try {
			psList = transactionService.selectgouspTransaction(logUser.getParkingInfo().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", psList);
		result.put("code", 0);
		result.put("msg", "");
		return JSONArray.toJSON(result);
	}
	
	private Integer transactionstatusid;
	
	
	// 购买车位审批状态记录
	public Integer getTransactionstatusid() {
		return transactionstatusid;
	}

	public void setTransactionstatusid(Integer transactionstatusid) {
		this.transactionstatusid = transactionstatusid;
	}

	@RequestMapping("/selectspStatusTransaction")
	@ResponseBody
	public Object selectspStatusTransaction(PageSupport psPage, Transaction transaction, HttpSession session ,Integer transactionstatusid) {// 获得所有的停车位数据，并分页。
		List<Transaction> psList = null;
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");// 获取当前登陆对象
		try {
			if(transactionstatusid==null)
			{
				psList = transactionService.selectgouTransaction(logUser.getParkingInfo().getId());
			}else if(transactionstatusid==0)
			{
				psList = transactionService.selectgouTransaction(logUser.getParkingInfo().getId());
			}
			else{
				psList = transactionService.selectspStatusTransaction(logUser.getParkingInfo().getId(), transactionstatusid);
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", psList);
		result.put("code", 0);
		result.put("msg", "");
		return JSONArray.toJSON(result);
	}
	
	
	@RequestMapping("/selectspStatuszuTransaction")
	@ResponseBody
	public Object selectspStatuszuTransaction(PageSupport psPage, Transaction transaction, HttpSession session ,Integer transactionstatusid) {// 获得所有的停车位数据，并分页。
		List<Transaction> psList = null;
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");// 获取当前登陆对象
		try {
			if(transactionstatusid==null)
			{
				psList = transactionService.selectTransaction(logUser.getParkingInfo().getId());
			}else if(transactionstatusid==0)
			{
				psList = transactionService.selectTransaction(logUser.getParkingInfo().getId());
			}
			else{
				psList = transactionService.selectspStatuszuTransaction(logUser.getParkingInfo().getId(), transactionstatusid);
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", psList);
		result.put("code", 0);
		result.put("msg", "");
		return JSONArray.toJSON(result);
	}
}
