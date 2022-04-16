package edu.friday;

import edu.friday.model.SysUser;
import edu.friday.repository.SysUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FridayApplicationTests {

    @Autowired
    SysUserRepository sysUserRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void testSaveUser() {
        SysUser user = new SysUser();
        user.setUserName("tttttt");
        user.setNickName("not t");
        sysUserRepository.save(user);
    }

}
