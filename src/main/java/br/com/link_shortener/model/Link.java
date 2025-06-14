package br.com.link_shortener.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String urlOriginal;

    @Column(unique = true,  nullable = false)
    private String urlEncurtada;

    @Column(nullable = false)
    private String urlQrCode;
    private LocalDateTime urlCriadaEm;


}
