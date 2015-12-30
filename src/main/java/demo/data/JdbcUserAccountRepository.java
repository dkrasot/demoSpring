package demo.data;

import demo.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcUserAccountRepository implements UserAccountRepository {
    private JdbcOperations jdbc;
    private final String SELECT_ACCOUNTS = "SELECT * FROM Accounts";
    private final String SELECT_COUNT_ACCOUNTS = "SELECT COUNT(*) FROM Accounts";
    private final String SELECT_ACCOUNT_BY_USERNAME = "SELECT id, username, password, email FROM Accounts WHERE username = ?";
    private final String SELECT_ACCOUNT_BY_ID = "SELECT id, username, password, email FROM Accounts WHERE id = ?";
    private final String INSERT_ACCOUNT = "INSERT INTO Accounts (username, password, email) VALUES (?,?,?)";

    @Autowired
    public JdbcUserAccountRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long count() {
        return jdbc.queryForObject(SELECT_COUNT_ACCOUNTS, Long.class);
    }

    @Override
    public UserAccount save(UserAccount account) {
        jdbc.update(INSERT_ACCOUNT, account.getUsername(), account.getPassword(), account.getEmail());
        return findByUsername(account.getUsername()); // now select for GETTING result.ID; WAS: return account WITHOUT SELECT from REPO
    }

    @Override
    public UserAccount findByUsername(String username) {
        return jdbc.queryForObject(SELECT_ACCOUNT_BY_USERNAME, new UserAccountRowMapper(), username);
    }

    @Override
    public UserAccount findById(long id) {
//        try {
        return jdbc.queryForObject(SELECT_ACCOUNT_BY_ID, new UserAccountRowMapper(), id);
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
    }

    @Override
    public List<UserAccount> findAll() {
        return jdbc.query(SELECT_ACCOUNTS, new UserAccountRowMapper());
    }

    private static class UserAccountRowMapper implements RowMapper<UserAccount> {
        public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new UserAccount(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),//TODO hide password? : //null,
                    rs.getString("email"));
        }
    }
}
