package asher.tyramusweapons.Listeners;

import asher.tyramusweapons.Main.AsherItems;
import asher.tyramusweapons.Main.ItemNBT;
import asher.tyramusweapons.Main.WeaponsMain;
import asher.tyramusweapons.Tasks.GetLookingAtTask;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;

public class TwinbladeUpdate implements Listener {


    // Basic Variables
    public AsherItems items = new AsherItems();
    public boolean cooldown = true;


    @EventHandler
    public void TwinbladeOfCinderfall(PlayerItemHeldEvent Event) {
    }


    @EventHandler
    public void onTwinBladeSwap(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();

        if (event.getOffHandItem().isSimilar(items.getTwinBlade())) {
            player.playSound(player.getLocation(), Sound.BLOCK_CHAIN_HIT, 1.5F, 1.5F);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.5F, 1.5F);
            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1.5F, 1.5F);
            event.setMainHandItem(items.getMainhandblade());
            event.setOffHandItem(items.getOffhandblade());
            player.setWalkSpeed(player.getWalkSpeed() + 0.15F);
        }

        if (event.getOffHandItem().isSimilar(items.getMainhandblade())) {
            event.setMainHandItem(items.getTwinBlade());
            event.setOffHandItem(new ItemStack(Material.AIR));
            player.setWalkSpeed(player.getWalkSpeed() - 0.15F);
        }
    }

    @EventHandler
    public void TwinBladeItemScroll(PlayerItemHeldEvent event) {

        Player player = event.getPlayer();

        if (Objects.equals(player.getInventory().getItem(event.getNewSlot()), items.getMainhandblade())) {
            player.getInventory().setItemInOffHand(items.getOffhandblade());
            player.setWalkSpeed(player.getWalkSpeed() + 0.15F);
        } else if (player.getInventory().getItemInOffHand().equals(items.getOffhandblade())) {

            player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            player.setWalkSpeed(player.getWalkSpeed() - 0.15F);
        }
    }


    @EventHandler
    public void TwinBladeThrow(PlayerDropItemEvent event) {

        Player player = event.getPlayer();

        if (event.getItemDrop().getItemStack().equals(items.getMainhandblade())) {

            player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));

        }

    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();

        GetLookingAtTask check = new GetLookingAtTask();
        LivingEntity theTarget;

        Location origin = player.getEyeLocation();
        Vector direction = origin.getDirection();
        direction.multiply(3);
        Location destination = origin.clone().add(direction);
        double a = destination.getY();
        a = destination.getY() - 0.5;
        destination.setY(a);

        if (items.getTwinBlade().isSimilar(event.getItem()) && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) && event.getHand() != EquipmentSlot.OFF_HAND) {


            // add in a cooldown
            world.spawnParticle(Particle.SWEEP_ATTACK, destination, 0);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.75f, 1.0f);


        }

        if (items.getMainhandblade().isSimilar(event.getItem()) && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) && event.getHand() != EquipmentSlot.OFF_HAND) {


            // add in a cooldown
            world.spawnParticle(Particle.SWEEP_ATTACK, destination, 0);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.75f, 1.0f);


        }


    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        World world = player.getWorld();
        double x = player.getLocation().getDirection().getX();
        double y = player.getLocation().getDirection().getY();
        double z = player.getLocation().getDirection().getZ();
        Vector pos = new Vector(x, y, z);

        BukkitRunnable cd = new BukkitRunnable() {
            @Override
            public void run() {
                cooldown = true;
                player.sendMessage("§aLeap Ability Ready Now!");
            }
        };

        if (items.getMainhandblade().isSimilar(event.getItem()) && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.getHand() != EquipmentSlot.OFF_HAND){
            if(cooldown){
                player.playSound(player.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.75f, 5.0f); // Sound



                world.spawnParticle(Particle.FLAME, player.getLocation(), 10); // Particle
                world.spawnParticle(Particle.FLAME, player.getLocation(), 10);
                world.spawnParticle(Particle.FLAME, player.getLocation(), 10);
                world.spawnParticle(Particle.FLAME, player.getLocation(), 10);

                // Action of the Spell

                pos.setX(pos.getX() * 3);
                pos.setZ(pos.getZ() * 3);
                player.setVelocity(pos);
                cooldown = false;
                cd.runTaskLater(WeaponsMain.getInstance(), 600);

            }
            else{
                player.sendMessage("§cThat Ability is on Cooldown!");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 0.5f); // Sound
            }
        }

    }




}






