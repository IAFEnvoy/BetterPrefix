package iafenvoy.betterprefix.prefix;

import iafenvoy.betterprefix.utils.TeamCommandUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PPlayer {
    private final List<Prefix> canUse;
    private Prefix nowUse;
    private final Player player;

    public PPlayer(Player player, List<Prefix> canUse, Prefix nowUse) {
        this.player = player;
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

    public void setNowUse(Prefix nowUse) {
        TeamCommandUtils.removePlayerPrefix(this.player.getName());
        this.nowUse = nowUse;
        if (this.nowUse != null)
            TeamCommandUtils.setPlayerPrefix(this.nowUse, this.player.getName());
    }

    public void addCanUse(Prefix p) {
        this.canUse.add(p);
    }

    public void removeCanUse(Prefix p) {
        if (this.canUse.contains(p)) {
            this.canUse.remove(p);
            if (nowUse == p) nowUse = null;
        }
    }

    public boolean isOp() {
        return this.player.isOp();
    }
}
