package pl.ktmc.weatherTimePlaceholder;

import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;

import java.util.Map;
import java.util.Objects;

public final class WeatherTimePlaceholder extends PlaceholderExpansion implements Configurable {

    private static final String WEATHER_STORM = "storm";
    private static final String WEATHER_THUNDER = "thunder";
    private static final String WEATHER_CLEAR = "clear";

    private static final String WEATHER_PLACEHOLDER = "weather";
    private static final String TIME_PLACEHOLDER = "time";

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

        if (identifier.equals(WEATHER_PLACEHOLDER)) {
            return getWeatherIcon(world);
        }

        if (identifier.equals(TIME_PLACEHOLDER)) {
            return getTimeIcon(world);
        }

        return null;
    }

    private String getTimeIcon(World world) {
        int time = getServerTime(world);
        if (time >= 0 && time < 6000) {
            return "☀";
        } else if (time >= 6000 && time < 12000) {
            return "☀";
        } else if (time >= 12000 && time < 18000) {
            return "🌕";
        } else if (time >= 18000 && time < 24000) {
            return "🌕";
        } else {
            return "❓";
        }
    }

    private String getWeatherIcon(World world) {
        // Replace with actual method to get server weather
        String weather = getServerWeather(world);
        switch (weather) {
            case WEATHER_CLEAR:
                return "☀";
            case WEATHER_STORM:
                return "🌧️";
            case WEATHER_THUNDER:
                return "⛈";
            default:
                return "❓";
        }
    }

    private String getServerWeather(World world) {
        if (world.hasStorm() && !world.isThundering()) return WEATHER_STORM;
        else if (world.hasStorm() && world.isThundering()) return WEATHER_THUNDER;
        else return WEATHER_CLEAR;
    }

    private int getServerTime(World world) {
        return (int) world.getTime(); // Example return value
    }

    @Override
    public Map<String, Object> getDefaults() {
        return Map.of();
    }
}
