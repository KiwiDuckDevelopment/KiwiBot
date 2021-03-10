import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import io.github.cdimascio.dotenv.Dotenv;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        init();
    }

    public static void init() throws LoginException {
        String token = "";
        Dotenv dotenv = Dotenv.load();
        token = dotenv.get("BOT_TOKEN");

        JDABuilder bot = new JDABuilder(AccountType.BOT);
        bot.setToken(token);
        bot.addEventListener(new Main());
        bot.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) {
            return;
        }

        System.out.println(String.format("We received a message from %s: %s",
                event.getAuthor().getName(),
                event.getMessage().getContentDisplay()
                ));
        if (event.getMessage().getContentRaw().equals("!ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }
    }
}
