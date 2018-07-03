package club.without.dereku.playerpinprotect;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.Arrays;
import java.util.HashMap;

public class Configuration {

    private final GeneralSettings generalSettings = new GeneralSettings();
    private final PinSettings pinSettings = new PinSettings();
    private final WindowSettings windowSettings = new WindowSettings();
    private final ItemSettings itemSettings = new ItemSettings();

    @Override
    public String toString() {
        return "Configuration{" +
                "generalSettings=" + generalSettings +
                ", pinSettings=" + pinSettings +
                ", windowSettings=" + windowSettings +
                ", itemSettings=" + itemSettings +
                '}';
    }

    public class GeneralSettings {
        private boolean forcePinRegistration = true;
        private boolean demotePlayerGroupWhenNotLoggedIn = false;
        private String demoteGroup = "unauthorized";
        private boolean teleportPlayerToOtherLocation = false;
        private String teleportLocation = null; //TODO: Location read/save

        public boolean isForcePinRegistration() {
            return forcePinRegistration;
        }

        public boolean isDemotePlayerGroupWhenNotLoggedIn() {
            return demotePlayerGroupWhenNotLoggedIn;
        }

        public String getDemoteGroup() {
            return demoteGroup;
        }

        public boolean isTeleportPlayerToOtherLocation() {
            return teleportPlayerToOtherLocation;
        }

        public String getTeleportLocation() {
            return teleportLocation;
        }

        @Override
        public String toString() {
            return "GeneralSettings{" +
                    "forcePinRegistration=" + forcePinRegistration +
                    ", demotePlayerGroupWhenNotLoggedIn=" + demotePlayerGroupWhenNotLoggedIn +
                    ", demoteGroup='" + demoteGroup + '\'' +
                    ", teleportPlayerToOtherLocation=" + teleportPlayerToOtherLocation +
                    ", teleportLocation='" + teleportLocation + '\'' +
                    '}';
        }
    }

    public class PinSettings {
        private int pinMinLength = 4;
        private int pinMaxLength = 8;
        private char[] allowedCharacters = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        public int getPinMinLength() {
            return pinMinLength;
        }

        public int getPinMaxLength() {
            return pinMaxLength;
        }

        public char[] getAllowedCharacters() {
            return allowedCharacters;
        }

        @Override
        public String toString() {
            return "PinSettings{" +
                    "pinMinLength=" + pinMinLength +
                    ", pinMaxLength=" + pinMaxLength +
                    ", allowedCharacters=" + Arrays.toString(allowedCharacters) +
                    '}';
        }
    }

    public class WindowSettings {
        private int lines = 5;
        private boolean useWindowFiller = true;
        private boolean useInputDisplay = true;
        private boolean displayEnteredCharacters = false;
        private boolean shuffleKeypadCharacters = true;
        private int keypadOffsetX = 3;
        private int keypadOffsetY = 1;
        private String keypadPattern =
                        "character,character,character|" +
                        "character,character,character|" +
                        "character,character,character|" +
                        "removeLastItem,character,doneItem";
        private int charactersWindowOffsetX = 1;
        private int charactersWindowOffsetY = 0;
        private HashMap<Integer, String> additionalItemsBySlot = new HashMap<>();

        public int getLines() {
            return lines;
        }

        public boolean isUseWindowFiller() {
            return useWindowFiller;
        }

        public boolean isUseInputDisplay() {
            return useInputDisplay;
        }

        public boolean isDisplayEnteredCharacters() {
            return displayEnteredCharacters;
        }

        public boolean isShuffleKeypadCharacters() {
            return shuffleKeypadCharacters;
        }

        public int getKeypadOffsetX() {
            return keypadOffsetX;
        }

        public int getKeypadOffsetY() {
            return keypadOffsetY;
        }

        public String getKeypadPattern() {
            return keypadPattern;
        }

        public int getCharactersWindowOffsetX() {
            return charactersWindowOffsetX;
        }

        public int getCharactersWindowOffsetY() {
            return charactersWindowOffsetY;
        }

        public HashMap<Integer, String> getAdditionalItemsBySlot() {
            return additionalItemsBySlot;
        }

        @Override
        public String toString() {
            return "WindowSettings{" +
                    "lines=" + lines +
                    ", useWindowFiller=" + useWindowFiller +
                    ", useInputDisplay=" + useInputDisplay +
                    ", displayEnteredCharacters=" + displayEnteredCharacters +
                    ", shuffleKeypadCharacters=" + shuffleKeypadCharacters +
                    ", keypadOffsetX=" + keypadOffsetX +
                    ", keypadOffsetY=" + keypadOffsetY +
                    ", keypadPattern='" + keypadPattern + '\'' +
                    ", charactersWindowOffsetX=" + charactersWindowOffsetX +
                    ", charactersWindowOffsetY=" + charactersWindowOffsetY +
                    ", additionalItemsBySlot=" + additionalItemsBySlot +
                    '}';
        }
    }

    public class ItemSettings {
        private ItemStackBase fillerItem = new ItemStackBase(Material.STAINED_GLASS_PANE).setDamage((short) 7).setName("&r")
                .addEnchantment(Enchantment.DURABILITY.getName(), (short) 1).addItemFlag(ItemFlag.HIDE_ENCHANTS);
        private ItemStackBase characterItem = new ItemStackBase(Material.STAINED_GLASS_PANE).setDamage((short) 7).setName("&r")
                .addEnchantment(Enchantment.DURABILITY.getName(), (short) 2).addItemFlag(ItemFlag.HIDE_ENCHANTS);
        private ItemStackBase doneItem = new ItemStackBase(Material.STAINED_GLASS_PANE)
                .setDamage((short) 13).setName("&6Done").setLore("Finish input");
        private ItemStackBase clearItem = new ItemStackBase(Material.STAINED_GLASS_PANE)
                .setDamage((short) 14).setName("&cClear input").setLore("Confused in these buttons? Try again.");
        private ItemStackBase removeLastItem = new ItemStackBase(Material.STAINED_GLASS_PANE)
                .setDamage((short) 4).setName("&cRemove last number").setLore("Everybody makes a mistake.");

        private HashMap<Character, ItemStackBase> items = new HashMap<Character, ItemStackBase>() {{
            for (int i = 0; i < 10; i++) {
                String s = String.valueOf(i);
                this.put(s.charAt(0), new ItemStackBase(Material.STAINED_GLASS_PANE)
                        .setAmount(i > 1 ? i : 1).setDamage((short) 5).setName(s)
                );
            }
        }};

        @Override
        public String toString() {
            return "ItemSettings{" +
                    "fillerItem=" + fillerItem +
                    ", characterItem=" + characterItem +
                    ", doneItem=" + doneItem +
                    ", clearItem=" + clearItem +
                    ", removeLastItem=" + removeLastItem +
                    ", items=" + items +
                    '}';
        }
    }
}
