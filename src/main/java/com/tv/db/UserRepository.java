package com.tv.db;

import com.tv.models.ApplicationRole;
import com.tv.models.ApplicationUserRole;
import com.tv.models.Show;
import com.tv.models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ApplicationUser findByUserName(String username){
        System.out.println("Searching for user: " + username);
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

    public List<ApplicationUserRole> getRolesForUser(ApplicationUser user){
       String query= "select id, role from user_role where id=" + user.getId();
       return jdbcTemplate.query(query, new RowMapper() {
           public ApplicationUserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
               ApplicationUserRole role = new ApplicationUserRole(
                       rs.getInt("id"),
                       ApplicationRole.valueOf(rs.getString("role"))
               );
               return role;
           }
       });
    }
}
