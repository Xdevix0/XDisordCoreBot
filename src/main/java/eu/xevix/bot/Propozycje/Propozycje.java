package eu.xevix.bot.Propozycje;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Propozycje extends ListenerAdapter {
    public static String suggestionschannelid = "1131950474669608980";
    public static String suggestiontitle = "Sugestions";
    public static String suggestionimagelink = "";
    public static Color suggestioncolor = Color.green;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        var id = event.isFromType(ChannelType.TEXT) && event.getTextChannel().getId().equals(suggestionschannelid);



        if (id) {
            if (event.getAuthor().isBot()) return;
            if (!event.getAuthor().isBot()) {
                event.getMessage().delete().queue();
            }

                String message = event.getMessage().getContentRaw();
                String user = event.getAuthor().getAsTag();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle(suggestiontitle);
                embed.setColor(suggestioncolor);
                embed.addField("**Nick** ", "``` "  + user + " ```", false);
                embed.addField("**contents**", "```" + message + "```", false);
                embed.setImage(suggestionimagelink);

                Guild guild = event.getGuild();
                TextChannel channelised = guild.getTextChannelById(suggestionschannelid);
                if (channelised == null) {
                    System.out.println("ID Kanalu jest nie poprawne");
                    return;
                }

            channelised.sendMessageEmbeds(embed.build()).queue(messageSent -> {
                messageSent.addReaction("✅").queue();
                messageSent.addReaction("❌").queue();
            });
        }
    }
}
