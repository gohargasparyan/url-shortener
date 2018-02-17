package gg.projects.urlshortener.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author gohar.gasparyan
 */
@Entity
@Data
public class Url {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String originalUrl;

    @Column
    private String shortUrl;

    public Url() {
    }

    public Url(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Url(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }
}