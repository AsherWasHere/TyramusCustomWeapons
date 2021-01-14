package asher.tyramusweapons.Listeners;

import asher.tyramusweapons.Main.AsherItems;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class KatanaUpdate implements Listener {

    public AsherItems items = new AsherItems();

    @EventHandler
    public void onKatanaSwap(PlayerSwapHandItemsEvent event){
        Player player = event.getPlayer();

        if(event.getOffHandItem().isSimilar(items.getKatana())){
            event.setMainHandItem(items.getKatana());
            event.setOffHandItem(new ItemStack(Material.AIR));
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.5F, 1.5F);
        }
    }



}
