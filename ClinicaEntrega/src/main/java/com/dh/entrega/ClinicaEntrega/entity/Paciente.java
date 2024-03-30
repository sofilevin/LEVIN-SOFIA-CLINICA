package com.dh.entrega.ClinicaEntrega.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaIngreso;
    @JoinColumn(name = "domicilio_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Domicilio domicilio;
    @OneToMany(mappedBy = "paciente")
    private Set<Turno> turnoSet = new HashSet<>();

}
