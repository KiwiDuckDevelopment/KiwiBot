package com.nzeng8.KiwiBot.Commands;

import com.nzeng8.KiwiBot.Bot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Ping extends Command {

    public Ping() {
        super("Ping", "Pings the bot", false, true);
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        if (args[0].equalsIgnoreCase("ping")) {
            channel.sendMessage(String.format("Pong! This Bot is connected with a ping of %sms.", event.getJDA().getGatewayPing())).queue();
        }
    }
}
