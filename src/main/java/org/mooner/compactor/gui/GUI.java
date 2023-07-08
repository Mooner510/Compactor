package org.mooner.compactor.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.mooner.compactor.Compactor;

public class GUI {
    private final Player player;

    private final Inventory inventory;

    private final Clicker clicker;

    public GUI(Player player, String title, int rows) {
        this.player = player;
        this.inventory = Bukkit.createInventory(player, rows * 9, title);
        this.clicker = new Clicker();
    }

    public Player getPlayer() {
        return player;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Clicker getClicker() {
        return clicker;
    }

    protected static class Clicker implements Listener {
        public Clicker() {
            Bukkit.getPluginManager().registerEvents(this, Compactor.getPlugin());
        }

        public void unregister() {
            HandlerList.unregisterAll(this);
        }
    }
}
