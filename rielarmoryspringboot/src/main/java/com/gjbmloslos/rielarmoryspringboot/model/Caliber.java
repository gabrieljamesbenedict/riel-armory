package com.gjbmloslos.rielarmoryspringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "calibers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Caliber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caliberId;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

}
