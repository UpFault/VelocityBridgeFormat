package com.github.upfault.velocitybridgeformat.cmds;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class TestCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "test";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/test <1|2|3>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 1) {
			throw new CommandException(getCommandUsage(sender));
		}

		String argumentNumber = args[0];

		String messageToSend = null;

		switch (argumentNumber) {
			case "1":
				messageToSend = "§2Guild > §a[VIP] VelocitySkyBlock §3[M]§f: UpFault's Senither Weight: 8,369 | Skills: 6,420 | Dungeons: 509";
				break;
			case "2":
				messageToSend = "§2Guild > §a[VIP] VelocitySkyBlock §3[M]§f: UwUMiau's Level: 236.19 | Senither Weight: 14,107 | Skill Average: 54.1 | Slayer: 3,993,620 | Catacombs: 36 | Class Average: 30 | Networth: 10.45B | Accessories: 77 | Recombobulated: 30 | Enriched: 2";
				break;
			case "3":
				messageToSend = "§2Guild > §a[VIP] VelocitySkyBlock §3[M]§f: UpFault's Networth is 2.57B | Unsoulbound Networth: 950.50M | Purse: 212.51M | Bank: 0 | Museum: 950.50M";
				break;
			default:

		}

		if (messageToSend != null) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(messageToSend));
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
}
