package main.java.im.prox.bombdefuse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class DefuseAttempt {

    private Player player;
    private Inventory inv;
    private TNTPrimed tnt;
    private int defuseSlot;

    public DefuseAttempt(Player player, TNTPrimed tnt) {
        this.player = player;
        this.tnt = tnt;
        String invName = "" + ChatColor.RED + ChatColor.BOLD + "Defuse attempt!";
        inv = Bukkit.getServer().createInventory(player, 36, invName);
        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, new ItemStack(Material.STRING));
        }
        ItemStack defuseItem = new ItemStack(Material.STRING);
        ItemMeta im = defuseItem.getItemMeta();
        im.setDisplayName("" + ChatColor.GREEN + ChatColor.BOLD + "Cut me!");
        defuseItem.setItemMeta(im);
        defuseSlot = new Random().nextInt(inv.getSize());
        inv.setItem(defuseSlot, defuseItem);
        player.openInventory(inv);
    }


    public int getDefuseSlot() {
        return defuseSlot;
    }

    public Inventory getInventory() {
        return inv;
    }

    public Player getPlayer() {
        return player;
    }

    public TNTPrimed getTNT() {
        return tnt;
    }

    public void close() {
        if(inv.getViewers().contains(player)) {
            player.closeInventory();
        }
    }

}
