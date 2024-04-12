package com.codenamemc.loader;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class inshop implements Listener {

    private final Main plugin;
    public inshop(Main plugin) { this.plugin = plugin; }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory != null && clickedInventory.getHolder() == null) {
            // 상점에서 클릭되었는지 확인
            if (event.getView().getTitle().equals("상점")) {
                event.setCancelled(true); // 상점 UI에서 아이템을 옮기지 못하게 함

                ItemStack clickedItem = event.getCurrentItem();

                ItemStack barrierItem = new ItemStack(Material.BARRIER, 1);
                ItemMeta barrierMeta = barrierItem.getItemMeta();
                barrierMeta.setCustomModelData(1001);

                barrierMeta.setDisplayName("§6§l마인토큰"); // 아이템 이름 변경
                barrierItem.setItemMeta(barrierMeta);

                if (clickedItem != null && clickedItem.getType() == Material.BARRIER && clickedItem.hasItemMeta()
                        && clickedItem.getItemMeta().hasDisplayName() && clickedItem.getItemMeta().getDisplayName().equals("§6§l마인토큰")) {
                    if (player.getLevel() > 4 && loadersystem.canAddItem(player, barrierItem)) {
                        player.setLevel(player.getLevel() - 4);
                        player.sendMessage("레벨이 4만큼 감소했습니다. 현재 레벨: " + player.getLevel());

                        player.getInventory().addItem(barrierItem);
                        player.sendMessage("마인토큰을 구매하였습니다.");
                        plugin.getLogger().info(player.getName() + "님이 아이템을 구매 하였습니다");
                    } else {
                        player.sendMessage("레벨이 부족하거나 인벤토리가 부족합니다");
                    }
                }

                if (clickedItem != null && clickedItem.getType() == Material.KNOWLEDGE_BOOK && clickedItem.hasItemMeta()
                        && clickedItem.getItemMeta().hasDisplayName() && clickedItem.getItemMeta().getDisplayName().equals("§6§l귀환서")) {
                    plugin.getLogger().info(player.getName() + "님이 아이템을 구매 하였습니다");
                    ItemStack purchasedItem = new ItemStack(Material.KNOWLEDGE_BOOK);
                    ItemMeta meta = purchasedItem.getItemMeta();
                    meta.setDisplayName("귀환서");
                    purchasedItem.setItemMeta(meta);

                    if (player.getInventory().contains(Material.BARRIER, 1) && loadersystem.canAddItem(player, purchasedItem)) {

                        player.getInventory().removeItemAnySlot(barrierItem);

                        // 인벤토리에 추가

                        player.getInventory().addItem(purchasedItem);
                        player.sendMessage("귀환서을 구매하였습니다.");
                    } else {
                        player.sendMessage("마인토큰이 부족하거나 인벤토리가 부족합니다");
                    }
                }

                if (clickedItem != null && clickedItem.getType() == Material.BLAZE_ROD && clickedItem.hasItemMeta()
                        && clickedItem.getItemMeta().hasDisplayName() && clickedItem.getItemMeta().getDisplayName().equals("§6§l파이어리")) {
                    plugin.getLogger().info(player.getName() + "님이 아이템을 구매 하였습니다");
                    ItemStack purchasedItem = new ItemStack(Material.BLAZE_ROD);
                    ItemMeta meta = purchasedItem.getItemMeta();
                    meta.setDisplayName("§6§l파이어리");
                    //meta.setCustomModelData();
                    purchasedItem.setItemMeta(meta);

                    if (player.getInventory().contains(Material.BARRIER, 2) && loadersystem.canAddItem(player, purchasedItem)) {

                        player.getInventory().removeItemAnySlot(barrierItem);
                        player.getInventory().removeItemAnySlot(barrierItem);

                        // 인벤토리에 추가

                        player.getInventory().addItem(purchasedItem);
                        player.sendMessage("파이어리를 구매하였습니다.");
                    } else {
                        player.sendMessage("마인토큰이 부족하거나 인벤토리가 부족합니다");
                    }
                }

                if (clickedItem != null && clickedItem.getType() == Material.DIAMOND && clickedItem.hasItemMeta()
                        && clickedItem.getItemMeta().hasDisplayName() && clickedItem.getItemMeta().getDisplayName().equals("§b§l다이아몬드")) {
                    plugin.getLogger().info(player.getName() + "님이 아이템을 구매 하였습니다");
                    ItemStack purchasedItem = new ItemStack(Material.DIAMOND, 2);
                    ItemMeta meta = purchasedItem.getItemMeta();
                    purchasedItem.setItemMeta(meta);

                    if (player.getInventory().contains(Material.BARRIER, 1) && loadersystem.canAddItem(player, purchasedItem)) {

                        player.getInventory().removeItemAnySlot(barrierItem);

                        // 인벤토리에 추가

                        player.getInventory().addItem(purchasedItem);
                        player.sendMessage("다이아몬드(2개)을 구매하였습니다.");
                    } else {
                        player.sendMessage("마인토큰이 부족하거나 인벤토리가 부족합니다");
                    }
                }

                if (clickedItem != null && clickedItem.getType() == Material.EMERALD && clickedItem.hasItemMeta()
                        && clickedItem.getItemMeta().hasDisplayName() && clickedItem.getItemMeta().getDisplayName().equals("§b§l에메랄드")) {
                    plugin.getLogger().info(player.getName() + "님이 아이템을 구매 하였습니다");
                    ItemStack purchasedItem = new ItemStack(Material.EMERALD, 10);
                    ItemMeta meta = purchasedItem.getItemMeta();
                    purchasedItem.setItemMeta(meta);

                    if (player.getInventory().contains(Material.BARRIER, 2) && loadersystem.canAddItem(player, purchasedItem)) {

                        player.getInventory().removeItemAnySlot(barrierItem);
                        player.getInventory().removeItemAnySlot(barrierItem);

                        // 인벤토리에 추가

                        player.getInventory().addItem(purchasedItem);
                        player.sendMessage("에메랄드(10개)을 구매하였습니다.");
                    } else {
                        player.sendMessage("마인토큰이 부족하거나 인벤토리가 부족합니다");
                    }
                }
            }
        }
    }

}
