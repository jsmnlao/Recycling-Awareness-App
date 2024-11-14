package gbolotin.recyclebuddy_spring_server.model.score;


import gbolotin.recyclebuddy_spring_server.model.score.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepo extends CrudRepository<Score, Long>
{
}
