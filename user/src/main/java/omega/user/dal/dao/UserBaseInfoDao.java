package omega.user.dal.dao;

import omega.user.dal.model.UserBaseInfo;

/**
 * Created by Administrator on 2017/5/13.
 */
public interface UserBaseInfoDao {
    int insert(UserBaseInfo info);

    UserBaseInfo selectByUserId(String userId);
}
