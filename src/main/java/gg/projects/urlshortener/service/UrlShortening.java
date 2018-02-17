package gg.projects.urlshortener.service;

import gg.projects.urlshortener.entity.Url;
import gg.projects.urlshortener.exception.UrlNotFound;
import gg.projects.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static gg.projects.urlshortener.util.Base62.encodeBase10;

/**
 * @author gohar.gasparyan
 */
@Service
public class UrlShortening {

    private final UrlRepository urlRepository;

    @Autowired
    public UrlShortening(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortUrlPath(String originalUrl) {
        Url url = urlRepository.findByOriginalUrl(originalUrl)
                .orElseGet(() -> registerUrl(originalUrl));

        return url.getShortUrl();
    }

    public String originalUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFound("Url not found."));

        return url.getOriginalUrl();
    }

    public void delete(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFound("Url not found."));

        urlRepository.delete(url.getId());
    }

    private Url registerUrl(String originalUrl) {
        Url url = urlRepository.save(new Url(originalUrl));
        url.setShortUrl(encodeBase10(url.getId()));

        return urlRepository.save(url);
    }

}
