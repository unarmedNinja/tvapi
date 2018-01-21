package com.tv.db;

import com.tv.models.Episode;
import com.tv.models.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ShowJdbcRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public Show findById(long id) {
        return jdbcTemplate.queryForObject("select * from shows where id=?", new Object[] {
                        id
                },
                new BeanPropertyRowMapper< Show >(Show.class));
    }

    public List<Show> getAllShows() {
        List<Show> shows = jdbcTemplate.query("select * from shows",
                new BeanPropertyRowMapper< Show >(Show.class));

        return shows;
    }

    public int insertShow(Show show) {
        return jdbcTemplate.update("insert into shows (id, name) " + "values(?, ?)",
                new Object[] {
                        show.getId(),
                        show.getName()
                });
    }

    public int insertEpisode(Episode episode) {
        System.out.println("Aired Season: " + episode.getAiredSeason());
        return jdbcTemplate.update("insert into episodes (id, showId, firstAired, airedEpisodeNumber,airedSeason, absoluteNumber, episodeName) " + "values(?, ?, ?, ?, ?, ?, ?)",
                new Object[] {
                        episode.getId(),
                        episode.getShowid(),
                        episode.getFirstAired(),
                        episode.getAiredEpisodeNumber(),
                        episode.getAiredSeason(),
                        episode.getAbsoluteNumber(),
                        episode.getEpisodeName()
                });
    }

    public int deleteEpisodesByShowId(int id) {
        return jdbcTemplate.update("delete from episodes where showId=?", new Object[] {
                id
        });
    }

    public List<Episode> getEpisodesByShowId(int showId) {
        List<Episode> episodes = jdbcTemplate.query("select * from episodes where showId=?", new Object[] {
          showId
        },
                new BeanPropertyRowMapper< Episode >(Episode.class));

        return episodes;
    }

    public List<Episode> getRecentEpisodes(Date startDate) {
        List<Episode> episodes = jdbcTemplate.query("select * from episodes where firstAired >=?", new Object[] {
                        startDate
                },
                new BeanPropertyRowMapper< Episode >(Episode.class));

        return episodes;
    }
}
