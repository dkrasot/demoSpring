package demo.data;

import demo.entity.UserAccount;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserAccountRepository {

    long count();

    @CachePut(value = "myCache", key = "#result.id") // maybe #result.username?? unique username/email
    UserAccount save(UserAccount profile);

    @Cacheable(value = "myCache", key = "#result.username")
    UserAccount findByUsername(String username);

    @Cacheable(value = "myCache", key = "#result.id")
    UserAccount findById(long id);

    List<UserAccount> findAll();

}
