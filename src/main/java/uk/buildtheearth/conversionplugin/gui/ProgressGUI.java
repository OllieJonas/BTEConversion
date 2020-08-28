package uk.buildtheearth.conversionplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.inventory.Inventory;
import uk.buildtheearth.conversionplugin.job.step.Step;

import java.util.Vector;

import static uk.buildtheearth.conversionplugin.util.ChestGUIUtils.calculateInventorySize;

public class ProgressGUI {

    private final String name;
    private final int slotsRequired;

    private final int invSize;

    private int currStep;

    private Inventory inventory;

    public ProgressGUI(String name, int slotsRequired, Vector<Step<?>> steps) {
        this.name = name;
        this.slotsRequired = slotsRequired;

        this.invSize = calculateInventorySize(slotsRequired);
        this.inventory = Bukkit.createInventory(null, invSize, name);
        this.currStep = 0;
    }

    public ProgressGUI init() {

        return this;
    }

    public enum State {
        COMPLETED("Completed!", DyeColor.GREEN.getWoolData());

        String message;
        byte data;

        State(String message, byte data) {
            this.message = message;
            this.data = data;
        }
    }
}
