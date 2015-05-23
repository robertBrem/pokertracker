package expert.optimist.pokertracker.pokerservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PLAYERS")
@NamedQueries(
        @NamedQuery(name = "Players.getAll", query = "SELECT p FROM Player p")
)
@Data
public class Player {
    @Id
    @SequenceGenerator(name = "PlayersSequence", sequenceName = "PLAYERS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PlayersSequence")
    @Column(name = "PLAYER_ID")
    private Long id;
    private String firstName;
    private String lastName;
    private Long points;
}
