Build a Shorten URL application which allows to manage URLs.
You should be able to add new URLs and delete existing ones (CRUD functionality).
Each entry should map to a unique shorten URL and the application have to do a
forwarding to the correct page.

Example of request to save url
```
curl localhost:8080 ‐v ‐XPOST ‐d '{
                                  	"value":"https://github.com/gohargasparyan/url-shortener"
                                  }'
```

Response:
```
{
    "value": "http://localhost:8080/a"
}
```

# Setup

## Download and build
    
    cd url-shortener
    mvn clean package

## Start the application

### Start the application server

    java -jar target/url-shortener-0.0.1-SNAPSHOT.jar
