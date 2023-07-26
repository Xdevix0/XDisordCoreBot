package eu.xevix.bot.joinmessage;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static eu.xevix.bot.Main.jda;

public class JoinMessevent extends ListenerAdapter {

    public static String joinserverchanelname = "│・\uD83D\uDE80・przyjazdy";
    public static Color joinservercolor = Color.GREEN;
    public static String joinserverfooter = "Mc/dc developers";

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        String member = event.getMember().getUser().getAsTag();
        String channelName = joinserverchanelname;
        int members = event.getGuild().getMemberCount();
        EmbedBuilder eb = new EmbedBuilder();
                eb.setDescription("Czesc " +  member + " dziekujemy ci ze dolaczyles do naszej\n" +
                        " spolecznosci MountRp. Mamy nadzieje ze szybko \n" +
                        "nie odlecisz od od teraz jestes** " + members + " **obywatelem.");
                eb.setImage("https://media.discordapp.net/attachments/1132255150598279250/1132269530308157490/WITAMY.png");
                eb.setColor(joinservercolor);
                eb.setFooter(joinserverfooter);
                eb.build();
        event.getGuild().getTextChannelsByName(channelName, true)
                .forEach(channel -> channel.sendMessageEmbeds(eb.build()).queue());
    }
}
