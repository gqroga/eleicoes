package com.lifters.voter.registration.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tb_votos")
@Getter
@Setter
@Entity
public class VotoModel {

    private static long serialVersionUID = -7409374564254782667L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_voto")
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_candidato")
    private CandidatoModel candidatoModel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_eleitor")
    private EleitorModel eleitorModel;

    @Column(name = "data")
    private LocalDateTime data;
}
