package gbolotin.recyclebuddy_spring_server.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserDao
{
    // Service class to be called from other places using autowiring

    @Autowired
    public AppUserRepo repo;

    public AppUser save(AppUser user){
        return repo.save(user);
    }

    public void delete(AppUser user){
        repo.delete(user);
    }

    public List<AppUser> getAllAppUsers(){
        List<AppUser> users = new ArrayList<>();
        Streamable.of(repo.findAll())
                .forEach(users::add); // same as the following lambda: .forEach(user -> {users.add(user)})
        return users;
    }
}
