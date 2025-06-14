package br.com.link_shortener.service;

import br.com.link_shortener.repository.LinkRepository;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

}
