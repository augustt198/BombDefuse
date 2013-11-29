package main.java.im.prox.bombdefuse;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.List;

public class DefuseListener implements Listener{

    final BombDefuse plugin;
    private AttemptManager am;

    public DefuseListener(BombDefuse plugin) {
        this.plugin = plugin;
        am = plugin.getAttemptManager();
    }

    @EventHandler
    public void PlayerInteract(PlayerInteractEntityEvent event) {
        if(!(event.getRightClicked() instanceof TNTPrimed)) {
            return;
        }
        TNTPrimed tnt = (TNTPrimed) event.getRightClicked();
        Player player = event.getPlayer();
        DefuseAttempt da = new DefuseAttempt(player, tnt);
        player.openInventory(da.getInventory());
        am.addAttempt(da);
    }

    @EventHandler
    public void PlayerClickItem(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        DefuseAttempt da = am.getAttemptFromPlayer(player);
        if(da == null) {
            return;
        }
        if(!da.getInventory().getViewers().contains(player)) {
            return;
        }
        if(event.getSlot() == da.getDefuseSlot()) {
            da.getTNT().remove();
            am.removeAttempt(da);
            da.close();
            player.sendMessage(ChatColor.GREEN + "Phew! TNT defused with only " + ChatColor.GOLD +
                    da.getTNT().getFuseTicks()/20 + ChatColor.GREEN + " seconds to go!");
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void EntityExplode(EntityExplodeEvent event) {
        if(!(event.getEntity() instanceof TNTPrimed)) {
            return;
        }
        List<DefuseAttempt> attempts = am.getAttemptsFromTNT((TNTPrimed) event.getEntity());
        if(attempts == null) {
            return;
        }
        for(DefuseAttempt da : attempts) {
            da.close();
            am.removeAttempt(da);
        }
    }



}
