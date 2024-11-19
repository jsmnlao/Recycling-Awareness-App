package gbolotin.recyclebuddy_spring_server.controllers;

import gbolotin.recyclebuddy_spring_server.model.user.AppUser;
import gbolotin.recyclebuddy_spring_server.model.user.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppUserController
{
    @Autowired
    private AppUserDao appUserDao;

    @GetMapping("/appuser/get-all")
    public List<AppUser> getAllAppUsers(){
        return appUserDao.getAllAppUsers();
    }

    @PostMapping("/appuser/save")
    public AppUser save(@RequestBody AppUser appUser){
        return appUserDao.save(appUser);
    }

}
