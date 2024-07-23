package dev.pilati.enhancedwindcharge.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import dev.pilati.enhancedwindcharge.messages.MessageUtil;

public class WindChargeTabCompleter implements TabCompleter {

    private final String[] targets = { "@a", "@p", "@r", "@s" };

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = new ArrayList<>();

        Parameters index =Arrays.asList(Parameters.values()).stream()
            .filter(parameterIndex -> parameterIndex.getIndex() == args.length - 1)
            .findFirst()
            .orElse(Parameters.TARGET);

        switch (index) {
            case TARGET -> {
                getFilteredTargets(args, arguments);
            }

            case AMOUNT -> {
                arguments.add(
                        "[" + MessageUtil.getMessage("command.parameter.amount") + "]"
                );
                arguments.add("1");
            }

            case STRENGTH -> {
                arguments.add(
                        "[" + MessageUtil.getMessage("command.parameter.strength") + "]"
                );
                arguments.add("1");
            }
            
            case SPEED -> {
                arguments.add(
                        "[" + MessageUtil.getMessage("command.parameter.speed") + "]"
                );
                arguments.add("1");
            }

            case RADIUS -> {
                arguments.add(
                        "[" + MessageUtil.getMessage("command.parameter.radius") + "]"
                );
                arguments.add("1");
            }
            
            case SHOW_PARTICLES -> {
                arguments.add(
                        "[" + MessageUtil.getMessage("command.parameter.showParticles") + "]"
                );
                arguments.add("true");
                arguments.add("false");
            }

            case PARTICLE_TYPE -> {
                if (!Boolean.parseBoolean(args[Parameters.SHOW_PARTICLES.getIndex()])) {
                    return null;
                }

                getFilteredParticleTypes(args, arguments);
            }
            
            case PARTICLE_AMOUNT -> {
                if (!Boolean.parseBoolean(args[Parameters.SHOW_PARTICLES.getIndex()])) {
                    return null;
                }

                arguments.add(
                        "[" + MessageUtil.getMessage("command.parameter.particleAmount") + "]"
                );
                arguments.add("1");
            }

            case PARTICLE_INTERVAL -> {
                if (!Boolean.parseBoolean(args[Parameters.SHOW_PARTICLES.getIndex()])) {
                    return null;
                }

                arguments.add(
                        "[" + MessageUtil.getMessage("command.parameter.particleInterval") + "]"
                );
                arguments.add("1");
            }
        }

        return arguments;
    }

    private void getFilteredParticleTypes(String[] args, List<String> arguments) {
        List<String> particleTypes = Arrays.asList(Particle.values())
            .stream()
            .map(particle -> particle.name())
            .toList();

        StringUtil.copyPartialMatches(
            args[Parameters.PARTICLE_TYPE.getIndex()], 
            particleTypes, 
            arguments
        );
    }

    private void getFilteredTargets(String[] args, List<String> arguments) {
        List<String> players = Bukkit.getOnlinePlayers()
            .stream()
            .map(player -> player.getName())
            .toList();
        
        List<String> commandTargets = new ArrayList<>();
        commandTargets.addAll(Arrays.asList(targets));
        commandTargets.addAll(players);

        StringUtil.copyPartialMatches(
            args[Parameters.TARGET.getIndex()], 
            commandTargets, 
            arguments
        );
    }
}
