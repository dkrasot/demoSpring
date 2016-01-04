package demo.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;

public class Tweet {
//    id IDENTITY,
//    text VARCHAR(140) NOT NULL,
//    creationDate TIMESTAMP NOT NULL,
//    id_user INT NOT NULL,
//    FOREIGN KEY (id_user) REFERENCES Accounts(id)
    private final Long id;
    private final String text;
    private final Date creationDate;
    private final String username;

    public Tweet(String text, Date creationDate, String username) {
        this(null, text, creationDate, username);
    }

    public Tweet(Long id, String text, Date creationDate, String username) {
        this.id = id;
        this.text = text;
        this.creationDate = creationDate;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, new String[]{"id", "creationDate"});
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[]{"id", "creationDate"});
    }

}
