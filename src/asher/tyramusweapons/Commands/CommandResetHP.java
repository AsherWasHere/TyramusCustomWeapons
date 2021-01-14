package asher.tyramusweapons.Commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandResetHP implements CommandExecutor {


    @Override

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(sender instanceof Player){
            Player player = (Player) sender;

            if (args.length >= 0){

                player.setHealthScale(20);
                player.setWalkSpeed(0.2F);

            }
        }
            return true;

    }




}
