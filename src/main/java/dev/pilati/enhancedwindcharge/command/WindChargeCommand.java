package dev.pilati.enhancedwindcharge.command;

import java.util.ArrayList;

import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.pilati.enhancedwindcharge.WindChargeUtil;
import dev.pilati.enhancedwindcharge.exceptions.MissingPermissionException;
import dev.pilati.enhancedwindcharge.messages.MessageUtil;
import dev.pilati.enhancedwindcharge.properties.WindChargeProperties;

public class WindChargeCommand implements CommandExecutor {

    private void checkParameterPermission(CommandSender sender, String parameter) throws MissingPermissionException {
        if (!sender.hasPermission("enhancedwindcharge.windcharge." + parameter)) {
            throw new MissingPermissionException("enhancedwindcharge.windcharge." + parameter);
        }
    }

    private WindChargeProperties getProperties(String[] args, CommandSender sender) throws MissingPermissionException  {
        WindChargeProperties properties = new WindChargeProperties();

        if (args.length > Parameters.STRENGTH.getIndex()) {
            this.checkParameterPermission(sender, "strength");
            properties.setStrength(Float.parseFloat(args[Parameters.STRENGTH.getIndex()]));
        }

        if (args.length > Parameters.SPEED.getIndex()) {
            this.checkParameterPermission(sender, "speed");
            properties.setSpeed(Float.parseFloat(args[Parameters.SPEED.getIndex()]));
        }

        if (args.length > Parameters.RADIUS.getIndex()) {
            this.checkParameterPermission(sender, "radius");
            properties.setRadius(Float.parseFloat(args[Parameters.RADIUS.getIndex()]));
        }

        if (args.length > Parameters.SHOW_PARTICLES.getIndex()) {
            this.checkParameterPermission(sender, "showParticles");
            properties.setShowParticles(Boolean.parseBoolean(args[Parameters.SHOW_PARTICLES.getIndex()]));
        }

        if (args.length > Parameters.PARTICLE_TYPE.getIndex()) {
            this.checkParameterPermission(sender, "particleType");
            properties.setParticleType(Particle.valueOf(
                args[Parameters.PARTICLE_TYPE.getIndex()]
            ));
        }

        if (args.length > Parameters.PARTICLE_AMOUNT.getIndex()) {
            this.checkParameterPermission(sender, "particleAmount");
            properties.setAmount(Integer.parseInt(args[Parameters.PARTICLE_AMOUNT.getIndex()]));
        }

        if (args.length > Parameters.PARTICLE_INTERVAL.getIndex()) {
            this.checkParameterPermission(sender, "particleInterval");
            properties.setInterval(Integer.parseInt(args[Parameters.PARTICLE_INTERVAL.getIndex()]));
        }

        return properties;
    }

    private int getAmount(String[] args, CommandSender sender) throws MissingPermissionException {
        if (args.length > Parameters.AMOUNT.getIndex()) {
            this.checkParameterPermission(sender, "amount");
            return Integer.parseInt(args[Parameters.AMOUNT.getIndex()]);
        }

        return 1;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("enhancedwindcharge.windcharge")) {
            String message = MessageUtil
                .getPrefixedMessage("command.error.missingPermission")
                .replace("%permission%", "enhancedwindcharge.windcharge");
            sender.sendMessage(message);
            return true;
        }

        if(args.length < 1 || "help".equals(args[0])) {
            showUsage(sender);
            return true;
        }

        ArrayList<Player> players = TargetUtil.getTargets(
            args[Parameters.TARGET.getIndex()],
            sender
        );

        if(players.isEmpty()) {
            String message = MessageUtil
                .getPrefixedMessage("command.error.noTargetFound")
                .replace("%target%", args[0]);

            sender.sendMessage(message);
            return true;
        }

        try {

            int amount = getAmount(args, sender);
            WindChargeProperties properties = getProperties(args, sender);
            WindChargeUtil.giveWindChargeItem(properties, players, amount);
            
        } catch (MissingPermissionException e) {
            String message = MessageUtil
                .getPrefixedMessage("command.error.missingPermission")
                .replace("%permission%", e.getPermission());

            sender.sendMessage(message);
        }

        return true;
    }

    private void showUsage(CommandSender sender) {
        StringBuilder usage = new StringBuilder();

        for (Parameters parameter : Parameters.values()) {

            if (parameter == Parameters.TARGET) {
                usage.append("<");
                usage.append(MessageUtil.getMessage("command.parameter.target"));
                usage.append(">");
                usage.append(" ");
                continue;
            }

            usage.append("[");
            usage.append(MessageUtil.getMessage("command.parameter." + parameter.getKey()));
            usage.append("]");
            usage.append(" ");
        }

        String message = MessageUtil.getPrefixedMessage("command.usage")
            .replace("%usage%", usage.toString());

        sender.sendMessage(message);
    }
}
