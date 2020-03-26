package com.lzx.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;


import com.lzx.model.Book_Shelf;
import com.lzx.utility.SearchBookIndex;


@Controller
@RequestMapping(value = { "/LZX", "/" })
public class DemoController {

		@RequestMapping("/index")
		public ModelAndView index() {
			return new ModelAndView("index");
		}
		
		@RequestMapping("/getBookInfoByIndex")
		public ModelAndView bookdetail(String bookIndex) {
			Book_Shelf Bb=SearchBookIndex.getBookInfoSearch(bookIndex);
			ModelAndView modelView=new ModelAndView("bookdetail");
			modelView.addObject("Bb", Bb);//把数据带回
			return modelView;//获得的页面对象
		}
		
		@RequestMapping("/3d")
		public ModelAndView D3333333333(String bookIndex) {
		
			ModelAndView modelView=new ModelAndView("3d");//获得页面的对象
		
			return modelView;//返回页面对象
		}
		
		@RequestMapping(value = "/getBookInfoByIndex", method = RequestMethod.POST)
		public @ResponseBody Book_Shelf getIndexAttrDetail(String bookIndex) {
			
			Book_Shelf Bb=SearchBookIndex.getBookInfoSearch(bookIndex);
			return Bb;//返回的数据自动解析为json格式的数据

		}
		
	/* 
	 * Spring MVC:先访问controller，controller找到对应的页面，然后把页面构建成一个对象返回
	 * Spring MVC jstl:把java对象传到前端，得想办法解析，jstl是一种解析的方式
	 * 
	 */
	

}