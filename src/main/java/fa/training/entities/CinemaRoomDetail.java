package fa.training.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CINEMA_ROOM_DETAIL")
@Table(name = "CINEMA_ROOM_DETAIL", schema = "MovieTheater")
@Builder
@ToString
public class CinemaRoomDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CINEMA_ROOM_DETAIL_ID")
    private Integer cinemaRoomDetailId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CINEMA_ROOM_ID")
    private CinemaRoom cinemaRoom;

    @Column(name = "ROOM_RATE", nullable = false)
    private Integer roomRate;

    @Column(name = "ACTIVE_DATE", nullable = false)
    private LocalDate activeDate;

    @Column(name = "ROOM_DESCRIPTION", columnDefinition = "VARCHAR(255)", nullable = false)
    private String roomDescription;


}
