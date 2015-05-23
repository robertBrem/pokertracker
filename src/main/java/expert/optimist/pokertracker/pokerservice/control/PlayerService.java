package expert.optimist.pokertracker.pokerservice.control;

import expert.optimist.pokertracker.pokerservice.entity.Player;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class PlayerService {

    @PersistenceContext
    EntityManager em;

    public Set<Player> getAll() {
        List players = em.createNamedQuery("Players.getAll").getResultList();
        return new HashSet<>(players);
    }

    public boolean exist(Long id) {
        return em.find(Player.class, id) != null;
    }

    public Player get(Long id) {
        if (!exist(id)) {
            throw new IllegalArgumentException("Player does not exist!");
        }
        return em.find(Player.class, id);
    }

    public Player create(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player is not set!");
        }
        if (player.getId() != null && exist(player.getId())) {
            throw new IllegalArgumentException("Player already exist!");
        }
        return em.merge(player);
    }

    public Player update(Player player) {
        if (!exist(player.getId())) {
            throw new IllegalArgumentException("Player does not exist!");
        }
        return em.merge(player);
    }

    public void delete(Long id) {
        Player player = get(id);
        em.remove(player);
    }

}
