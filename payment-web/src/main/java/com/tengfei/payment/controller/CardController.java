package com.tengfei.payment.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tengfei.payment.dto.Result;
import com.tengfei.payment.dto.Status;
import com.tengfei.payment.service.CardService;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.vo.Card;
import com.tengfei.payment.vo.Page;

/**
 * 银行卡管理
 * @author miaoxin.chen
 * @date   2016年8月30日
 */
@Controller
@RequestMapping("/cardService")
public class CardController {

	@Autowired
	private CardService cardService;
	
	@Value("#{settings['filePath']}")
	private String filePath;
	
	@RequestMapping("/page")
	public ModelAndView page(Page page) {
		ModelAndView mav = new ModelAndView("cardService/page");
		page.setPageState(true);
		cardService.pageQueryCard(page);
		mav.addObject("page", page);
		return mav;
	}
	
	
	@RequestMapping("/addOrEditCard")
	public ModelAndView addOrEditCard(Card card) {
		ModelAndView mav = new ModelAndView("cardService/addOrEditCard");
		if(Utility.isNotEmpty(card.getId())) card = cardService.getCardById(card.getId());
		mav.addObject("entity", card);
	
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/saveCard")
	public Result saveCard(Card card) {
		Result result = Tools.buildOkResult();
		cardService.saveCard(card);
		return result;
	}
	
	

	@ResponseBody
	@RequestMapping("/delCardById")
	public Result delUserById(Card card) {
		Result result = Tools.buildOkResult();
		cardService.delCardById(card);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/upload") 
	public Result upload(@RequestParam("file")CommonsMultipartFile upfile,HttpServletRequest req) throws IOException{  
		//|获取在Web服务器上的 绝对路径  
	        File dir = new File(filePath);
	        if (!dir.exists()) {//目录不存在则创建目录
	            dir.mkdir();
	        }
	        //|获取输入流  
	        InputStream is=upfile.getInputStream();  
	        //|文件输出流  
	        OutputStream os =new FileOutputStream(new File(filePath,upfile.getOriginalFilename()));  
	        //|循环写入  
	        int length=0;  
	        byte [] buffer=new byte[128];  
	        while((length=is.read(buffer))!=-1)  
	        {  
	            os.write(buffer,0, length);  
	        }  
	        is.close();  
	        os.close();  
	    	Result result = Tools.buildUpLoadResult(Status.OK,"/merchant/img/"+upfile.getOriginalFilename());
	        //|返回至渲染器  
	        return result;  
	    }  
	
}
