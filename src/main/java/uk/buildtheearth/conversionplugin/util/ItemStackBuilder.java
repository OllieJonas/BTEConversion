package uk.buildtheearth.conversionplugin.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ItemStackBuilder {

    private ItemStack item;
    private ItemMeta meta;

    private String name;
    private List<String> lore;
    private Set<ItemFlag> flags;
    private Map<Enchantment, Integer> enchantments;

    private Class<?> metaClazz;
    private Set<CustomMetaEntry<?>> metaEntries;

    public ItemStackBuilder(Material material) {
        this.item = new ItemStack(material);
    }

    public ItemStackBuilder(Material material, byte data) {
        this.item = new ItemStack(material, 1, (short) 0, data);
    }

    public ItemStackBuilder(Material material, int data) {
        this.item = new ItemStack(material, 1, (short) 0, (byte) data);
    }

    public ItemStackBuilder name(String name) {
        meta.setDisplayName(name);
        return this;
    }

    public ItemStackBuilder amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemStackBuilder durability(short durability) {
        item.setDurability(durability);
        return this;
    }

    public ItemStackBuilder lore(String... lore) {
        meta.setLore(Lists.newArrayList(lore));
        return this;
    }

    public ItemStackBuilder flags(ItemFlag... flags) {
        this.flags = Sets.newHashSet(flags);
        return this;
    }

    @SafeVarargs
    public final <T extends ItemMeta> ItemStackBuilder metaEntries(Class<T> clazz, Consumer<T>... entries) {
        if (!clazz.isInstance(meta)) return this;
        meta.(entries).stream().map(e -> CustomMetaEntry.of(clazz, e)).collect(Collectors.toSet());
        return this;
    }

    public ItemStackBuilder enchantment(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
        return this;
    }



    @SuppressWarnings("unchecked")
    public <T extends ItemMeta> ItemStack build() {
        if (name != null)
            meta.setDisplayName(parseColour(name));

        if (lore != null && !lore.isEmpty())
            meta.setLore(lore.stream().map(this::parseColour).collect(Collectors.toList()));

        if (enchantments != null && !enchantments.isEmpty())
            enchantments.forEach((e, i) -> meta.addEnchant(e, i, true));

        if (flags != null && !flags.isEmpty())
                meta.addItemFlags(flags.toArray(new ItemFlag[0]));

        if (metaEntries != null && !metaEntries.isEmpty()) {
            checkAllSame();
            ClassUtils.checkCanCast(meta, metaClazz);

            T customMeta = (T) metaClazz.cast(meta);
            metaEntries.stream().map(CustomMetaEntry::getConsumer).forEach(c -> c.accept(customMeta));
        }

        return item;
    }

    private void checkAllSame() {
        boolean same = metaEntries.stream().allMatch(e -> e.getType() == metaClazz);
        if (!same)
            throw new IllegalArgumentException("Please ensure the class for the custom Item Meta is always the same!");
    }

    private String parseColour(String name) {
        return ChatColor.translateAlternateColorCodes('&', name);
    }

    @AllArgsConstructor(staticName = "of") @Getter
    private static class CustomMetaEntry<T extends ItemMeta> {
        private final Class<T> type;
        private final Consumer<T> consumer;
    }

}
