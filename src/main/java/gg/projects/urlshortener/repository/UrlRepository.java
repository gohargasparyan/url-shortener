package gg.projects.urlshortener.repository;

import gg.projects.urlshortener.entity.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author gohar.gasparyan
 */
@Repository
public interface UrlRepository extends CrudRepository<Url, Long> {

    Optional<Url> findByOriginalUrl(String originalUrl);

    Optional<Url> findByShortUrl(String shortUrl);

}
