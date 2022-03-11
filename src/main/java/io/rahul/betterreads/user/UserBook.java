package io.rahul.betterreads.user;

import java.time.LocalDate;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "user_books")
public class UserBook {

    @PrimaryKey
    private UserBookPrimaryKey key;

    @Column("status")
    @CassandraType(type = Name.TEXT)
    private String status;

    @Column("startDate")
    @CassandraType(type = Name.DATE)
    private LocalDate startDate;

    @Column("endDate")
    @CassandraType(type = Name.DATE)
    private LocalDate endDate;

    @Column("rating")
    @CassandraType(type = Name.INT)
    private int rating;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UserBookPrimaryKey getKey() {
        return key;
    }

    public void setKey(UserBookPrimaryKey key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "UserBook [endDate=" + endDate + ", key=" + key + ", rating=" + rating + ", startDate=" + startDate
                + ", status=" + status + "]";
    }

}
