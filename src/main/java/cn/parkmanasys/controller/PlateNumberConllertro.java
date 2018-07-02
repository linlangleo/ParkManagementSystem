package cn.parkmanasys.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.parkmanasys.entity.BoundOfPlateNumber;
import cn.parkmanasys.entity.Car;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.Peccancy;
import cn.parkmanasys.entity.PlateNumber;
import cn.parkmanasys.service.car.CarService;
import cn.parkmanasys.service.peccancy.PeccancyService;
import cn.parkmanasys.service.platenumber.PlateNumberService;
import cn.parkmanasys.util.Constants;


@Controller
@RequestMapping("/plateNumber")
public class PlateNumberConllertro {

	@Resource
	private PlateNumberService plateNumberService;
	
	@Resource
	private PeccancyService peccancyService;
	
	@Resource
	private CarService carService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getallpsbypage")
	@ResponseBody 
	public Object getAllplateNumberByPage(HttpSession session){
				List<Peccancy> psList = null;
				List<Car> carList = null;
				try {
					psList = peccancyService.getAll();
					carList = carService.getCar();
				} catch (Exception e) {
					e.printStackTrace();
				}
				

				Map<String, Object> result = new HashMap<String, Object>();
				result.put("data", psList);
				
				result.put("code", 0);
				result.put("msg", "");
				
				return JSONArray.toJSON(result);
	}
	
	
	@RequestMapping("/delplateNumber")
	@ResponseBody
	public Object delplateNumber(HttpSession session, Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		try {
			if(peccancyService.delPeccancy(id)){
				result.put("result", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	

	
	
}
