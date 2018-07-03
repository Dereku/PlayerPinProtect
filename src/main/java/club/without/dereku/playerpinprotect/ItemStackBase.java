package club.without.dereku.playerpinprotect;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public final class ItemStackBase {

    private Material material = Material.STONE;
    private short damage = 0;
    private int amount = 1;
    private boolean unbreakable = false;
    private String name = null;
    private List<String> lore = null;
    //Using string for enchantment because "UnsupportedOperationException: Abstract class can't be instantiated!"
    private HashMap<String, Short> enchantments = new HashMap<>();
    private ArrayList<ItemFlag> itemFlags = new ArrayList<>();

    protected ItemStackBase() {
    }

    protected ItemStackBase(Material material) {
        this.material = material;
    }

    public ItemStack toItemStack() {
        ItemStack result = new ItemStack(this.material, this.amount, this.damage);
        ItemMeta im = result.getItemMeta();
        if (this.unbreakable) {
            im.setUnbreakable(true);
        }

        if (this.name != null) {
            im.setDisplayName(
                    ChatColor.translateAlternateColorCodes('\u0026', this.name)
            );
        }
        if (this.lore != null) {
            final LinkedList<String> newLore = new LinkedList<>();
            this.lore.forEach(line -> newLore.add(
                    ChatColor.translateAlternateColorCodes('\u0026', line)
            ));
            im.setLore(newLore);
        }

        if (!this.itemFlags.isEmpty()) {
            im.addItemFlags(this.itemFlags.toArray(new ItemFlag[this.itemFlags.size()]));
        }
        result.setItemMeta(im);

        if (!this.enchantments.isEmpty()) {
            this.enchantments.forEach((ench, level) -> result.addUnsafeEnchantment(Enchantment.getByName(ench), level));
        }
        return result;
    }

    public Material getMaterial() {
        return material;
    }

    public short getDamage() {
        return damage;
    }

    protected ItemStackBase setDamage(short damage) {
        this.damage = damage;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    protected ItemStackBase setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }

    protected ItemStackBase setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public String getName() {
        return name;
    }

    protected ItemStackBase setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getLore() {
        return Collections.unmodifiableList(lore);
    }

    protected ItemStackBase setLore(String... lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    public Map<String, Short> getEnchantments() {
        return Collections.unmodifiableMap(enchantments);
    }

    protected ItemStackBase addEnchantment(String enchantmentName, short level) {
        this.enchantments.put(enchantmentName, level);
        return this;
    }

    public List<ItemFlag> getItemFlags() {
        return Collections.unmodifiableList(itemFlags);
    }

    protected ItemStackBase addItemFlag(ItemFlag itemFlag) {
        this.itemFlags.add(itemFlag);
        return this;
    }

    @Override
    public String toString() {
        return "ItemStackBase{" +
                "material=" + material +
                ", damage=" + damage +
                ", amount=" + amount +
                ", unbreakable=" + unbreakable +
                ", name='" + name + '\'' +
                ", lore=" + lore +
                ", enchantments=" + enchantments +
                ", itemFlags=" + itemFlags +
                '}';
    }
}
