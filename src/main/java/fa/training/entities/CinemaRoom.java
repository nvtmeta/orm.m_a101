package fa.training.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CINEMA_ROOM")
@Table(name = "CINEMA_ROOM", schema = "MovieTheater")
@Builder
@ToString
public class CinemaRoom {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CINEMA_ROOM_ID")
    private Integer cinemaRoomId;

    @Column(name = "CINEMA_ROOM_NAME", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String roomName;

    @Column(name = "SEAT_QUANTITY", nullable = false)
    private Integer seatQuantity;

    @OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.MERGE)
    @ToString.Exclude
    private List<Seat> seats;

    @OneToOne(mappedBy = "cinemaRoom", cascade = CascadeType.ALL)
    @ToString.Exclude
    private CinemaRoomDetail cinemaRoomDetail;

    public CinemaRoom(Integer cinemaRoomId, String roomName, Integer seatQuantity) {
        this.cinemaRoomId = cinemaRoomId;
        this.roomName = roomName;
        this.seatQuantity = seatQuantity;
    }

    public CinemaRoom(String roomName, Integer seatQuantity) {
        this.roomName = roomName;
        this.seatQuantity = seatQuantity;
    }
}
