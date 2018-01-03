package bukkit.moderatorplus.api;

import org.bukkit.entity.Player;

public class PermissionManager {

    public boolean mayUse(Player p, Feature feature){
        switch(feature){
            case STAFFCHAT:
                if(p.hasPermission("moderatorplus.staffchat")) return true;
            case PLAYERINFO:
                if(p.hasPermission("moderatorplus.playerinfo")) return true;
            case ACTIVEREPORTS:
                if(p.hasPermission("moderatorplus.activereports")) return true;
        }
        return false;
    }

    public enum Feature{
        STAFFCHAT, PLAYERINFO, ACTIVEREPORTS;
    }
}
