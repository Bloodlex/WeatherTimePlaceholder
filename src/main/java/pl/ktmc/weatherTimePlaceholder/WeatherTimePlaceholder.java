package pl.ktmc.weatherTimePlaceholder;

import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;

import java.util.Map;
import java.util.Objects;

public final class WeatherTimePlaceholder extends PlaceholderExpansion implements Configurable {

    private static final String STORM = "storm";
    private static final String THUNDER = "thunder";
    private static final String CLEAR = "clear";

    @Override
    public String getAuthor() {
        return "Buldog";
    }

    @Override
    public String getIdentifier() {
        return "WeatherTimePlaceholder";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        World world = Objects.requireNonNull(player.getPlayer()).getWorld();

        if (identifier.equals("weather")) {
            return checkWeather(world);
        }

        if (identifier.equals("time")) {
            return checkTime(world);
        }

        return null;
    }

    private String checkTime(World world) {
        // Replace with actual method to get server time
        int time = getServerTime(world);
        if (time >= 0 && time < 6000) {
            return ":sunrise:";
        } else if (time >= 6000 && time < 12000) {
            return "☀";
        } else if (time >= 12000 && time < 18000) {
            return ":city_sunset:";
        } else if (time >= 18000 && time < 24000) {
            return ":crescent_moon:";
        } else {
            return ":question:";
        }
    }

    private String checkWeather(World world) {
        // Replace with actual method to get server weather
        String weather = getServerWeather(world);
        switch (weather) {
            case CLEAR:
                return "☀";
            case STORM:
                return ":cloud_rain:";
            case THUNDER:
                return "⛈";
            default:
                return ":question:";
        }
    }

    // Example methods for retrieving server weather and time
    private String getServerWeather(World world) {
        if (world.hasStorm() && !world.isThundering())
            return STORM;
        else if (world.hasStorm() && world.isThundering())
            return THUNDER;
        else
            return CLEAR;
    }

    private int getServerTime(World world) {
        return (int) world.getTime(); // Example return value
    }

    @Override
    public Map<String, Object> getDefaults() {
        return Map.of();
    }
}
