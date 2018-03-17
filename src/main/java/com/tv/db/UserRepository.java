package com.tv.db;

import com.tv.models.Show;
import com.tv.models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ApplicationUser findByUserName(String username){
        return jdbcTemplate.queryForObject("select * from applicationusers where username=?", new Object[] {
                        username
                },
                new BeanPropertyRowMapper< ApplicationUser >(ApplicationUser.class));
    }

    public int save(ApplicationUser user){
        return jdbcTemplate.update("insert into applicationusers (username, password) " + "values(?, ?)",
                new Object[] {
                        user.getUsername(),
                        user.getPassword()
                });
    }
}
