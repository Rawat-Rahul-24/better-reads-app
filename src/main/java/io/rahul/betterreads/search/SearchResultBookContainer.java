package io.rahul.betterreads.search;

import java.util.List;

public class SearchResultBookContainer {

    public String key;
    public String title;
    public List<String> author_name;
    public String cover_i;
    public List<String> publish_year;
    public String publishedYear;
    public List<String> isbn;

    public List<String> getIsbn() {
        return isbn;
    }

    public void setIsbn(List<String> isbn) {
        this.isbn = isbn;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_i() {
        return cover_i;
    }

    public void setCover_i(String cover_i) {
        this.cover_i = cover_i;
    }

    public List<String> getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(List<String> author_name) {
        this.author_name = author_name;
    }

    public List<String> getPublish_year() {
        return publish_year;
    }

    public void setPublish_year(List<String> publish_year) {
        this.publish_year = publish_year;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(String publishedYear) {
        this.publishedYear = publishedYear;
    }

}
