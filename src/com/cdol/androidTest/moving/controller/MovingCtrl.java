/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF I-POPCORN.CO.KR.
 * I-POPCORN.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 I-POPCORN.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 i-popcorn.co.kr에 있으며,
 * i-popcorn.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * i-popcorn.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2014 i-popcorn.co.kr All Rights Reserved.
 *
 *
 * @author wscha@i-popcorn.co.kr
 * @since 2014-06-16
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: MovingCtrl.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140616000000][wscha@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.androidTest.moving.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdol.androidTest.common.Session;
import com.cdol.androidTest.common.dto.ResponseDto;
import com.cdol.androidTest.moving.dto.ApDto;
import com.cdol.androidTest.moving.dto.BusDto;
import com.cdol.androidTest.moving.dto.BusRouteDto;
import com.cdol.androidTest.moving.dto.MovingDto;
import com.cdol.util.inter.Bis;


/**
 * @author wscha@i-popcorn.co.kr
 * @since 2014-06-16
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
@RequestMapping("/androidTest")
@Controller
public class MovingCtrl extends Session{
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(MovingCtrl.class);
	
	@Autowired
	Properties commonProperties;
	
	/**
	 * @author wscha@i-popcorn.co.kr
	 * @since 2014-06-28
	 * 
	 * @param MovingDto
	 * @param HttpServletRequest
	 * @return ResponseDto
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	@RequestMapping(value = "/api/infoBus.do", method = RequestMethod.POST)
	public @ResponseBody ResponseDto infoBus(@RequestBody MovingDto movingDto, HttpServletRequest request) {
		
		ResponseDto responseDto = new ResponseDto();
		String veh_data		= "";
		String route_data	= "";
		Bis veh_bis			= new Bis();
		Bis route_bis		= new Bis();
		BusDto busDto		= new BusDto();
		
		try {
			/** API Security(METHOD=POST, content-type=application/json; charset=UTF-8, accept=application/json, User-Agent=*) */			
			if (!checkAccess(request)) {
				responseDto.setCd_res(commonProperties.getProperty("res.1001.cd", "[UNDEFINED]"));
				responseDto.setMsg(commonProperties.getProperty("res.1001.msg", "[UNDEFINED]"));
				return responseDto;  
			}
			
			if(movingDto.getBus_id() != null || !movingDto.getBus_id().equals("")){
				/* vehId=7103, busRouteId=3014700 */
				
				// bis 특정버스 위치 정보
				String veh_location_url = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByVehId";
				String bis_serviceKey = "ev5LosPVIB2lgO8EQWkwklWnglOiJ+H3+dXBjCOEys7cloGLNgQdLTcC7GOJAJ6RCcI3SXa+xr9hEFLUqKgJnA==";
				String veh_params = "&" + "vehId=" + movingDto.getBus_id();
				
				veh_data = veh_bis.restClient(veh_location_url, bis_serviceKey, veh_params);
				
				JSONObject veh_obj	= (JSONObject) new XMLSerializer().read(veh_data);
				JSONObject veh_msgBody	= (JSONObject) veh_obj.get("msgBody");
				JSONObject veh_item	= (JSONObject) veh_msgBody.get("itemList");
				
				busDto.setLics_plat_no(veh_item.get("plainNo").toString());
				busDto.setStat_id(Integer.parseInt(veh_item.get("stId").toString()));
				busDto.setIs_stop(Integer.parseInt(veh_item.get("stopFlag").toString()));
				
				// bis 踰꾩뒪�몄꽑��
				String route_info_url = "http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute";
				String route_params = "&" + "busRouteId=" + movingDto.getRoute_id();
				logger.info("[" + this.getClass().getName() + ".infoBus()]" + route_info_url + route_params);
				
				route_data = route_bis.restClient(route_info_url, bis_serviceKey, route_params);
				
				JSONObject route_obj	= (JSONObject) new XMLSerializer().read(route_data);
				JSONArray route_msgBody	= (JSONArray) route_obj.get("msgBody");
				BusRouteDto[] list = new BusRouteDto[route_msgBody.size()];
				
				for(int i=0 ; i < route_msgBody.size() ; i++){
					JSONObject obj = route_msgBody.getJSONObject(i);
					BusRouteDto tmp = new BusRouteDto();
					tmp.setSeq(Integer.parseInt(obj.get("seq").toString()));
					tmp.setId(obj.get("station").toString());
					tmp.setNme(obj.get("stationNm").toString());
					
					list[i] = tmp;
					
					if(obj.get("station").equals(veh_item.get("stId"))){
						busDto.setStat_nme(obj.get("stationNm").toString());
						busDto.setBus_cors_full_no(obj.get("busRouteNm").toString());
					}
				}
				
				busDto.setStat_count_(route_msgBody.size());
				busDto.setStat_list(list);
				
				responseDto.setCd_res(commonProperties.getProperty("res.0000.cd", "[UNDEFINED]"));
				responseDto.setMsg(commonProperties.getProperty("res.0000.msg", "[UNDEFINED]"));
				responseDto.setData(busDto);
			}
			else {
				responseDto.setCd_res(commonProperties.getProperty("res.0000.cd", "[UNDEFINED]"));
				responseDto.setMsg(commonProperties.getProperty("res.0000.msg", "[UNDEFINED]"));
				responseDto.setData(busDto);
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".infoBus()]" + e.getMessage(), e);
			responseDto.setCd_res(commonProperties.getProperty("res.9100.cd", "[UNDEFINED]"));
			responseDto.setMsg(commonProperties.getProperty("res.9100.msg", "[UNDEFINED]"));
		}
		
		return responseDto;
	}
}
