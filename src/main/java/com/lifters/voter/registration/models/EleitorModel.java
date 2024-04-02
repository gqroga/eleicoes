package com.lifters.voter.registration.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "tb_eleitores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE tb_eleitores SET deletado_em = current_timestamp WHERE id_eleitor=?")
public class EleitorModel {

    private static long serialVersionUID = -3337863329558727162L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_eleitor")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    @CPF
    private String cpf;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    @Column(name = "alterado_em")
    private LocalDateTime alteradoEm;
    @Column(name = "deletado_em")
    private LocalDateTime deletadoEm;

}
