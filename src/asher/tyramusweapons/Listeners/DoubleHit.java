package asher.tyramusweapons.Listeners;

import asher.tyramusweapons.Main.AsherItems;
import asher.tyramusweapons.Main.WeaponsMain;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftLivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DoubleHit implements Listener {

    public AsherItems items = new AsherItems();

    @EventHandler
    public void onDualHit(EntityDamageByEntityEvent event) {


        if (event.getDamager() instanceof Player && ((Player) event.getDamager()).getInventory().getItemInMainHand().equals(items.getMainhandblade())) {
            // LivingEntity damaged = (LivingEntity) event.getEntity();
            CraftLivingEntity damaged;
            damaged = (CraftLivingEntity) event.getEntity();
            BukkitRunnable doubleHit = new BukkitRunnable() {
                @Override
                public void run() {
                    double dmg = damaged.getLastDamage();
                    damaged.setInvulnerable(false);
                    damaged.setNoDamageTicks(5);
                    damaged.damage(dmg);
                }
            };
            doubleHit.runTaskLater(WeaponsMain.getInstance(), 5);
        }


    }



}
