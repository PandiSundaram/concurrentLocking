package com.pandi.databaselocking.Entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;

    @ManyToOne
    @JoinColumn(name="busticket")
    Bus bus;
    @Version
    private Long version;



}
