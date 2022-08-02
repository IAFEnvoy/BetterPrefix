package iafenvoy.betterprefix.prefix;

import iafenvoy.betterprefix.utils.UUIDUtils;
import iafenvoy.betterprefix.utils.exceptions.PlayerNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Prefix {
    private final String id;
    private String text;
    private final List<UUID> admins = new ArrayList<>();

    public Prefix(String id, String text, UUID... playerUUIDs) {
        this.id = id;
        this.text = text;
        this.admins.addAll(Arrays.asList(playerUUIDs));
    }

    public Prefix(String id, String text, String... playerNames) {
        this.id = id;
        this.text = text;
        for (String name : playerNames) {
            try {
                this.admins.add(UUIDUtils.getUuidByName(name));
            } catch (PlayerNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Prefix(String id, String text) {
        this(id,text,new UUID[]{});
    }

    public String getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public List<UUID> getAdmins() {
        return admins;
    }

    public boolean isAdmin(UUID uuid){
        return this.admins.contains(uuid);
    }

    public void addAdmin(UUID uuid){
        this.admins.add(uuid);
    }

    public void removeAdmin(UUID uuid){
        this.admins.remove(uuid);
    }

    public void setText(String text) {
        this.text = text;
    }
}
