package com.lifters.voter.registration.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_cargos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE tb_cargos SET deletado_em = current_timestamp WHERE id_cargo=?")
public class CargoModel implements Serializable {

    private static long serialVersionUID = 9146823172603510936L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cargo")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "cargoModel", cascade = CascadeType.ALL)
    private List<CandidatoModel> candidatos;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    @Column(name = "alterado_em")
    private LocalDateTime alteradoEm;
    @Column(name = "deletado_em")
    private LocalDateTime deletadoEm;
}
