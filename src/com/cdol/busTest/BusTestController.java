package com.cdol.busTest;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.cdol.busTest.model.XmlBusData;
import com.cdol.busTest.model.XmlData;

@Controller
@RequestMapping("/bus")
public class BusTestController {
	
	@RequestMapping(value = "/jsondata", method = RequestMethod.GET)
    public void jsondata(Model model) {
         
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        Map<String, String> data1 = new HashMap<String, String>();
        data1.put("name", "a");
         
        Map<String, String> data2 = new HashMap<String, String>();
        data2.put("name", "b");
         
        list.add(data1);
        list.add(data2);
         
        model.addAttribute("data", list);
    }
	
	@RequestMapping(value = "/xmlBus", method = RequestMethod.GET)
	public String xmlBus(Model model, HttpServletRequest request, HttpServletResponse response) {
		List<XmlBusData> list = new ArrayList<XmlBusData>();
		
		XmlBusData child1 = new XmlBusData();
        child1.setEmail("1");
        child1.setName("a");
        child1.setPosition("z");
         
        XmlBusData child2 = new XmlBusData();
        child2.setEmail("2");
        child2.setName("b");
        child2.setPosition("x");
         
        list.add(child1);
        list.add(child2);
         
        XmlData data = new XmlData();
        data.setItems(list);
        
        model.addAttribute("data", data);
        
        
		
		String addr = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid"+"?ServiceKey=";
		String serviceKey = "ev5LosPVIB2lgO8EQWkwklWnglOiJ+H3+dXBjCOEys7cloGLNgQdLTcC7GOJAJ6RCcI3SXa+xr9hEFLUqKgJnA==";
		String parameter = "";
		
		try {
			System.out.println("********************시작*******************");
			//인증키(서비스키) url인코딩
			serviceKey = URLEncoder.encode(serviceKey, "UTF-8");			
			
			parameter = parameter + "&" + "busRouteId=3014700";

			addr = addr + serviceKey + parameter;
			System.out.println(addr);
			
			URL url = new URL(addr);
			InputStream in = url.openStream();
			InputStreamReader rin = new InputStreamReader(in, "UTF-8");
			StringBuffer sb = new StringBuffer(); 
			int c;
			
			while ((c = rin.read()) != -1) {
				sb.append((char) c);
			}
			
			rin.close();
			in.close();
			
			System.out.println("********************결과(OutPutString)*******************");
			System.out.println(sb.toString());
			System.out.println("****************************************************");
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(false);
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document xmldoc = db.parse(new InputSource(new StringReader(sb.toString())));
			
			System.out.println("********************결과(xmlDoc)*******************");
			System.out.println(xmldoc.getElementsByTagName("plainNo").item(0).getChildNodes().item(0));
			System.out.println(xmldoc.getElementsByTagName("plainNo").item(1).getChildNodes().item(0));
			System.out.println(xmldoc.getElementsByTagName("plainNo").item(2).getChildNodes().item(0));
			System.out.println(xmldoc.getElementsByTagName("plainNo").item(3).getChildNodes().item(0));
			System.out.println(xmldoc.getElementsByTagName("plainNo").item(4).getChildNodes().item(0));
			System.out.println("************************************************");
			
			//model.addAttribute("data", xmldoc);		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "bus/result";
	}
	
	@RequestMapping("/click.do")
	public ModelAndView clickPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/bus/click");
		
		return mav;
	}

}
