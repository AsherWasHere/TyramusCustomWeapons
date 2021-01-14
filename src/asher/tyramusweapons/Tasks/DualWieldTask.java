package asher.tyramusweapons.Tasks;

import asher.tyramusweapons.Main.AsherItems;
import asher.tyramusweapons.Main.WeaponsMain;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.PacketPlayOutAnimation;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DualWieldTask {

    public static ProtocolManager manager = ProtocolLibrary.getProtocolManager();
    public static AsherItems items = new AsherItems();
    public static void dualWield() {
        manager.addPacketListener(new PacketAdapter(WeaponsMain.getInstance(), PacketType.Play.Client.ARM_ANIMATION) {

            @Override
            public void onPacketReceiving(PacketEvent packetEvent) {

                PacketContainer offhand = new PacketContainer(PacketType.Play.Client.ARM_ANIMATION);
                offhand.getHands().write(0, EnumWrappers.Hand.OFF_HAND);

                PacketContainer offhandServer = new PacketContainer(PacketType.Play.Server.ANIMATION);
                offhandServer.getEntityModifier(packetEvent.getPlayer().getWorld()).write(0, packetEvent.getPlayer());
                offhandServer.getIntegers().write(1,3);

                Player player = packetEvent.getPlayer();
                EntityPlayer p = ((CraftPlayer) player).getHandle();

                List<EnumWrappers.Hand> mainhand = new ArrayList<EnumWrappers.Hand>(Collections.singleton(EnumWrappers.Hand.MAIN_HAND));

                if (player.getInventory().getItemInMainHand().equals(items.getMainhandblade()) && packetEvent.getPacket().getHands().getValues().equals(mainhand))
                {
                        BukkitRunnable swingDelay = new BukkitRunnable() {
                            @Override
                            public void run() {
                                p.playerConnection.sendPacket(new PacketPlayOutAnimation(p, 3));
                            }
                        };
                        swingDelay.runTaskLater(WeaponsMain.getInstance(), 5);


                }
            }
        });
    }

}
