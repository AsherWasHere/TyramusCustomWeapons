package asher.tyramusweapons.Main;

import asher.tyramusweapons.Commands.CommandKit;
import asher.tyramusweapons.Commands.CommandResetHP;
import asher.tyramusweapons.Listeners.AvalancheUpdate;
import asher.tyramusweapons.Listeners.DoubleHit;
import asher.tyramusweapons.Listeners.KatanaUpdate;
import asher.tyramusweapons.Listeners.TwinbladeUpdate;
import asher.tyramusweapons.Tasks.DualWieldTask;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

public class WeaponsMain extends JavaPlugin {

    private static WeaponsMain instance;

    public static WeaponsMain getInstance(){ return instance;}

    @Override
    public void onEnable() {
        instance = this;


        // Registered Listeners
        getServer().getPluginManager().registerEvents(new TwinbladeUpdate(), this);
        getServer().getPluginManager().registerEvents(new KatanaUpdate(), this);
        getServer().getPluginManager().registerEvents(new AvalancheUpdate(), this);
        getServer().getPluginManager().registerEvents(new DoubleHit(), this);
        // Registered Commands
        this.getCommand("kit").setExecutor(new CommandKit());
        this.getCommand("resethp").setExecutor(new CommandResetHP());



        // Tasks to Run
        DualWieldTask.dualWield();

    }

    @Override
    public void onDisable(){ instance = null;}



}
