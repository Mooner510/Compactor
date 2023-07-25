package org.mooner.compactor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CompactorAlgorithm {
    public static boolean compact(Inventory inventory) {
        int coal = 0, diamond = 0, emerald = 0, iron = 0, gold = 0, copper = 0, redstone = 0, lapis = 0, netherite = 0, quatz = 0;
        for (ItemStack itemStack : inventory) {
            if(itemStack == null) continue;
            switch (itemStack.getType()) {
                case COAL -> coal += itemStack.getAmount();
                case DIAMOND -> diamond += itemStack.getAmount();
                case EMERALD -> emerald += itemStack.getAmount();
                case REDSTONE -> redstone += itemStack.getAmount();
                case IRON_INGOT -> iron += itemStack.getAmount();
                case GOLD_INGOT -> gold += itemStack.getAmount();
                case COPPER_INGOT -> copper += itemStack.getAmount();
                case LAPIS_LAZULI -> lapis += itemStack.getAmount();
                case NETHERITE_INGOT -> netherite += itemStack.getAmount();
                case QUARTZ -> quatz += itemStack.getAmount();
                default -> {
                    continue;
                }
            }
            itemStack.setAmount(0);
        }

        if(coal == 0 && diamond == 0 && emerald == 0 && iron == 0 && gold == 0 && copper == 0 && redstone == 0 && lapis == 0 && netherite == 0) {
            return false;
        }

        addItem(inventory, Material.COAL_BLOCK, coal / 9);
        addItem(inventory, Material.DIAMOND_BLOCK, diamond / 9);
        addItem(inventory, Material.EMERALD_BLOCK, emerald / 9);
        addItem(inventory, Material.REDSTONE_BLOCK, redstone / 9);
        addItem(inventory, Material.GOLD_BLOCK, gold / 9);
        addItem(inventory, Material.IRON_BLOCK, iron / 9);
        addItem(inventory, Material.COPPER_BLOCK, copper / 9);
        addItem(inventory, Material.NETHERITE_BLOCK, netherite / 9);
        addItem(inventory, Material.LAPIS_BLOCK, lapis / 9);
        addItem(inventory, Material.QUARTZ_BLOCK, lapis / 4);

        addItem(inventory, Material.COAL, coal % 9);
        addItem(inventory, Material.DIAMOND, diamond % 9);
        addItem(inventory, Material.EMERALD, emerald % 9);
        addItem(inventory, Material.REDSTONE, redstone % 9);
        addItem(inventory, Material.IRON_INGOT, iron % 9);
        addItem(inventory, Material.GOLD_INGOT, gold % 9);
        addItem(inventory, Material.COPPER_INGOT, copper % 9);
        addItem(inventory, Material.LAPIS_LAZULI, lapis % 9);
        addItem(inventory, Material.NETHERITE_INGOT, netherite % 9);
        addItem(inventory, Material.QUARTZ, netherite % 4);
        return true;
    }

    private static void addItem(Inventory inventory, Material material, int amount) {
        if(inventory.getHolder() instanceof Player p) {
            World world = p.getWorld();
            Location loc = p.getLocation();
            inventory.addItem(new ItemStack(material, amount)).values().forEach(item -> world.dropItemNaturally(loc, item));
        } else if(inventory.getHolder() instanceof Chest b) {
            World world = b.getWorld();
            Location loc = b.getLocation();
            inventory.addItem(new ItemStack(material, amount)).values().forEach(item -> world.dropItemNaturally(loc, item));
        }
    }
}
