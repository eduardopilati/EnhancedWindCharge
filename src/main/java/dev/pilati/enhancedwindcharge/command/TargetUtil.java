package dev.pilati.enhancedwindcharge.command;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TargetUtil {

    private static Player getRandomPlayer() {
        int rnd = new Random().nextInt(Bukkit.getOnlinePlayers().size());
        return Bukkit.getOnlinePlayers().toArray(new Player[0])[rnd];
    }

    public static ArrayList<Player> getTargets(String target, CommandSender sender) {
        ArrayList<Player> players = new ArrayList<>();

        if (target.equals("@a")) {
            players.addAll(Bukkit.getOnlinePlayers());
            return players;
        }

        if (target.equals("@p") || target.equals("@s")) {
            if (sender instanceof Player player) {
                players.add(player);
            }
            return players;
        }

        if (target.equals("@r")) {
            players.add(getRandomPlayer());
            return players;
        }

        Player player = Bukkit.getPlayer(target);
        if (player != null) {
            players.add(player);
        }
        
        return players;
    }
}
