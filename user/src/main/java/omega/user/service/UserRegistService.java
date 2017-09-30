package omega.user.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/5/13.
 */
public interface UserRegistService {

    @Transactional
    void registByMobile(String mobile, String pwd);
}
