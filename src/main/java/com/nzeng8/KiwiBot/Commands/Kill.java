package com.nzeng8.KiwiBot.Commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import com.nzeng8.KiwiBot.Bot;

public class Kill extends Command {

    public Kill() {
        super("Kill", "Kills the bot", false, true);
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        if (args[0].equalsIgnoreCase("kill")) {
            Bot.api.shutdownNow();
            System.exit(0);
        }
    }
    
}
