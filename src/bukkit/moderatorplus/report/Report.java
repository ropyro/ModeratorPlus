package bukkit.moderatorplus.report;

import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Report {

    private final Player reporter;
    private final Player target;
    private final String reason;
    private final String time;
    private final String ID;

    private boolean handled = false;

    public Report(Player reporter, Player target, String reason){
        this.reporter = reporter;
        this.target = target;
        this.reason = reason;
        this.time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        this.ID = Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 2);
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public Player getReporter() {
        return reporter;
    }

    public Player getTarget() {
        return target;
    }

    public String getReason(){
        return reason;
    }

    public String getTime(){
        return time;
    }

    public String getID(){
        return ID;
    }
}
