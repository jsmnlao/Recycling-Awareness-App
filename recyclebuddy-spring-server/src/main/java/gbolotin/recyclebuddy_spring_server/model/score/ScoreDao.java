package gbolotin.recyclebuddy_spring_server.model.score;

import gbolotin.recyclebuddy_spring_server.model.user.AppUser;
import gbolotin.recyclebuddy_spring_server.model.user.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreDao
{
    // Service class to be called from other places using autowiring

    @Autowired
    public ScoreRepo repo;

    public void save(Score score){
        repo.save(score);
    }

    public void delete(Score score){
        repo.delete(score);
    }

    public List<Score> getAllScores(){
        List<Score> scores = new ArrayList<>();
        Streamable.of(repo.findAll())
                .forEach(scores::add); // same as the following lambda: .forEach(user -> {users.add(user)})
        return scores;
    }
}
