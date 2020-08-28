package uk.buildtheearth.conversionplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExampleCode {
    Inventory inventory = Bukkit.createInventory(null, 27, "test"); // Create your inventory
    Map<Integer, ItemStack> items = new HashMap<>(); // HashMap where the key is the location of the item in the chest

    public ExampleCode() {
        addToHashMap();
        addToInventory();
    }

    public void addToHashMap() { // Add your items here
        items.put(3, new ItemStack(Material.ARROW));
        items.put(5, new ItemStack(Material.ARROW));
        items.put(7, new ItemStack(Material.ARROW));
    }

    public void addToInventory() { // Add the items you've declared in the HashMap to the inventory.
        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) { // Iterate through both the key and value of the hashmap (this is super powerful in general)
            inventory.setItem(entry.getKey(), entry.getValue()); // Add the item to your inventory
        }

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE); // This filler object can come from anywhere, just needs to be made somewhere

        for (int i : calculateFiller(items.keySet(), 27)) { // Iterate through the filler, calculated using the keys from your HashMap.
            inventory.setItem(i, filler); // Add the filler
        }
    }

    // This calculateFiller method works by taking a set of numbers ranging from 0 to the 36, then just removing all the numbers that you've put an ItemStack in from the items HashMap.
    public Set<Integer> calculateFiller(Set<Integer> items, int invSize) { // don't worry about this IntStream range shit, you can just copy it and it'll work fine. I've commented it anyways
        return difference(IntStream.range(0, invSize) // New IntStream with range 0 to inventory size
                .boxed() // Convert to regular stream
                .collect(Collectors.toSet()), // Collect stream as a new set
                items);
    }

    private <E> Set<E> difference(Set<E> original, Set<E> toRemove) { // Calculates the difference between two sets.
        original.removeAll(toRemove);
        return original;
    }
}
