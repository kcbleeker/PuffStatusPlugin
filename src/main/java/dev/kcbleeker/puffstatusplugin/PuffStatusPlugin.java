package dev.kcbleeker.puffstatusplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.YamlConfiguration;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PuffStatusPlugin extends JavaPlugin implements Listener {
    private YamlConfiguration statusesConfig;
    private File statusesFile;

    @Override
    public void onEnable() {
        getLogger().info("PuffStatusPlugin has been enabled!");
        // Load statuses.yml
        statusesFile = new File(getDataFolder(), "statuses.yml");
        if (!statusesFile.exists()) {
            statusesFile.getParentFile().mkdirs();
            saveResource("statuses.yml", false);
        }
        statusesConfig = YamlConfiguration.loadConfiguration(statusesFile);
        // Register events
        getServer().getPluginManager().registerEvents(this, this);
        // Register command executor
        this.getCommand("status").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("PuffStatusPlugin has been disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String status = getStatus(player.getUniqueId());
        MiniMessage miniMessage = MiniMessage.miniMessage();
        Component nameComponent;
        if (status != null && !status.isEmpty()) {
            nameComponent = miniMessage.deserialize(status)
                .append(Component.space())
                .append(Component.text(player.getName())
                    .color(TextColor.color(0xFFFFFF))
                    .decoration(TextDecoration.BOLD, false)
                    .decoration(TextDecoration.ITALIC, false)
                    .decoration(TextDecoration.UNDERLINED, false)
                    .decoration(TextDecoration.STRIKETHROUGH, false)
                    .decoration(TextDecoration.OBFUSCATED, false));
        } else {
            nameComponent = Component.text(player.getName()).color(TextColor.color(0xFFFFFF));
        }
        player.displayName(nameComponent);
        player.playerListName(nameComponent);
    }

    public String getStatus(UUID uuid) {
        return statusesConfig.getString(uuid.toString());
    }

    public void setStatus(UUID uuid, String status) {
        statusesConfig.set(uuid.toString(), status);
        try {
            statusesConfig.save(statusesFile);
        } catch (IOException e) {
            getLogger().warning("Could not save statuses.yml: " + e.getMessage());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Component.text("Only players can set their status.", NamedTextColor.RED));
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(Component.text("Usage: /status <your status message>", NamedTextColor.YELLOW));
            return true;
        }
        String status = String.join(" ", args);
        setStatus(player.getUniqueId(), status);
        MiniMessage miniMessage = MiniMessage.miniMessage();
        Component nameComponent = miniMessage.deserialize(status)
            .append(Component.space())
            .append(Component.text(player.getName())
                .color(TextColor.color(0xFFFFFF))
                .decoration(TextDecoration.BOLD, false)
                .decoration(TextDecoration.ITALIC, false)
                .decoration(TextDecoration.UNDERLINED, false)
                .decoration(TextDecoration.STRIKETHROUGH, false)
                .decoration(TextDecoration.OBFUSCATED, false));
        player.displayName(nameComponent);
        player.playerListName(nameComponent);
        player.sendMessage(Component.text("Your status has been set to: " + status, NamedTextColor.GREEN));
        return true;
    }
}
