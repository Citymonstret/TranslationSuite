package com.intellectualsites.translation.bungee;

import com.intellectualsites.translation.TranslationAsset;
import com.intellectualsites.translation.TranslationLanguage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;

/**
 * @author Citymonstret
 */
public class BungeeTranslation {

    /**
     * Get the converted string
     * @param asset asset
     * @return converted asset
     */
    public static String convert(TranslationAsset asset) {
        // In some cases newline can screw stuff up, so I added a new character thing
        // &- = new line
        return ChatColor.translateAlternateColorCodes('&', asset.getTranslated().replaceAll("&-", "\n"));
    }

    /**
     * Get the universal parent based on the plugin data folder
     * @param plugin to check
     * @return parent folder
     */
    public static File getParent(Plugin plugin) {
        return new File(plugin.getDataFolder() + File.separator + "translations");
    }

    /**
     * The default translation language
     * @return default translation language
     */
    public TranslationLanguage getDefaultLanguage() {
        return TranslationLanguage.englishAmerican;
    }

}
