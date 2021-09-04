package at.hitm.hitmplugin;

import at.hitm.hitmplugin.api.API;
import at.hitm.hitmplugin.commands.DateCommand;
import at.hitm.hitmplugin.commands.GetOnlinePlayersCommand;
import at.hitm.hitmplugin.commands.GiveMoneyCommand;
import at.hitm.hitmplugin.items.ItemManager;
import at.hitm.hitmplugin.listeners.JoinLeaveListener;
import at.hitm.hitmplugin.listeners.PlayerCraftListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().fine("Plugin wird aktiviert...");

        listenerRegistration();
        commandRegistration();
        ItemManager.init();
        // start the webserver
        API.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        API.shutdown();
    }

    /**
     * Gets the prefix of the plugin
     *
     * @return the prefix
     */
    public static String getPrefix() {
        return ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "HITM" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
    }

    private void listenerRegistration() {
        Bukkit.getLogger().fine("Plugin wird deaktiviert...");
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerCraftListener(), this);
        pluginManager.registerEvents(new JoinLeaveListener(), this);
    }

    private void commandRegistration() {
        Objects.requireNonNull(getCommand("date")).setExecutor(new DateCommand());
        Objects.requireNonNull(getCommand("getOnlinePlayers")).setExecutor(new GetOnlinePlayersCommand());
        Objects.requireNonNull(getCommand("giveMoney")).setExecutor(new GiveMoneyCommand());
    }
}
