package bukkit.moderatorplus.api;

import org.bukkit.entity.Player;

public class PermissionManager {

    public boolean mayUse(Player p, Feature feature){
        switch(feature){
            case STAFFCHAT:
                if(p.hasPermission("moderator.staffchat")) return true;
            case PLAYERINFO:
                if(p.hasPermission("moderator.playerinfo")) return true;
        }
        return false;
    }

    public enum Feature{
        STAFFCHAT, PLAYERINFO;
    }
}
