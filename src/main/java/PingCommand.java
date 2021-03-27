import com.github.kaktushose.jda.commands.annotations.Command;
import com.github.kaktushose.jda.commands.annotations.CommandController;
import com.github.kaktushose.jda.commands.entities.CommandEvent;

@CommandController
public class PingCommand {

    @Command("ping")
    public void onPing(CommandEvent event) {
        event.reply("Pong!");
    }
}
