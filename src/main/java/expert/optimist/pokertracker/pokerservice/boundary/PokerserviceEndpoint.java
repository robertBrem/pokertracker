package expert.optimist.pokertracker.pokerservice.boundary;

import expert.optimist.pokertracker.pokerservice.control.PlayerService;
import expert.optimist.pokertracker.pokerservice.entity.Player;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("players")
public class PokerserviceEndpoint {

    @Inject
    PlayerService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Player> getAll() {
        return service.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Player create(Player player) {
        return service.create(player);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Player get(@PathParam("id") Long id) {
        return service.get(id);
    }

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Player update(@PathParam("id") Long id, Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player is not set!");
        }
        if (id == null) {
            throw new IllegalArgumentException("Id is not set!");
        }
        if (player.getId() == null || !player.getId().equals(id)) {
            throw new IllegalArgumentException("Id(" + id + ") does not match player id(" + player.getId() + ")!");
        }
        return service.update(player);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

}
