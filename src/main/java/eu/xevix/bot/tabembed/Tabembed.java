package eu.xevix.bot.tabembed;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;

public class Tabembed extends ListenerAdapter {
    public static String tabembeddescription = "\uD83C\uDDF5\uD83C\uDDF1   Regulamin\n" +
            "\n" +
            "^Zasady discord^\n" +
            "1.1 Zakazuje się wyzywania serwera\n" +
            "1.2 Zakaz jest promowania własnych usług, serwerów itp.\n" +
            "1.3 Zakaz jest sprzedawania własnych usług bez zgody \n" +
            "1.4 Aby zakupić trzeba stworzyć ticket oraz poczekać na odpowiedz, zakazuje się spamowania\n" +
            "\n" +
            "^Zasady Sklepu^\n" +
            "2.1 Wszystkie płatności za daną prace nie podlegają zwrotowi\n" +
            "2.2 Płaci się dopiero po wykonanej pracy lub w połowie przed i resztę po\n" +
            "2.3 Zakazuje się próby oszustwa\n" +
            "2.4 Wszystkie płatności są dobrowolne nikogo nie zmuszamy do kupowania\n" +
            "\n" +
            "\uD83C\uDDEC\uD83C\uDDE7  Statute\n" +
            "\n" +
            "^discord rudiments^\n" +
            "1.1 It is forbidden to challenge the office\n" +
            "1.2 Prohibition is the promotion of services, servers, etc.\n" +
            "1.3 Prohibition is the sale of services provided without consent\n" +
            "1.4 To install the ticket and support for the answer, it is forbidden to spam\n" +
            "\n" +
            "^Shop rudiments^\n" +
            "2.1 All payments are non-refundable\n" +
            "2.2 You transfer the salary after the work is done or half now and the other half later\n" +
            "2.3 Fraud is prohibited\n" +
            "2.4 All payments are not mandatory, we do not force you to buy";
    public static Color tabembedcolor = Color.GREEN;
    public static String foother = "Mc/Dc developers";

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getChannelType().equals(ChannelType.TEXT)) {
            event.reply("Ta komenda może być używana tylko na kanałach tekstowych!").setEphemeral(true).queue();
            return;
        }

        if (event.getName().equals("tabembed")) {


            MessageEmbed embed = new EmbedBuilder()
                    .setTitle("**REGULAMIN DISCORD**")
                    .setDescription(tabembeddescription)
                    .setColor(tabembedcolor)
                    .setFooter(foother)
                    .build();
            String channelName = event.getTextChannel().getName();

            event.getGuild().getTextChannelsByName(channelName, true)
                    .forEach(channel -> channel.sendMessageEmbeds(embed).queue());

            event.reply("Embed został wysłany na kanał " + channelName + "!").setEphemeral(true).queue();
        }
    }
}
