package main.java.im.prox.bombdefuse;


import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

import java.util.ArrayList;
import java.util.List;

public class AttemptManager {

    public List<DefuseAttempt> attempts = new ArrayList<DefuseAttempt>();

    public void addAttempt(DefuseAttempt da) {
        if(!attempts.contains(da)) {
            attempts.add(da);
        }
    }

    public void removeAttempt(DefuseAttempt da) {
        if(attempts.contains(da)) {
            attempts.remove(da);
        }
    }

    public DefuseAttempt getAttemptFromPlayer(Player player) {
        for(DefuseAttempt da : attempts) {
            if(da.getPlayer() == player) {
                return da;
            }
        }
        return null;
    }

    public List<DefuseAttempt> getAttemptsFromTNT(TNTPrimed tnt) {
        List<DefuseAttempt> tries = new ArrayList<DefuseAttempt>();
        for(DefuseAttempt da : attempts) {
            if(da.getTNT() == tnt) {
                tries.add(da);
            }
        }
        return tries;
    }


}
