package iafenvoy.betterprefix.prefix;

import iafenvoy.betterprefix.utils.TeamCommandUtils;
import iafenvoy.betterprefix.utils.UUIDUtils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PrefixManager {
    public static PrefixManager INSTANCE = new PrefixManager();
    private final List<PPlayer> players = new ArrayList<>();
    private final List<Prefix> prefixes = new ArrayList<>();

    public List<Prefix> getPrefixes() {
        return prefixes;
    }

    @Nullable
    public Prefix getPrefixById(String id) {
        for (Prefix p : prefixes)
            if (p.getId().equals(id))
                return p;
        return null;
    }

    @Nullable
    public PPlayer getPlayerByName(String name) {
        UUID uuid = UUIDUtils.getUuidByName(name);
        return this.getPlayerByUuid(uuid);
    }

    @Nullable
    public PPlayer getPlayerByUuid(UUID uuid) {
        for (PPlayer p : this.players)
            if (p.getUuid().equals(uuid))
                return p;
        return null;
    }

    public boolean addPrefix(String id, String text) {
        for (Prefix p : this.prefixes)
            if (p.getId().equals(id))
                return false;
        Prefix p = new Prefix(id, text);
        this.prefixes.add(p);
        TeamCommandUtils.addPrefix(p);
        return true;
    }

    public boolean removePrefix(String id) {
        Prefix prefix = null;
        for (Prefix p : this.prefixes)
            if (p.getId().equals(id))
                prefix = p;
        if (prefix != null) {
            this.prefixes.remove(prefix);
            TeamCommandUtils.removePrefix(prefix);
        }
        return prefix != null;
    }

    public boolean modifyPrefix(String id, String text) {
        Prefix prefix = null;
        for (Prefix p : this.prefixes)
            if (p.getId().equals(id))
                prefix = p;
        if (prefix != null) {
            prefix.setText(text);
            TeamCommandUtils.modifyPrefix(prefix);
        }
        return prefix != null;
    }

    public void newPlayer(Player player) {
        this.players.add(new PPlayer(player));
    }
}
