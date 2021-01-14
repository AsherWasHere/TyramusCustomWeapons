package asher.tyramusweapons.Listeners;

import asher.tyramusweapons.Main.AsherItems;
import asher.tyramusweapons.Main.WeaponsMain;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class AvalancheUpdate implements Listener {

    public AsherItems items = new AsherItems();
    public Boolean cooldown = true;
    @EventHandler
    public void onAvalancheSwap(PlayerSwapHandItemsEvent event){

        Player player = event.getPlayer();

        if(event.getOffHandItem().isSimilar(items.getEarthAxe())){


            event.setMainHandItem(items.getEarthAxe());
            event.setOffHandItem(new ItemStack(Material.AIR));


            // Find where the player is looking
            // Shoot a projectile in that direction


            // extra definitions
            Vector direction = player.getLocation().getDirection();
            double x = player.getLocation().getDirection().getX();
            double y = player.getLocation().getDirection().getY();
            double z = player.getLocation().getDirection().getZ();
            Vector pos = new Vector(x, y, z);
            World world = player.getWorld();
            int i = 0;
            BukkitRunnable cd2 = new BukkitRunnable() {
                @Override
                public void run() {
                    cooldown = true;
                    player.sendMessage("§aSmash Ability Ready Now!");
                }
            };

            BukkitRunnable spam = new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendMessage("§cThat ability is on cooldown!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.5F, 1.5F);
                }
            };

            if(cooldown){
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.5F, 1.5F);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.5F, 1.5F);
                for(Entity e : player.getNearbyEntities(5, 5, 5)){
                    if(e instanceof LivingEntity){
                        LivingEntity le = (LivingEntity) e;
                        le.damage(1);
                        Vector epos = new Vector(le.getLocation().getDirection().getX(), le.getLocation().getDirection().getY(), le.getLocation().getDirection().getZ());
                        epos.setY(epos.getY() + 1);

                        le.setVelocity(epos);

                        // world.strikeLightningEffect(le.getLocation());

                    }
                }

                // Particles



                // Start the cooldown
                cooldown = false;
                // runnable to reset the cooldown after 30 seconds
                cd2.runTaskLater(WeaponsMain.getInstance(), 600);
            }
            else{
                // If you're spamming the button, only run the Runnable once every 1 second.
                spam.runTask(WeaponsMain.getInstance());
            }



        }

    }


}
