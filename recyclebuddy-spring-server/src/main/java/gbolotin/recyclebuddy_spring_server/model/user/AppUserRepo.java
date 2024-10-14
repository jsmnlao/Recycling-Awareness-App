package gbolotin.recyclebuddy_spring_server.model.user;


import gbolotin.recyclebuddy_spring_server.model.user.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends CrudRepository<AppUser, Long>
{
}
