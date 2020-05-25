package com.moshiur.springrevision.scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {

    @Autowired
    JdbcConccention jdbcConccention ;

    public JdbcConccention getJdbcConccention() {
        return jdbcConccention;
    }

    public void setJdbcConccention(JdbcConccention jdbcConccention) {
        this.jdbcConccention = jdbcConccention;
    }
}
