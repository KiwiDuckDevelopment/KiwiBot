package com.nzeng8.KiwiBot.Commands;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class DidIAsk extends Command {

    private final long channelID, authorID;

    public DidIAsk(Long channelID, Long userID) {
        super("Did I Ask", "Annoy people who spam 'Did I Ask'", false, false);
        this.channelID = channelID;
        this.authorID = userID;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        if (event.getAuthor().isBot()) return;
        if (event.getChannel().getIdLong() != this.channelID) return;
        MessageChannel channel = event.getChannel();

        if (event.getAuthor().getIdLong() == authorID) {
            channel.sendTyping().queue();
            channel.sendMessage(String.format("<@%s> Did I ask?", authorID)).queue();
            event.getJDA().removeEventListener(this);
        }
    }

}
