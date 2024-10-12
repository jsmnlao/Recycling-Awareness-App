package gbolotin.recyclebuddy_spring_server.repo;


import gbolotin.recyclebuddy_spring_server.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long>
{
}
