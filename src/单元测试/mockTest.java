package 单元测试;

import org.easymock.EasyMock;
import org.junit.Test;
import 单元测试.Bean.User;
import 单元测试.Dao.UserDao;
import 单元测试.Service.UserServiceImpl;


import static org.junit.Assert.assertEquals;

public class mockTest {
    @Test
    public void testQuery(){
        User expectedUser=new User();
        expectedUser.setName("测试");
        UserDao mock= EasyMock.createMock(UserDao.class);
        EasyMock.expect(mock.getById("1001")).andReturn(expectedUser);//创建Mock对象
        EasyMock.expect(mock.getById("1001")).andThrow(new RuntimeException());//录制Mock对象预期行为

        EasyMock.replay(mock);//重放Mock对象，测试时以录制的对象预期行为代替真实对象的行为
        UserServiceImpl service=new UserServiceImpl();
        service.setDao(mock);
        User user = service.query("1001");//调用测试方法
        System.out.println("======"+user);
        assertEquals(expectedUser,user);//断言测试结果
        EasyMock.verify(mock);//验证Mock对象被调用

    }
}
