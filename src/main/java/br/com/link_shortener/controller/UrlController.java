package br.com.link_shortener.controller;

import br.com.link_shortener.model.Url;
import br.com.link_shortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }
    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> encurtarUrl(@RequestBody Map<String, String> request){
        String urlOriginal = request.get("urlOriginal");
        String urlCurta = urlService.encurtarUrl(urlOriginal);
        Map<String, String> response = new HashMap<String, String>();
        response.put("urlOriginal", "https://www.xxx.com/"+urlCurta);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{urlCurta}")
    public ResponseEntity<Object> redirecionarUrlOriginal(@PathVariable String urlCurta){
        Optional<Url> urlOptional = urlService.pegarUrlOriginal(urlCurta);
        if (urlOptional.isPresent()) {
            Url url = urlOptional.get();
            System.out.println("Redirecionando para: "+url.getUrlOriginal());
            return ResponseEntity.status(302).location(URI.create(url.getUrlOriginal())).build();
        }
        System.out.println("URL não encontrada ou expirada: "+urlCurta);
        return ResponseEntity.notFound().build();
    }
}
