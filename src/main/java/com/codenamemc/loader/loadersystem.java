package com.codenamemc.loader;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class loadersystem implements Listener {

    private final Main plugin;

    public loadersystem(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.getLogger().info("플레이어 접속 완료.");
        Player player = event.getPlayer();
        String playerName = player.getName();
        String prefix = "";
        if (isbedrock(player)) {
            prefix = "§7[BE]§r";
        } else {
            prefix = "§6[JE]§r";
        }

        if (playerName.equals("CodingManFocus")) prefix = (ChatColor.DARK_AQUA + "[관리자]§r") + prefix;
        if (playerName.equals(".Laon7982")) prefix = (ChatColor.DARK_AQUA + "[관리자]§r") + prefix;

        player.setDisplayName(prefix + playerName);
        player.setPlayerListName(prefix + playerName);

    }

    public boolean isbedrock(Player player) {
        String playerName = player.getName();
        return playerName.charAt(0) == '.';
    }

    public static boolean canAddItem(Player player, ItemStack item) {
        Inventory inventory = player.getInventory();

        return inventory.firstEmpty() != -1;
    }

    @EventHandler
    public void onAnvilUse(PrepareAnvilEvent event) {
        // 모루에서 아이템의 이름이 변경되려고 할 때 그것을 막습니다.
        if (event.getInventory().getRenameText() != null && !event.getInventory().getRenameText().equals("")) {
            // 결과 슬롯을 비웁니다.
            event.setResult(null);
        }
    }

    public static void gotoHome(Player player) {
        ItemStack barrierItem = new ItemStack(Material.BARRIER, 1);
        ItemMeta barrierMeta = barrierItem.getItemMeta();

        barrierMeta.setDisplayName("§6§l마인토큰"); // 아이템 이름 변경
        barrierItem.setItemMeta(barrierMeta);
        Location bedSpawn = player.getBedSpawnLocation();
        if (bedSpawn != null) {
            // 청크가 이미 로딩되었는지 확인합니다.
            Chunk chunk = bedSpawn.getChunk();
            if (!chunk.isLoaded()) {
                // 청크가 로딩되지 않았다면, 청크를 로드합니다.
                chunk.load();
            }
            // 청크가 로딩된 후 플레이어를 텔레포트합니다.
            player.teleport(bedSpawn);
        } else {
            // 침대 스폰이 설정되지 않았을 경우, 기본 스폰 위치로 텔레포트하거나
            // 다른 로직을 구현할 수 있습니다.
            player.sendMessage("침대 스폰 위치가 설정되지 않았습니다.");
        }
    }
    public static void tutorial(Player player) {
        player.sendMessage("""
                안녕! 로더 튜토리얼에 온걸 환영해!
                난 이번에 설명을 맡은 하디Hardy 라고해!
                로더는 RPG SMP로 여러 기능이 있는데 자세히 알기가 어려워
                
                금세 이해 하니까 잠깐만 읽어봐!
                1. 몬스터
                몬스터들은 각자 랜덤으로 강력,HP를 나타내는 레벨이 있어
                로더의 난이도는 쉬움이지만, 몬스터 자체는 굉장히 강력해...!
                플레이 하면서 느낀건데 레벨이 높은 몬스터들이 여럿이 모이면
                다이아몬드로 완전 무장해도 굉장히 강력해!
                그러니까 강력해지지 않은 이상 너무 맞짱을 뜨려하지마 (엄청 아파...)
                
                2. 상점
                /shop 으로 상점을 열수 있어!
                상점에서는 마법 도구나, 자원 등등 여러 용품을 팔아!
                구매는 플레이어의 레벨을 소모(4레벨) 하여 마인토큰이라는 화폐로 구매 해야해
                현금을 통한 구매를 유도 하거나, 현금으로 아이템을 판매하는건
                Mojang Eula 위반이기도, 로더의 규칙 위반이기도 하니까 주의 해줘!
                (솔직히 개발자가 한명이고 돈을 벌기 어려운데 현금 거래가 배 아픈건 아닐까...)
                
                3. 집으로 돌아가기
                /gotohome 으로 너가 설정한 오버월드의 리스폰 지점(침대)로 마인토큰 1개를 소모하고 이동할수 있어!
                그런데 침대가 가로 막혀있거나, 침대를 다른 사람이 사용하면 자동적으로 0, 0 으로 이동이 되고,
                아직까진 불안정한 기능이니 주의 해줘!
                
                4. 서버 세부 사항
                후후... 자랑할 시간이다! 서버 성능은
                CPU : AMD Ryzen 5 5600G
                RAM : 16GB
                SSD : 256GB
                이렇게 돼! 그리고 서버는 Paper의 포크인 Folia 이기 때문에 일부 버그가 막혀 있으니 주의 해줘!
                (개발팀 : CodenameMC 전체 개발자 : 포커스Focus)
                
                자! 여기 까지 로더 설명이였고, 좋은 플레이가 되길 바래!
                걷지 말고 뛰어!
                """);
    }
}
