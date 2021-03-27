import com.github.kaktushose.jda.commands.entities.JDACommandsBuilder;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {

    private static final String BOT_TOKEN = Dotenv.load().get("BOT_TOKEN");

    public static String prefix = "!";

    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA api = JDABuilder.createDefault(BOT_TOKEN)
                .addEventListeners(
                        new Bot()
                )
                .build()
                .awaitReady();
        JDACommandsBuilder.start(api, prefix, true, true, true);
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
//        if (content.equals("!ping")) {
//            MessageChannel channel = event.getChannel();
//            channel.sendMessage("Pong!").queue();
//        }
        if (content.contains("duc")) {
            event.getChannel().sendMessage("quac").queue();
        }
    }
}

