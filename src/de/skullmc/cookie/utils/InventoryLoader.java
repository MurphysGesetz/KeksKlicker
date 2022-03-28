package de.skullmc.cookie.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryLoader {

    private final List<ItemStack> achievmentItemStacks;
    private final List<Integer> achievmentSlots = Arrays.asList(19, 20, 21, 22, 23, 24, 25, 28, 29, 30);
    private final Inventory cookieMenu;
    private final Inventory cookieUpgrades;
    private final Inventory cookieAchievments;

    public InventoryLoader() {
        this.achievmentItemStacks = new ArrayList<>(10);
        loadAchievmentItemStacks();
        cookieMenu = loadCookieMenu();
        cookieUpgrades = loadCookieUpgrades();
        cookieAchievments = loadCookieAchievments();
    }

    private void loadAchievmentItemStacks() {
        final String skullTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQwNjNiYTViMTZiNzAzMGEyMGNlNmYwZWE5NmRjZDI0YjA2NDgzNmY1NzA0NTZjZGJmYzllODYxYTc1ODVhNSJ9fX0=";
        achievmentItemStacks.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§6▰§e▰ Keks-Lehrling").setLore("§7Farme §e100 §7Kekse.").setSkullTexture(skullTexture).build());
        achievmentItemStacks.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§6▰§e▰ Keks-Dieb").setLore("§7Farme §e1.000 §7Kekse.").setSkullTexture(skullTexture).build());
        achievmentItemStacks.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§6▰§e▰ Keks-Ernter").setLore("§7Farme §e10.000 §7Kekse.").setSkullTexture(skullTexture).build());
        achievmentItemStacks.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§6▰§e▰ Alles meine!").setLore("§7Farme §e50.000 §7Kekse.").setSkullTexture(skullTexture).build());
        achievmentItemStacks.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§6▰§e▰ Keine Hobbies").setLore("§7Farme §e100.000 §7Kekse.").setSkullTexture(skullTexture).build());
        achievmentItemStacks.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§6▰§e▰ Meister der Kekse").setLore("§7Farme §e250.000 §7Kekse.").setSkullTexture(skullTexture).build());
        achievmentItemStacks.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§6▰§e▰ Ich bekomme sie alle!").setLore("§7Farme §e500.000 §7Kekse.").setSkullTexture(skullTexture).build());
        achievmentItemStacks.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§6▰§e▰ Finger brennt!").setLore("§7Farme §e1.000.000 §7Kekse.").setSkullTexture(skullTexture).build());
        achievmentItemStacks.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§6▰§e▰ Gott der Kekse").setLore("§7Farme §e10.000.000 §7Kekse.").setSkullTexture(skullTexture).build());
        achievmentItemStacks.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§6▰§e▰ KeksKlicker durchgespielt").setLore("§7Farme §e1.000.000.000 §7Kekse.").setSkullTexture(skullTexture).build());
    }

    private Inventory loadCookieUpgrades() {
        final Inventory cookieUpgrades = Bukkit.createInventory(null, 6 * 9, "§6▰§e▰ Verbessern §8▰ §7Auswahl");
        for (int i = 0; i < 54; i++) {
            cookieUpgrades.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 7).setName("§7").build());
        }
        cookieUpgrades.setItem(49, new ItemBuilder(Material.ARROW).setName("§4▰§c▰ Zurück").build());
        return cookieUpgrades;
    }

    private Inventory loadCookieMenu() {
        final Inventory cookieMenu = Bukkit.createInventory(null, 3 * 9, "§6▰§e▰ Keks §8▰ §7Auswahl");
        for (int i = 0; i < 27; i++) {
            cookieMenu.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 7).setName("§7").build());
        }
        cookieMenu.setItem(11, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§4▰§c▰ Erfolge").setLore("§7Sehe deine Keks Erfolge ein!").setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzIwZWYwNmRkNjA0OTk3NjZhYzhjZTE1ZDJiZWE0MWQyODEzZmU1NTcxODg2NGI1MmRjNDFjYmFhZTFlYTkxMyJ9fX0=").build());
        cookieMenu.setItem(15, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§5▰§d▰ Verbesserungen").setLore("§7Verbessere deine Klicks!").setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2FkOGNjOTgyNzg2ZmI0ZDQwYjBiNmU2NGE0MWYwZDk3MzZmOWMyNmFmZmI4OThmNGE3ZmFlYTg4Y2NmODk5NyJ9fX0=").build());
        return cookieMenu;
    }

    private Inventory loadCookieAchievments() {
        final String skullTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFhNTUzOTFlZjg5YjJhNTM0YTU4ZDAxNTBmMTYyMTE3NTMxMGVhODc3MzU2MDFhZWJlOGQ2YjFkN2M1NjhiYiJ9fX0=";
        final Inventory cookieAchievments = Bukkit.createInventory(null, 6 * 9, "");
        for (int i = 0; i < 54; i++) {
            cookieAchievments.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 7).setName("§7").build());
        }
        cookieAchievments.setItem(49, new ItemBuilder(Material.ARROW).setName("§4▰§c▰ Zurück").build());
        cookieAchievments.setItem(19, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§5▰§d▰ Keks-Lehrling").setLore("§8§l???").setSkullTexture(skullTexture).build());
        cookieAchievments.setItem(20, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§5▰§d▰ Keks-Dieb").setLore("§8§l???").setSkullTexture(skullTexture).build());
        cookieAchievments.setItem(21, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§5▰§d▰ Keks-Ernter").setLore("§8§l???").setSkullTexture(skullTexture).build());
        cookieAchievments.setItem(22, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§5▰§d▰ Alles meine!").setLore("§8§l???").setSkullTexture(skullTexture).build());
        cookieAchievments.setItem(23, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§5▰§d▰ Keine Hobbies").setLore("§8§l???").setSkullTexture(skullTexture).build());
        cookieAchievments.setItem(24, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§5▰§d▰ Meister der Kekse").setLore("§8§l???").setSkullTexture(skullTexture).build());
        cookieAchievments.setItem(25, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§5▰§d▰ Ich bekomme sie alle!").setLore("§8§l???").setSkullTexture(skullTexture).build());
        cookieAchievments.setItem(28, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§5▰§d▰ Finger brennt!").setLore("§8§l???").setSkullTexture(skullTexture).build());
        cookieAchievments.setItem(29, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§5▰§d▰ Gott der Kekse").setLore("§8§l???").setSkullTexture(skullTexture).build());
        cookieAchievments.setItem(30, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§5▰§d▰ KeksKlicker durchgespielt").setLore("§8§l???").setSkullTexture(skullTexture).build());
        cookieAchievments.setItem(2, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner("MHF_Question").setName("§7» §e§l??? §7«").build());
        cookieAchievments.setItem(3, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner("MHF_Question").setName("§7» §e§l??? §7«").build());
        cookieAchievments.setItem(4, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner("MHF_Question").setName("§7» §e§l??? §7«").build());
        cookieAchievments.setItem(5, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner("MHF_Question").setName("§7» §e§l??? §7«").build());
        cookieAchievments.setItem(6, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner("MHF_Question").setName("§7» §e§l??? §7«").build());
        return cookieAchievments;
    }

    public List<Integer> getAchievmentSlots() {
        return achievmentSlots;
    }

    public List<ItemStack> getAchievmentItemStacks() {
        return achievmentItemStacks;
    }

    public Inventory getCookieAchievments() {
        return cookieAchievments;
    }

    public Inventory getCookieUpgrades() {
        return cookieUpgrades;
    }

    public Inventory getCookieMenu() {
        return cookieMenu;
    }
}
