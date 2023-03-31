package me.lugamidev.kunai.events;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.Config;
import me.lugamidev.kunai.Loader;

public class PlayerChatEvent implements Listener
{
    Server server = Server.getInstance();
    @EventHandler
    public void onChat(cn.nukkit.event.player.PlayerChatEvent e) throws InterruptedException {
        Player player = e.getPlayer();
        Config config = Loader.instance.getConfig();

        String mainMsg = config.getString("chat.player-message").replace("&", "§");
        String answer = config.getString("chat.player-answer").replace("&", "§").replace("%player%", e.getPlayer().getName());

        if(e.getMessage().equals(mainMsg))
        {
            new NukkitRunnable(){
                public void run(){
                    player.sendMessage(answer);
                }
            }.runTaskLater(Loader.getInstance(), 10);
        }
    }
}
