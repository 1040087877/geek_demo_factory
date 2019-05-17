package 单元测试.Service;

import 单元测试.Bean.User;
import 单元测试.Dao.UserDao;

public class UserServiceImpl {
    private UserDao dao;
    public User query(String id ){
        return dao.getById(id);
    }

    public void setDao(UserDao dao) {
        this.dao = dao;
    }
}
