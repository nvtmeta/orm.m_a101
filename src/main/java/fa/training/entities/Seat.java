package fa.training.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "SEAT")
@Table(name = "SEAT", schema = "MovieTheater")
@Check(constraints = "SEAT_STATUS IN ('Available', 'Unavailable', 'Booked') AND SEAT_TYPE IN ('VIP', 'Normal')")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEAT_ID")
    private Integer seatId;

    @Column(name = "SEAT_COLUMN", columnDefinition = "VARCHAR(255)", nullable = false)
    private String seatColumn;


    @Column(name = "SEAT_ROW", nullable = false)
    private Integer seatRow;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CINEMA_ROOM_ID")
    private CinemaRoom cinemaRoom;

    @Column(name = "SEAT_STATUS", columnDefinition = "VARCHAR(255)", nullable = false)
    private String seatStatus;

    @Column(name = "SEAT_TYPE", columnDefinition = "VARCHAR(255)", nullable = false)
    private String seatType;

    public Seat(Integer seatId, String seatColumn, Integer seatRow,  String seatStatus, String seatType) {
        this.seatId = seatId;
        this.seatColumn = seatColumn;
        this.seatRow = seatRow;
        this.seatStatus = seatStatus;
        this.seatType = seatType;
    }
}
