package Command;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DidIAsk extends ListenerAdapter {

    private final long channelID, authorID;

    public DidIAsk(Long channelID, Long userID) {
        this.channelID = channelID;
        this.authorID = userID;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (event.getChannel().getIdLong() != this.channelID) return;
        MessageChannel channel = event.getChannel();

        if (event.getAuthor().getIdLong() == authorID) {
            channel.sendMessage(String.format("<@%s> Did I ask?", authorID)).queue();
            event.getJDA().removeEventListener(this);
        }
    }

}
