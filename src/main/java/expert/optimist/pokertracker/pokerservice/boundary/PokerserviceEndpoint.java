package expert.optimist.pokertracker.pokerservice.boundary;

import expert.optimist.pokertracker.pokerservice.entity.Player;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.Set;

@Path("players")
public class PokerserviceEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Player> getAll() {

        Player robert = new Player();
        robert.setFirstName("Robert");
        robert.setLastName("Brem");
        robert.setPoints(1_000L);

        Player dan = new Player();
        dan.setFirstName("Dan");
        dan.setLastName("Lopez");
        dan.setPoints(500L);

        Set<Player> players = new HashSet<>();
        players.add(robert);
        players.add(dan);
        return players;
    }

}
