package br.com.link_shortener.controller;

import br.com.link_shortener.model.Link;
import br.com.link_shortener.service.LinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/url")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }
    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> encurtarUrl(@RequestBody Map<String, String> request){
        String urlOriginal = request.get("urlOriginal");
        String urlCurta = linkService.encurtarUrl(urlOriginal);
        Map<String, String> response = new HashMap<String, String>();
        response.put("urlOriginal", "https://www.xxx.com/"+urlCurta);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{urlCurta}")
    public ResponseEntity<Object> redirecionarUrlOriginal(@PathVariable String urlCurta){
        Optional<Link> urlOptional = linkService.pegarUrlOriginal(urlCurta);
        if (urlOptional.isPresent()) {
            Link link = urlOptional.get();
            System.out.println("Redirecionando para: "+link.getUrlOriginal());
            return ResponseEntity.status(302).location(URI.create(link.getUrlOriginal())).build();
        }
        System.out.println("URL não encontrada ou expirada: "+urlCurta);
        return ResponseEntity.notFound().build();
    }
}
