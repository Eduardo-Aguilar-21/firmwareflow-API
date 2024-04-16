package com.ast.firmwareflow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "versions")
public class VersionsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "proyect", referencedColumnName = "id", nullable = false)
    private ProjectsModel projectModel;
}
