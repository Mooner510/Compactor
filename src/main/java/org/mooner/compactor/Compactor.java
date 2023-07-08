package org.mooner.compactor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mooner.compactor.command.CommandManager;

import static org.mooner.compactor.MoonerUtils.chat;

public final class Compactor extends JavaPlugin implements Listener {
    private static Compactor instance;

    public static Compactor getPlugin() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getConsoleSender().sendMessage(chat("&eCompactor Plugin Enabled!"));
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(chat("&eCompactor Plugin Disabled!"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return CommandManager.runCommand(sender, command.getName(), args);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
//        if(event.getClickedBlock() != null) event.getPlayer().sendMessage(event.getClickedBlock().getClass().getName());
//        if(event.getClickedBlock().getState() instanceof Chest) event.getPlayer().sendMessage("It's chest");

        if (event.getClickedBlock() != null && event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getState() instanceof Chest chest) {
            if (event.getItem() != null && event.getItem().getType() == Material.STICK && event.getItem().hasItemMeta() && event.getItem().getItemMeta() != null && event.getItem().getItemMeta().hasDisplayName() && event.getItem().getItemMeta().getDisplayName().equals(CommandManager.itemString)) {
                if (CompactorAlgorithm.compact(chest.getInventory())) {
                    event.getPlayer().playSound(event.getPlayer(), Sound.BLOCK_ANVIL_USE, SoundCategory.MASTER, 1, 1);
                    event.getPlayer().sendMessage(chat("&a성공적으로 조합이 되었습니다"));
                    chest.update(true, true);
                } else {
                    event.getPlayer().playSound(event.getPlayer(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 0.5f);
                    event.getPlayer().sendMessage(chat("&c조합실패(재료없음)"));
                }
                event.setCancelled(true);
            }
        }
    }
}
