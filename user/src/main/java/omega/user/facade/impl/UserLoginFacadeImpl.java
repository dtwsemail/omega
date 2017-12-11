package omega.user.facade.impl;

import omega.lang.common.utils.BeanUtils;
import omega.lang.common.utils.DateUtil;
import omega.user.dal.model.UserLoginInfo;
import omega.user.facade.response.UserLoginResp;
import omega.user.service.UserLoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userLogin")
public class UserLoginFacadeImpl {

	@Autowired
	private UserLoginService userLoginService;

	@ResponseBody
	@RequestMapping(value = "login")
	public UserLoginResp doLogin(String mobile, String pwd) {

		UserLoginResp resp = new UserLoginResp();
		UserLoginInfo loginInfo = userLoginService.login(mobile, pwd);
		BeanUtils.copyProperties(resp, loginInfo);
		return resp;
	}
	
}
