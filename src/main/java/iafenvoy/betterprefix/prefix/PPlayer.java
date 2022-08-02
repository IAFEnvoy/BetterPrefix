package iafenvoy.betterprefix.prefix;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PPlayer{
    private List<Prefix> canUse;
    private Prefix nowUse;
    private Player player;

    public PPlayer(Player player, List<Prefix> canUse, Prefix nowUse) {
        this.player=player;
        this.canUse = canUse;
        this.nowUse = nowUse;
    }

    public PPlayer(Player player, List<Prefix> canUse) {
        this(player, canUse, null);
    }

    public PPlayer(Player player) {
        this(player, new ArrayList<>());
    }

    public UUID getUuid() {
        return this.player.getUniqueId();
    }

    public List<Prefix> getCanUse() {
        return canUse;
    }

    public Prefix getNowUse() {
        return nowUse;
    }

    public void setNowUse(Prefix nowUse) {
        this.nowUse = nowUse;
    }

    public void addCanUse(Prefix p) {
        this.canUse.add(p);
    }

    public boolean removeCanUse(Prefix p) {
        if (this.canUse.contains(p)) {
            this.canUse.remove(p);
            if (nowUse == p) nowUse = null;
            return true;
        }
        return false;
    }

    public boolean isOp(){
        return this.player.isOp();
    }
}
