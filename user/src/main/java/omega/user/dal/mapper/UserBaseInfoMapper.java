package omega.user.dal.mapper;

import java.util.List;
import omega.user.dal.model.UserBaseInfo;
import omega.user.dal.model.UserBaseInfoExample;
import org.apache.ibatis.annotations.Param;

public interface UserBaseInfoMapper {
    int countByExample(UserBaseInfoExample example);

    int deleteByExample(UserBaseInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserBaseInfo record);

    int insertSelective(UserBaseInfo record);

    List<UserBaseInfo> selectByExample(UserBaseInfoExample example);

    UserBaseInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserBaseInfo record, @Param("example") UserBaseInfoExample example);

    int updateByExample(@Param("record") UserBaseInfo record, @Param("example") UserBaseInfoExample example);

    int updateByPrimaryKeySelective(UserBaseInfo record);

    int updateByPrimaryKey(UserBaseInfo record);
}