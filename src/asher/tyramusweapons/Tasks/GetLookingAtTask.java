package asher.tyramusweapons.Tasks;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class GetLookingAtTask {

    private boolean getLookingAt(Player player, LivingEntity entity){

        Location eye = player.getEyeLocation();
        Vector toEntity = entity.getEyeLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());

        return dot > 0.99D;

    }

    public List<Entity> getEntitys(Player player, int range){
        List<Entity> entitys = new ArrayList<Entity>();
        for(Entity e : player.getNearbyEntities(range, range, range)){
            if(e instanceof LivingEntity){
                if(getLookingAt(player, (LivingEntity) e)){
                    entitys.add(e);
                }
            }
        }
        return entitys;
    }








}
