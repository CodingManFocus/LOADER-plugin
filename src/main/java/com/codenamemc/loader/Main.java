package com.codenamemc.loader;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Main extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Loader 플러그인 활성화됨!");
    }
    @Override
    public void onDisable() {
        getLogger().info("Loader 플러그인 비활성화됨!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equals("shop")){
            openShop((Player) sender);
        }
        return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        getLogger().info("플레이어 접속 완료.");
        Player player = event.getPlayer();
        String playerName = player.getName();
        getLogger().info(playerName);
        UUID uuid = player.getUniqueId();
        String prefix = "";
        if (playerName.toString().charAt(0) == '.') {
            prefix = "§7[배드락BE]§r";
        } else {
            prefix = "§6[자바JE]§r";
        }

        player.setDisplayName(prefix + playerName);
        player.setPlayerListName(prefix + playerName);

    }

    // 상점 UI를 열기 위한 메소드
    public void openShop(Player player) {
        Inventory shopInventory = getServer().createInventory(null, 9, "상점");

        // 상점 아이템 추가 예시: 배리어 아이템을 다이아몬드 15개에 판매
        ItemStack barrierItem = new ItemStack(Material.BARRIER);
        ItemMeta barrierMeta = barrierItem.getItemMeta();
        barrierMeta.setDisplayName("마인토큰"); // 아이템 이름 변경
        barrierItem.setItemMeta(barrierMeta);

        shopInventory.setItem(0, barrierItem);

        player.openInventory(shopInventory);
    }

    // 상점 UI에서 아이템을 클릭했을 때의 이벤트 처리
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory != null && clickedInventory.getHolder() == null) {
            // 상점에서 클릭되었는지 확인
            if (event.getView().getTitle().equals("상점")) {
                event.setCancelled(true); // 상점 UI에서 아이템을 옮기지 못하게 함

                ItemStack clickedItem = event.getCurrentItem();

                // 만약 클릭된 아이템이 마법의 방패라면
                if (clickedItem != null && clickedItem.getType() == Material.BARRIER && clickedItem.hasItemMeta()
                        && clickedItem.getItemMeta().hasDisplayName() && clickedItem.getItemMeta().getDisplayName().equals("마법의 방패")) {
                    // 다이아몬드 15개를 소모하여 마법의 방패 아이템을 구매할 수 있도록 함
                    if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 15)) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 15));

                        // 인벤토리에 추가
                        ItemStack purchasedItem = new ItemStack(Material.BARRIER);
                        ItemMeta meta = purchasedItem.getItemMeta();
                        meta.setDisplayName("마인토큰");
                        purchasedItem.setItemMeta(meta);

                        player.getInventory().addItem(purchasedItem);
                        player.sendMessage("마인토큰을 구매하였습니다.");
                    } else {
                        player.sendMessage("다이아몬드가 부족합니다.");
                    }
                }
            }
        }
    }
}

