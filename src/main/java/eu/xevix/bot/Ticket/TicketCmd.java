package eu.xevix.bot.Ticket;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.managers.Manager;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketCmd extends ListenerAdapter {
    public  static int ticket = 0;
    public static String openname = "null";

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("ticketpanel")) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Potrzebujesz pomocy? A może chcesz coś zamówić?");
            eb.setDescription("Wystarczy że utworzysz ticket używając przycisku znajdującego się poniżej!\n" +
                    "\n" +
                    "UWAGA! Prosimy o nie pingowanie administracji, ponieważ posiadamy życie prywatne, a tworzenie ticketów 4FUN będzie karane tymczasowym banem.");
            eb.setColor(14750168);
            List<Button> buttons = new ArrayList<>();
            buttons.add(Button.success("kupno", "Buy oferts 💲"));
            //buttons.add(Button.danger("blad", "Zglos blad \uD83D\uDD34"));
            buttons.add(Button.danger("Oferty", "Oferts"));
            //buttons.add(Button.success("dozarzadu", "Do zarzadu \uD83D\uDC51"));
            buttons.add(Button.secondary("pytanie", "Queastion ❓"));
            //buttons.add(Button.danger("skarga", "Skarga\uD83D\uDEAB"));

            event.replyEmbeds(eb.build()).addActionRow(buttons).queue();


        }
    }


    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {


        if (event.getComponentId().equals("kupno")) {
            Member member = event.getMember();
            String user = member.getUser().getName();
            Member author = event.getMember();
            String userId = event.getUser().getId();
            Category category = event.getGuild().getCategoryById("1133058645362495548");
            System.out.println(ticket);
            if (ticket <= 1) {
                openname = event.getInteraction().getUser().getName();

            ticket = ticket + 1;
                category.createTextChannel("buy - " + user).syncPermissionOverrides()
                        .queue(channel -> {

                            channel.createPermissionOverride(member)
                                    .setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND, Permission.USE_APPLICATION_COMMANDS, Permission.MESSAGE_EMBED_LINKS)
                                    .queue();
                            event.reply("Pomyślnie stworzyłeś ticket !!!").setEphemeral(true).queue();
                            EmbedBuilder eb1 = new EmbedBuilder();
                            eb1.setDescription("Kliknij przycisk aby zamknac ticket");
                            eb1.setColor(Color.DARK_GRAY);
                            List<Button> buttonsq = new ArrayList<>();
                            buttonsq.add(Button.success("zamknij", "Zamknij ticket ❌"));
                            channel.sendMessageEmbeds(eb1.build()).setActionRow(buttonsq).queue();
                            //channel.delete().queue();



                        });
            } else {
                event.reply("Juz masz stworzone 2 tickety").setEphemeral(true).queue();

            }

        }
        if (event.getComponentId().equals("zamknij")) {
            TextChannel channel = event.getTextChannel();
            event.reply("Ticket sie zamknie za 10 sekund").queue();
            long millisecondsToWait = 10000; // Convert seconds to milliseconds

            try {
                Thread.sleep(millisecondsToWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String channelname = channel.getName();
            String deleteuser = event.getInteraction().getUser().getName();
            ticket = ticket - 1;
            channel.delete().queue();
            EmbedBuilder ebqz = new EmbedBuilder();
            ebqz.setColor(Color.GREEN);
            ebqz.setTitle("**Ticket logi**");
            ebqz.setDescription("****Ticket: **** " + channelname + "\n" +
                    "****Utworzony przez: **** " + openname + "\n "  +
                    "****Zamkniety przez: **** " + deleteuser);
            Date date = new Date();
            ebqz.setFooter(String.valueOf(date));

            event.getGuild().getTextChannelsByName("ticket-logi", true)
                    .forEach(xchannel -> xchannel.sendMessageEmbeds(ebqz.build()).queue());
        }
        //
        //
        //
        //
        //
        //
        //
        //
        //   Questions
        //
        //
        //
        //
        //
        //

        if (event.getComponentId().equals("pytanie")) {
            Member member = event.getMember();
            String user = member.getUser().getName();
            Member author = event.getMember();
            String userId = event.getUser().getId();
            Category category = event.getGuild().getCategoryById("1133079866288394310");
            System.out.println(ticket);
            if (ticket <= 1) {
                openname = event.getInteraction().getUser().getName();

                ticket = ticket + 1;
                category.createTextChannel("Question - " + user).syncPermissionOverrides()
                        .queue(channel -> {

                            channel.createPermissionOverride(member)
                                    .setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND, Permission.USE_APPLICATION_COMMANDS, Permission.MESSAGE_EMBED_LINKS)
                                    .queue();
                            event.reply("Pomyślnie stworzyłeś ticket !!!").setEphemeral(true).queue();
                            EmbedBuilder eb1 = new EmbedBuilder();
                            eb1.setDescription("Kliknij przycisk aby zamknac ticket");
                            eb1.setColor(Color.GREEN);
                            List<Button> buttonsquestion = new ArrayList<>();
                            buttonsquestion.add(Button.success("zamknijquestions", "Zamknij ticket ❌"));
                            channel.sendMessageEmbeds(eb1.build()).setActionRow(buttonsquestion).queue();
                            //channel.delete().queue();



                        });
            } else {
                event.reply("Juz masz stworzone 2 tickety").setEphemeral(true).queue();

            }

        }
        if (event.getComponentId().equals("zamknijquestions")) {
            TextChannel channel = event.getTextChannel();
            event.reply("Ticket sie zamknie za 1 minute").queue();
            long millisecondsToWait = 60000; // Convert seconds to milliseconds

            try {
                Thread.sleep(millisecondsToWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String channelname = channel.getName();
            String deleteuser = event.getInteraction().getUser().getName();
            ticket = ticket - 1;

            channel.delete().queue();
            EmbedBuilder ebqz = new EmbedBuilder();
            ebqz.setColor(Color.GREEN);
            ebqz.setTitle("**Ticket logi**");
            ebqz.setDescription("****Ticket: **** " + channelname + "\n" +
                    "****Utworzony przez: **** " + openname + "\n "  +
                    "****Zamkniety przez: **** " + deleteuser);
            Date date = new Date();
            ebqz.setFooter(String.valueOf(date));

            event.getGuild().getTextChannelsByName("ticket-logi", true)
                    .forEach(xchannel -> xchannel.sendMessageEmbeds(ebqz.build()).queue());
        }
        if (event.getComponentId().equals("Oferty")){
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Oferts");
            eb.setDescription("oferty");
            eb.setColor(Color.GREEN);

            event.deferReply().addEmbeds(eb.build()).setEphemeral(true).queue();
        }




        }

    }



