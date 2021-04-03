package com.nzeng8.KiwiBot.Commands;

import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Help extends Command {
    
    public Help() {
        super("Help", "Displays this message", false, true);
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        StringBuilder string = new StringBuilder();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Help").setColor(Color.RED);
        if (args[0].equalsIgnoreCase(this.name)) {
            for (Command command : Command.getCommands()) {
                string.append(command.toString() + "\n");
            }
            eb.setDescription(string);
            channel.sendTyping();
            channel.sendMessage(eb.build()).queue();
        }
    }
}
