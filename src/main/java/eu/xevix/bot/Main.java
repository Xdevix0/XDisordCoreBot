package eu.xevix.bot;

import eu.xevix.bot.Ankiety.Ankietycmd;
import eu.xevix.bot.Embedcreator.Embed;
import eu.xevix.bot.Propozycje.Propozycje;
import eu.xevix.bot.Ticket.TicketCmd;
import eu.xevix.bot.joinmessage.JoinMessevent;
import eu.xevix.bot.tabembed.Tabembed;
import eu.xevix.bot.weryfikacja.WeryfikacjaPanel;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class Main extends ListenerAdapter {

    public static JDA jda;

    public static void main(String[] args) throws LoginException {
        Scanner sc = new Scanner(System.in);

        //String token = "MTEzMTYwMzM5NDI5MzkzNjMwMA.GKYVGD.XYzUkoaUdrxYnZWWUupjEd6J60Z0GXHqEnlvC4";
        String token = "MTEzMzAzNTY4OTM4NzIzMzMxMA.G0eMYm.1F4nZyLfxuAPqxWxC2PCArVPRbWiFQdvwXG19w";

        jda = JDABuilder.createDefault(token)
                .addEventListeners(new Embed())
                .addEventListeners(new TicketCmd())
               // .addEventListeners(new banalll())
                .addEventListeners(new Propozycje())
                .addEventListeners(new Ankietycmd())
                .addEventListeners(new WeryfikacjaPanel())
                .addEventListeners(new JoinMessevent())
                .addEventListeners(new Tabembed())
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGE_REACTIONS)
                .setChunkingFilter(ChunkingFilter.ALL)
                .build();


        CommandListUpdateAction commands = jda.updateCommands();


        String guildId = "1113729533019951164";
        commands.addCommands(
                Commands.slash("embed", "Kreator embeda")
                        .addOption(OptionType.STRING, "title", "Tytuł embeda", true)
                        .addOption(OptionType.STRING, "tresc", "Treść embeda", true)
                        .addOption(OptionType.STRING, "kanal", "Nazwa kanału", true)
                        .addOption(OptionType.STRING, "fother", "Tekst w stopce", false)
                        .setDefaultEnabled(true)


        );
        commands.addCommands(
                Commands.slash("ticketpanel", "Komenda do tworzenia panelu ")
        );
        commands.addCommands(
                Commands.slash("tabembed", "Kreator embeda z tabulatorami")
        );
        commands.addCommands(
                Commands.slash("ping", "wysyla wiadomosc na pv")
        );
        commands.addCommands(
          Commands.slash("weryfikacja", "Wysyla panel weryfikacji")
        );
        commands.addCommands(
                Commands.slash("ankieta", "Wstawia ankiete")
                        .addOption(OptionType.STRING, "tresc", "Treść embeda", true)
                        .setDefaultEnabled(true)
        );
        commands.queue();

    }
}
