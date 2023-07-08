package org.mooner.compactor.command;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.mooner.compactor.CompactorAlgorithm;

import static org.mooner.compactor.MoonerUtils.chat;

public class CommandManager {
    public static String itemString = chat("&a블럭 제조기");

    public static boolean runCommand(CommandSender sender, String cmd, String[] args) {
        switch (cmd) {
            case "blockmaker" -> {
                if (sender instanceof Player p) {
                    ItemStack itemStack = new ItemStack(Material.STICK);
                    ItemMeta meta = itemStack.getItemMeta();
                    meta.setDisplayName(itemString);
                    itemStack.setItemMeta(meta);
                    p.getInventory().addItem(itemStack);
                    p.sendMessage(chat("&a성공적으로 생성했습니다"));
                }
            }
            case "craft" -> {
                if (sender instanceof Player p) {
                    if (CompactorAlgorithm.compact(p.getInventory())) {
                        p.playSound(p, Sound.BLOCK_ANVIL_USE, SoundCategory.MASTER, 1, 1);
                        p.sendMessage(chat("&a성공적으로 조합이 되었습니다"));
                    } else {
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 0.5f);
                        p.sendMessage(chat("&c조합실패(재료없음)"));
                    }
                }
            }
            default -> {
                return false;
            }
        }
        return true;
    }
}
