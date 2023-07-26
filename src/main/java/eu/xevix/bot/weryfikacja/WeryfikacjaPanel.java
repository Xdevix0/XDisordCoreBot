package eu.xevix.bot.weryfikacja;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeryfikacjaPanel extends ListenerAdapter {
    private static final String VERIFICATION_ROLE_ID = "1113481044222558261";
    private static final String MEMBER_TO_VERIFY_ID = "1121505653677113414";

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("weryfikacja")) {
            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("**Panel weryfikacji**");
            eb.setColor(993399);
            eb.setDescription("Nacisnij odpowiedni przycisk ktory odpowiada twojemj plci");
            eb.setFooter("MoodRp - weryfikacja");
            List<Button> buttons = new ArrayList<>();
            buttons.add(Button.danger("kobieta", "Kobieta"));
            buttons.add(Button.success("chlopak", "Chlopak"));
            event.replyEmbeds(eb.build()).addActionRow(buttons).queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        var verification = "1131575857702510683";
        if (event.getComponentId().equals("kobieta")) {
            Member member = event.getMember();

                event.getGuild().addRoleToMember(member, event.getGuild().getRoleById("1131575954397986859")).queue();
                event.getGuild().addRoleToMember(member, event.getGuild().getRoleById(verification)).queue();


            event.reply("Nadano range obywatelka").setEphemeral(true).queue();
        }
        Member member = event.getMember();
        if (event.getComponentId().equals("chlopak")) {
                event.getGuild().addRoleToMember(member, event.getGuild().getRoleById("1131575955480125480")).queue();
                event.getGuild().addRoleToMember(member, event.getGuild().getRoleById(verification)).queue();
            event.reply("Nadano range obywatel").setEphemeral(true).queue();
        }
    }
}
