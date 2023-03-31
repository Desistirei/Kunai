package me.lugamidev.kunai;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import me.lugamidev.kunai.events.PlayerChatEvent;

import java.io.File;
import java.io.IOException;

public class Loader extends PluginBase
{
    @Getter @Setter
    public static Loader instance;
    @Override
    public void onEnable()
    {
        setInstance(this);
        saveDefaultConfig();
        Config config = this.getConfig();
        boolean startupLog = config.getBoolean("startup.enabled");
        boolean chatEvent = config.getBoolean("chat.enabled");
        String startupText = config.getString("startup.message").replace("&", "§");
        if (startupLog)
        {
            getLogger().info(startupText);
        }
        if (chatEvent)
        {
            getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);
        }
    }

    @Override
    public void onDisable()
    {
        Config config = this.getConfig();
        boolean shutdownLog = config.getBoolean("shutdown.enabled");
        String shutdownText = config.getString("shutdown.message").replace("&", "§");
        if (shutdownLog)
        {
            getLogger().info(shutdownText);
        }
    }


}