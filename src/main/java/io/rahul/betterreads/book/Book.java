package io.rahul.betterreads.book;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "book_by_id")
public class Book {
    @Id
    @PrimaryKeyColumn(name = "book_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;

    @Column("bookTitle")
    @CassandraType(type = Name.TEXT)
    private String title;

    @Column("description")
    @CassandraType(type = Name.TEXT)
    private String description;

    @Column("publishedDate")
    @CassandraType(type = Name.DATE)
    private LocalDate publishedDate;

    @Column("coverId")
    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> coverId;

    @Column("authorName")
    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> authorName;

    @Column("authorList")
    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> authorList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<String> getCoverId() {
        return coverId;
    }

    public void setCoverId(List<String> coverId) {
        this.coverId = coverId;
    }

    public List<String> getAuthorName() {
        return authorName;
    }

    public void setAuthorName(List<String> authorName) {
        this.authorName = authorName;
    }

    public List<String> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<String> authorList) {
        this.authorList = authorList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book [authorList=" + authorList + ", authorName=" + authorName + ", coverId=" + coverId
                + ", description=" + description + ", id=" + id + ", publishedDate=" + publishedDate + ", title="
                + title + "]";
    }

}
