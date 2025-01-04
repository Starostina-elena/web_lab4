package org.lia.lab4_v3.DBEntity;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "points", schema = "s414257")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(name = "sequence-generator", sequenceName = "points_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private double y;
    @Column(name = "r")
    private double r;

    @Column(name = "result")
    private boolean result;

    @Column(name = "date")
    private Timestamp date;

}