package eu.xevix.bot.Ankiety;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Ankietycmd extends ListenerAdapter {
    public static String questionchannelerror = "Ta komenda może być używana tylko na kanałach tekstowych!";
    public static String questiontitle = "**Questionnaire**";
    public static Color questioncolor = Color.GREEN;
    public static String questionfooter = "Mc/dc developers";
    public static String questionchannel = "chat";
    public static String questionsendmessage = "Wiadomosc zostala wyslana";

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("ankieta")) {
            if (!event.getChannelType().equals(ChannelType.TEXT)) {
                event.reply(questionchannelerror).setEphemeral(true).queue();
                return;
            }
            String content = event.getOption("tresc").getAsString();
            MessageEmbed embed = new EmbedBuilder()
                    .setTitle(questiontitle)
                    .setDescription(content)
                    .setColor(questioncolor)
                    .setFooter(questionfooter)
                    .build();
            event.getGuild().getTextChannelsByName(questionchannel, true)
                    .forEach(channel -> channel.sendMessageEmbeds(embed).queue(messageSent -> {
                        messageSent.addReaction("✅").queue();
                        messageSent.addReaction("❌").queue();
                    }));
            event.reply(questionsendmessage).setEphemeral(true).queue();
        }

    }
}


