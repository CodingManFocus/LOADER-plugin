package com.codenamemc.loader;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ifclick implements Listener {

    private final Main plugin;
    public ifclick(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        // 플레이어가 귀환서를 우클릭했을 때
        if (item != null && item.getType() == Material.KNOWLEDGE_BOOK && item.hasItemMeta() && event.getAction().toString().contains("RIGHT")
                && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("귀환서")) {
            PotionEffect resistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 10, 4);
            player.addPotionEffect(resistance); //저항
            PotionEffect weakness = new PotionEffect(PotionEffectType.WEAKNESS, 20 * 10, 0);
            player.addPotionEffect(weakness); //나약함
            // 플레이어를 지정한 좌표로 텔레포트
            Location teleportLocation = new Location(Bukkit.getServer().getWorld("world"), 0, 100, 0);
            player.teleportAsync(teleportLocation);
            player.sendMessage("귀환서로 텔레포트하였습니다.");
            player.getInventory().removeItem(new ItemStack(Material.KNOWLEDGE_BOOK, 1));
        }

        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals("§6§l파이어리")) {
                if (event.getAction().toString().contains("RIGHT")) {
                    Fireball fireball = player.launchProjectile(Fireball.class);
                    fireball.setVelocity(player.getLocation().getDirection().multiply(1));
                    fireball.setYield(1F);
                    player.getCooldown(Material.BLAZE_ROD);
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() == Material.BARRIER) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("토큰은 설치가 불가합니다");
        }
    }
}
