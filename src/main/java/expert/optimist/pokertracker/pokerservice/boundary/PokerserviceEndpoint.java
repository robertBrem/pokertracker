package expert.optimist.pokertracker.pokerservice.boundary;

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
    public Set<String> getAll() {
        Set<String> players = new HashSet<>();
        players.add("Robert Brem");
        players.add("Dan Lopez");
        return players;
    }

}
