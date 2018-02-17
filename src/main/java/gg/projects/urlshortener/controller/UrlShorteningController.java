package gg.projects.urlshortener.controller;

import gg.projects.urlshortener.controller.dto.Url;
import gg.projects.urlshortener.service.UrlShortening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gohar.gasparyan
 */
@RestController
public class UrlShorteningController {

    private final UrlShortening urlShortening;

    @Autowired
    public UrlShorteningController(UrlShortening urlShortening) {
        this.urlShortening = urlShortening;
    }

    @PutMapping
    @RequestMapping(value = "/url")
    public Url shorten(@RequestBody Url url, HttpServletRequest request) {
        String shortUrl =  String.format("http://%s:%d/%s", request.getServerName(), request.getServerPort(), urlShortening.shortUrlPath(url.getValue()));

        return new Url(shortUrl);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String shortUrl) {
        urlShortening.delete(shortUrl);
    }

    @RequestMapping(value = "/{uri:^[a-zA-Z0-9]{1,}$}", method = RequestMethod.GET)
    @ResponseBody()
    public void forwardToOriginalUrl(@PathVariable String uri, final HttpServletResponse response) throws IOException {

        String url = urlShortening.originalUrl(uri);
        response.sendRedirect(url);
    }


}
