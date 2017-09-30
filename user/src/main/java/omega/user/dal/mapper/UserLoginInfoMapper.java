package omega.user.dal.mapper;

import java.util.List;
import omega.user.dal.model.UserLoginInfo;
import omega.user.dal.model.UserLoginInfoExample;
import org.apache.ibatis.annotations.Param;

public interface UserLoginInfoMapper {
    int countByExample(UserLoginInfoExample example);

    int deleteByExample(UserLoginInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserLoginInfo record);

    int insertSelective(UserLoginInfo record);

    List<UserLoginInfo> selectByExample(UserLoginInfoExample example);

    UserLoginInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserLoginInfo record, @Param("example") UserLoginInfoExample example);

    int updateByExample(@Param("record") UserLoginInfo record, @Param("example") UserLoginInfoExample example);

    int updateByPrimaryKeySelective(UserLoginInfo record);

    int updateByPrimaryKey(UserLoginInfo record);
}