package gbolotin.recyclebuddy_spring_server;

import gbolotin.recyclebuddy_spring_server.model.score.Score;
import gbolotin.recyclebuddy_spring_server.model.score.ScoreDao;
import gbolotin.recyclebuddy_spring_server.model.user.AppUserDao;
import gbolotin.recyclebuddy_spring_server.model.user.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

@SpringBootTest
class RecyclebuddySpringServerApplicationTests {

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private ScoreDao scoreDao;

	@Test
	void addUserTest() {
        AppUser user = new AppUser();
        user.setEmail("gbolotin");
        OffsetDateTime now = OffsetDateTime.now();
        user.setRegistration(now);
        user.setName("Galit");
        appUserDao.save(user);
	}

    @Test
    void addScoreTest() {
        Score score = new Score();
        score.setUsername("gbolotin");
        score.setTypesRecycled(3);
        score.setPaperRecycled(3);
        score.setCardboardRecycled(4);
        score.setGlassRecycled(2);
        score.setMetalRecycled(5);
        score.setPlasticRecycled(10);
        scoreDao.save(score);
    }

}
