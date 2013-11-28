package main.java.im.prox.bombdefuse;


import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BombDefuse extends JavaPlugin {

    public static BombDefuse plugin;
    public static AttemptManager am;

    @Override
    public void onEnable() {
        plugin = this;
        am = new AttemptManager();
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new DefuseListener(this), this);
    }

    @Override
    public void onDisable() {

    }

    public AttemptManager getAttemptManager() {
        return am;
    }

}
