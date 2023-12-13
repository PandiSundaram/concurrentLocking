package com.pandi.databaselocking.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String busName;
    private Integer capacity;
    @Version
    private Long version;

    @OneToMany(mappedBy = "bus",fetch = FetchType.EAGER)
    List<Ticket> tickets;



}
