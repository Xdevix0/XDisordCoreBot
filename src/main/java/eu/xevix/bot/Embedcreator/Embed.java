package eu.xevix.bot.Embedcreator;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.MessageEmbed;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.jetbrains.annotations.NotNull;

import java.awt.*;


public class Embed extends ListenerAdapter {
    public static String embedchannelerror = "Ta komenda może być używana tylko na kanałach tekstowych!";
    public static Color embedcolor = Color.green;
    public static String embedfooter = "Mc/dc developers";
    public static String embedsendmessage = "Embed zostal wyslany na ";
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getChannelType().equals(ChannelType.TEXT)) {
            event.reply(embedchannelerror).setEphemeral(true).queue();
            return;
        }

        if (event.getName().equals("embed")) {

            String title = event.getOption("title").getAsString();
            String content = event.getOption("tresc").getAsString();
            String channelName = event.getOption("kanal").getAsString();
            String footer = event.getOption("fother").getAsString();

            MessageEmbed embed = new EmbedBuilder()
                    .setTitle(title)
                    .setDescription(content)
                    .setColor(embedcolor)
                    .setFooter(embedfooter)
                    .build();

            event.getGuild().getTextChannelsByName(channelName, true)
                    .forEach(channel -> channel.sendMessageEmbeds(embed).queue());

            event.reply(embedsendmessage + channelName + "!").setEphemeral(true).queue();
        }
    }

}

