package com.unir.rents.model.db;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "rating")
    private int rating;

    @Column(name = "rated_at")
    private Timestamp rated_at;

}
