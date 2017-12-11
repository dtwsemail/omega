package omega.user.service;

import omega.user.dal.model.UserBaseInfo;

public interface UserInfoSearchService {

	UserBaseInfo queryByMobile(String mobile);

	boolean checkUserExistsByMobile(String mobile);

}
