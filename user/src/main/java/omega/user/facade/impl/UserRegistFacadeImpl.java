package omega.user.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import omega.user.facade.UserRegisteFacade;
import omega.user.facade.request.RegistByMobileRequest;
import omega.user.facade.response.RegistByMobileResponse;
import omega.user.lang.CommonValidateUtil;
import omega.user.service.UserInfoSearchService;
import omega.user.service.UserRegistService;

@Controller
@RequestMapping("/userRegist")
public class UserRegistFacadeImpl implements UserRegisteFacade {

	@Autowired
	private UserRegistService userRegistService;
	
	@RequestMapping("/index")
	public String doIndex(){
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/registByMobile")
	public RegistByMobileResponse doRegistByMobile(@RequestBody RegistByMobileRequest request){
		CommonValidateUtil.checkUserPwdValidate(request.getPassword());
		userRegistService.registByMobile(request.getMobile(), request.getPassword());
		return null;
	}
	
	
	
	
	
	
	
}
