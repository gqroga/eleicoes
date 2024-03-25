package com.lifters.voter.registration.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_candidatos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE tb_candidatos SET deletado_em = current_timestamp WHERE id_candidato=?")
public class CandidatoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_candidato")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "legenda")
    private String legenda;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    @Column(name = "alterado_em")
    private LocalDateTime alteradoEm;
    @Column(name = "deletado_em")
    private LocalDateTime deletadoEm;
}
