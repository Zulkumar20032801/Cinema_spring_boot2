package com.example.cinema_spring_boot.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "places")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Place {   // место
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
      Long id;
     int x;
     int y;
     int price;
     byte[] image;

    @ToString.Exclude
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH
    }, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    Room room;
    @Transient
    Long roomId;

}