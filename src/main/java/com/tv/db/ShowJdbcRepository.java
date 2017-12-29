package com.tv.db;

import com.tv.models.Episode;
import com.tv.models.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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


    public int insertEpisode(Episode episode) {
        return jdbcTemplate.update("insert into episodes (id, showId, firstAired, airedEpisodeNumber) " + "values(?, ?, ?, ?)",
                new Object[] {
                        episode.getId(),
                        episode.getShowid(),
                        episode.getFirstAired(),
                        episode.getAiredEpisodeNumber()
                });
    }
}
