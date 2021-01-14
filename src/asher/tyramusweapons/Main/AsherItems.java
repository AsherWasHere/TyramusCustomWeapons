package asher.tyramusweapons.Main;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AsherItems {

    public AsherItems(){

    }



    public ItemStack getTwinBlade() {
        ItemStack twinBlade = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta twinBladeMeta = twinBlade.getItemMeta();

        twinBladeMeta.setDisplayName("§6Iterum, Blade of the Eclipse");
        twinBladeMeta.setUnbreakable(true);
        twinBladeMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        twinBlade.setItemMeta(twinBladeMeta);

        return twinBlade;
    }


    public ItemStack getMainhandblade(){

        ItemStack mainhandblade = new ItemStack(Material.IRON_SWORD);
        ItemMeta mainhandmeta = mainhandblade.getItemMeta();
        mainhandmeta.setDisplayName("§cSolarum, Blade of the Sun");
        mainhandmeta.setUnbreakable(true);
        mainhandmeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        mainhandblade.setItemMeta(mainhandmeta);

        return mainhandblade;
    }


    public ItemStack getOffhandblade(){

        ItemStack offhandblade = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta offhandMeta = offhandblade.getItemMeta();
        offhandMeta.setDisplayName("§bLunaron, Blade of the Moon");
        offhandMeta.setUnbreakable(true);
        offhandMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        offhandblade.setItemMeta(offhandMeta);

        return offhandblade;
    }

    public ItemStack getKatana(){

        ItemStack Katana = new ItemStack(Material.STONE_SWORD);
        ItemMeta KatanaMeta = Katana.getItemMeta();
        KatanaMeta.setUnbreakable(true);
        KatanaMeta.setDisplayName("§6Nightblade");
        KatanaMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 2, true);
        Katana.setItemMeta(KatanaMeta);
        return Katana;

    }

    public ItemStack getEarthAxe(){

        ItemStack earthAxe = new ItemStack(Material.IRON_AXE);
        ItemMeta earthAxeMeta = earthAxe.getItemMeta();
        earthAxeMeta.setUnbreakable(true);
        earthAxeMeta.setDisplayName("§6Avalanche");
        earthAxeMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        earthAxe.setItemMeta(earthAxeMeta);
        return earthAxe;
    }



    public ItemStack getArtifactShield(){

        ItemStack artifactShield = new ItemStack(Material.SHIELD);
        ItemMeta artifactShieldMeta = artifactShield.getItemMeta();
        artifactShieldMeta.setUnbreakable(true);
        artifactShieldMeta.setDisplayName("§6Artifact Shield");
        artifactShield.setItemMeta(artifactShieldMeta);
        return artifactShield;
    }






}
