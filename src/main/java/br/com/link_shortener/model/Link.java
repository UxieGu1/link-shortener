package br.com.link_shortener.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "links")
@Getter
@Setter
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlOriginal;
    private String urlEncurtada;
    private LocalDateTime urlCriadaEm;
    private LocalDateTime dataExpiracao;


}
