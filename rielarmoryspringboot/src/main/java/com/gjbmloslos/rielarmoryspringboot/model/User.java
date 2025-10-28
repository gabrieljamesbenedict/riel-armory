package com.gjbmloslos.rielarmoryspringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String password;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

}
