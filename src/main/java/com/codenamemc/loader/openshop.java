package com.codenamemc.loader;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Bukkit.getServer;

public class openshop implements Listener {

    private final Main plugin;
    public openshop(Main plugin) {
        this.plugin = plugin;
    }
    public static void openInvShop(Player player) {
        Inventory shopInventory = getServer().createInventory(null, 9, "상점");

        // 상점 아이템 추가 예시: 배리어 아이템을 다이아몬드 15개에 판매
        ItemStack barrierItem = new ItemStack(Material.BARRIER);
        ItemMeta barrierMeta = barrierItem.getItemMeta();

        barrierMeta.setLore(java.util.List.of("레벨 5를 소모하고 구입합니다"));
        barrierMeta.setDisplayName("§6§l마인토큰"); // 아이템 이름 변경
        barrierMeta.setCustomModelData(1001);

        barrierItem.setItemMeta(barrierMeta);

        shopInventory.setItem(0, barrierItem);

        ItemStack knowledgeItem = new ItemStack(Material.KNOWLEDGE_BOOK);
        ItemMeta knowledgeMeta = knowledgeItem.getItemMeta();

        knowledgeMeta.setLore(java.util.List.of("0,0 으로 돌아갑니다 (토큰 1개)"));
        knowledgeMeta.setDisplayName("§6§l귀환서"); // 아이템 이름 변경
        knowledgeItem.setItemMeta(knowledgeMeta);

        shopInventory.setItem(2, knowledgeItem);

        ItemStack firery = new ItemStack(Material.BLAZE_ROD);
        ItemMeta fireryMeta = firery.getItemMeta();

        fireryMeta.setLore(java.util.List.of("화염구를 발사합니다 (토큰 2개)"));
        fireryMeta.setDisplayName("§6§l파이어리"); // 아이템 이름 변경
        firery.setItemMeta(fireryMeta);

        shopInventory.setItem(3, firery);

        ItemStack diamond = new ItemStack(Material.DIAMOND, 2);
        ItemMeta diamondMeta = diamond.getItemMeta();

        diamondMeta.setLore(java.util.List.of("다이아몬드를 지급합니다(2개) (토큰 1개)"));
        diamondMeta.setDisplayName("§b§l다이아몬드");
        diamond.setItemMeta(diamondMeta);

        shopInventory.setItem(4, diamond);

        ItemStack emerald = new ItemStack(Material.EMERALD, 10);
        ItemMeta emeraldMeta = emerald.getItemMeta();

        emeraldMeta.setLore(java.util.List.of("에메랄드를 지급합니다(10개) (토큰 2개)"));
        emeraldMeta.setDisplayName("§b§l에메랄드");
        emerald.setItemMeta(emeraldMeta);

        shopInventory.setItem(5, emerald);

        ItemStack level = new ItemStack(Material.EXPERIENCE_BOTTLE, 4);
        ItemMeta levelMeta = level.getItemMeta();

        levelMeta.setLore(java.util.List.of("흙 32세트를 팔고 경험치 4를 얻습니다"));
        levelMeta.setDisplayName("§b§l경험치(4)");
        level.setItemMeta(levelMeta);

        shopInventory.setItem(6, level);

        player.openInventory(shopInventory);
    }
}


