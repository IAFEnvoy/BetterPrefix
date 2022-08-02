package iafenvoy.betterprefix.prefix;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PPlayer {
    private UUID uuid;
    private List<Prefix> canUse;
    private Prefix nowUse;

    public PPlayer(UUID uuid, List<Prefix> canUse, Prefix nowUse) {
        this.uuid = uuid;
        this.canUse = canUse;
        this.nowUse = nowUse;
    }

    public PPlayer(UUID uuid, List<Prefix> canUse) {
        this(uuid, canUse, null);
    }

    public PPlayer(UUID uuid) {
        this(uuid, new ArrayList<>());
    }

    public UUID getUuid() {
        return this.uuid;
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
}
