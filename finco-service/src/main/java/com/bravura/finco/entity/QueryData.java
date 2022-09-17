package com.bravura.finco.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "QUERY_DATA")
public class QueryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, insertable = false)
    private Long id;

    @Column(name = "QUERY", nullable = false, unique = false)
    private String query;

}
