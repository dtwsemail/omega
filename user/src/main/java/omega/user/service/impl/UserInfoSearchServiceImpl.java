package omega.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import omega.lang.redis.RedisUtils;
import omega.user.dal.dao.UserBaseInfoDao;
import omega.user.dal.model.UserBaseInfo;
import omega.user.service.UserInfoSearchService;


@Service
public class UserInfoSearchServiceImpl implements UserInfoSearchService {


	@Autowired
	private  UserBaseInfoDao userBaseInfoDao;
	@Autowired
	private RedisUtils redisUtils;
	
	
	@Override
	public UserBaseInfo queryByMobile(String mobile){
		UserBaseInfo info = userBaseInfoDao.selectByMobile(mobile);
		return info;
	}
	
	@Override
	public boolean checkUserExistsByMobile(String mobile){
		return queryByMobile(mobile)!=null;
	}
}
