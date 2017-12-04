package org.seckill.controller;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.pojo.Seckill;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller@RequestMapping("/seckill")
public class SeckillController {
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	@Resource
	private SeckillService seckillService;
	//所有秒杀商品
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}
	//秒杀商品详细信息
	@RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
	public String detail(@PathVariable Long seckillId,Model model) {
		if(seckillId==null)
			return "redirect:/seckill/list";
		Seckill seckill = seckillService.getSeckillById(seckillId);
		if(seckill==null)
			return "redirect:/seckill/list";
		
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	//获得秒杀相关信息
	@RequestMapping(value="/{seckillId}/exposer",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable Long seckillId) {
		SeckillResult<Exposer> result;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			result=new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			result=new SeckillResult<>(false, e.getMessage());
		}
		return result;
	}
	//执行秒杀
	@RequestMapping(value="/{seckillId}/{md5}/execution",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable Long seckillId,@PathVariable String md5,@CookieValue(value="userPhone",required=false) Long userPhone) {
		SeckillResult<SeckillExecution> result = null;
		SeckillExecution seckillExecution=null;
		if(userPhone==null)
			return new SeckillResult<SeckillExecution>(false, "未注册");
		try {
			seckillExecution= seckillService.executeSeckill(seckillId, userPhone, md5);
			result=new SeckillResult<SeckillExecution>(true,seckillExecution);
			
		} catch (SeckillCloseException e) {
			seckillExecution=new SeckillExecution(seckillId, SeckillStateEnum.END);
			result=new SeckillResult<>(false,seckillExecution);
		} catch (RepeatKillException e) {
			seckillExecution=new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
			result=new SeckillResult<>(false, seckillExecution);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			seckillExecution=new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
			result=new SeckillResult<>(false, seckillExecution);
		}
		return result;
	}
	
	
		//获得系统当前时间
		@RequestMapping(value="/time/now",method=RequestMethod.GET)
		@ResponseBody
		public SeckillResult<Long> time() {
			Date now=new Date();
			return new SeckillResult<>(true, now.getTime());
		}
		
		/**
		 * 获取增加秒杀商品的页面
		 * @return
		 */
		@GetMapping("/add")
		public String getAddView() {
			return "add";
		}
		
		@PostMapping("/add")
		public String saveSeckill(Seckill seckill) {
			seckillService.save(seckill);
			return "redirect:/seckill/list";
		}
}
