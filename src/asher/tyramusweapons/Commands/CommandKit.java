package asher.tyramusweapons.Commands;

import asher.tyramusweapons.Main.AsherItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if (sender instanceof Player){

            Player player = (Player) sender;
            AsherItems items = new AsherItems();

            if(args.length >= 1) {
                if(args[0].equalsIgnoreCase("asher")){
                    if(player.getName().equals("AsherWasHere")){
                        player.sendMessage("Added Iterum to your inventory");
                        player.getInventory().addItem(items.getTwinBlade());
                    }
                    else{
                        player.sendMessage("Only Asher can use this command!");
                    }
                }
                if(args[0].equalsIgnoreCase("ellie")){
                    if(player.getName().equals("AsherWasHere")){
                        player.sendMessage("Added Nightblade to your inventory");
                        player.getInventory().addItem(items.getKatana());
                    }
                }
                else{
                    player.sendMessage("Invalid Command");
                }
                if(args[0].equalsIgnoreCase("oliver")){
                    if(player.getName().equals("AsherWasHere")){
                        player.sendMessage("Added Avalanche to your inventory");
                        player.getInventory().addItem(items.getEarthAxe());
                    }
                }
                else{
                    player.sendMessage("Invalid Command");
                }
                if(args[0].equalsIgnoreCase("shield")){
                    if(player.getName().equals("AsherWasHere")){
                        player.sendMessage("Added Shield to your inventory");
                        player.getInventory().addItem(items.getArtifactShield());
                    }
                }
                else{
                    player.sendMessage("Invalid Command");
                }
            }




        }

        return true;

    }







}
