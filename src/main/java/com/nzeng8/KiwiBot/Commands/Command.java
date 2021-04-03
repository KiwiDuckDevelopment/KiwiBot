package com.nzeng8.KiwiBot.Commands;

import java.util.ArrayList;
import java.util.List;

import com.nzeng8.KiwiBot.Bot;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class Command extends ListenerAdapter {

    public static final String prefix = "i!";
    protected String name, description;
    protected String[] args;
    protected MessageChannel channel;
    protected String content;
    protected Boolean isBot;
    protected Boolean enabled;
    protected final Boolean alwaysEnabled;

    private static List<Command> commands = new ArrayList<Command>();

    public Command(String name, String description, Boolean isBot, final Boolean alwaysEnabled) {
        this.name = name;
        this.description = description;
        this.isBot = isBot;
        this.alwaysEnabled = alwaysEnabled;
        commands.add(this);
    }
    
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (alwaysEnabled) enabled = true;
        if (!enabled) return;
        if (!event.getMessage().getContentRaw().substring(0, 2).equals(prefix)) return;
        if (!event.getAuthor().isBot() && this.isBot) return;
        content = event.getMessage().getContentRaw().substring(prefix.length());
        args = content.split(" ");
        if (!args[0].equalsIgnoreCase(this.name)) return;
        channel = event.getChannel();
    }


    @Override
    public String toString() {
        return String.format("[%s] %s: %s", (this.enabled ? "Enabled" : "Disabled"), this.name, this.description);
    }

    protected String getName() {
        return this.name;
    }

    protected String getDescription() {
        return this.description;
    }

    public static List<Command> getCommands() {
        return commands;
    }

    public static void enable(Command... commands) {
        for (Command command : commands) {
            Bot.api.addEventListener(command);
        }
    }

    protected Boolean isEnabled() {
        return this.enabled;
    }

    protected void toggle() {
        enabled = !enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    protected void onEnable() {
        try {
            Bot.api.addEventListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDisable() {
        try {
            Bot.api.removeEventListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
