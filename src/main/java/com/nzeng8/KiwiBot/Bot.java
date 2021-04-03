package com.nzeng8.KiwiBot;

import com.nzeng8.KiwiBot.Commands.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {

    private static final String BOT_TOKEN = Dotenv.load().get("BOT_TOKEN");

    public static String prefix = "i!";

    public static JDA api;

    public static void main(String[] args) throws LoginException, InterruptedException {
        api = JDABuilder.createDefault(BOT_TOKEN)
                .addEventListeners(
                        new Bot(),
                        new Kill(),
                        new Ping(),
                        new Help()
                        // new DidIAsk(827994722752135198L, 468131208996585472L)
                )
                .build()
                .awaitReady();
        api.getPresence().setActivity(Activity.watching("Duccs going q u a c c"));
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        if (content.contains("duc")) {
            event.getChannel().sendMessage("quac").queue();
        }
//        event.getJDA().addEventListener(new DidIAsk(612059384721440791L, 320369001005842435L));
    }
}

