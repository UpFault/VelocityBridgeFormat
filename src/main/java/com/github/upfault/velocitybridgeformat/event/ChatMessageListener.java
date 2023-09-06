package com.github.upfault.velocitybridgeformat.event;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatMessageListener {

	private final Map<String, String> eventColors;

	public ChatMessageListener() {
		eventColors = new HashMap<>();
		eventColors.put("Traveling Zoo", "§e"); // Yellow
		eventColors.put("Spooky Festival", "§5"); // Dark Purple
		eventColors.put("Season of Jerry", "§c"); // Red
		eventColors.put("New Year Celebration", "§b"); // Aqua
		eventColors.put("Jerry's Workshop", "§c"); // Red
		eventColors.put("Jacob's Farming Contest", "§a"); // Green
		eventColors.put("Fear Mongerer", "§4"); // Dark Red
		eventColors.put("Cult of the Fallen Star", "§d"); // Light Purple
		eventColors.put("Election Over", "§e"); // Yellow
		eventColors.put("Election Booth Opens", "§e"); // Yellow
		eventColors.put("Dark Auction", "§5"); // Dark Purple
		eventColors.put("Bank Interest", "§6"); // Gold
	}

	@SubscribeEvent
	public void onChatMessageReceived(ClientChatReceivedEvent event) {
		String message = event.message.getUnformattedText();

		System.out.println("Received message: " + message);

		Pattern eventPattern = Pattern.compile("§2Guild > §a\\[VIP] VelocitySkyBlock §3\\[M]§f: \\[EVENT\\] (.+?) » (\\d+m|NOW)");
		Matcher matcher2 = eventPattern.matcher(message);

		Pattern usernamePattern = Pattern.compile("§2Guild > §a\\[VIP] VelocitySkyBlock §3\\[M]§f: (.+?)'s Senither Weight: ([\\d,]+) .* Skills: ([\\d,]+) .* Dungeons: ([\\d,]+).*");
		Matcher matcher = usernamePattern.matcher(message);


		Pattern messagePattern1 = Pattern.compile("§2Guild > §a\\[VIP] VelocitySkyBlock §3\\[M]§f: (.+?)'s Level: ([\\d,]+\\.\\d+) \\| Senither Weight: ([\\d,]+) \\| Skill Average: ([\\d,]+\\.\\d+) \\| Slayer: ([\\d,]+) \\| Catacombs: ([\\d,]+) \\| Class Average: ([\\d,]+) \\| Networth: ([\\d.,]+[BM]?) \\| Accessories: ([\\d,]+) \\| Recombobulated: ([\\d,]+) \\| Enriched: ([\\d,]+)");
		Matcher matcher1 = messagePattern1.matcher(message);

		String nwPattern = "§2Guild > §a\\[VIP] VelocitySkyBlock §3\\[M]§f: (.+?)'s Networth is ([\\d.,]+[BM]?) \\| Unsoulbound Networth: ([\\d.,]+[BM]?) \\| Purse: ([\\d.,]+[BM]?) \\| Bank: ([\\d.,]+[BM]?) \\| Museum: ([\\d.,]+[BM]?|N/A)";
		Pattern messagePattern = Pattern.compile(nwPattern);
		Matcher matcher3 = messagePattern.matcher(message);

		if (matcher3.find()) {
			String modifiedMessage = getString2(matcher3);
			event.message = new ChatComponentText(modifiedMessage);
		}

		if (matcher1.find()) {
			String modifiedMessage = getString1(matcher1);
			event.message = new ChatComponentText(modifiedMessage);
		}

		if (matcher.find()) {
			String modifiedMessage = getString(matcher);
			event.message = new ChatComponentText(modifiedMessage);
		}

		if (matcher2.find()) {
			String eventName = matcher2.group(1);
			String timeOrNow = matcher2.group(2);

			String eventColor = eventColors.getOrDefault(eventName, "§6");

			String timeColor = timeOrNow.equals("NOW") ? "§a" : "§e";

			String formattedMessage = eventColor + "[EVENT] " + eventName + "§f » " + timeColor + timeOrNow;
			event.message = new ChatComponentText(formattedMessage);
		}
	}

	@NotNull
	private static String getString(Matcher matcher) {
		String username = matcher.group(1);
		String senitherWeight = matcher.group(2);
		String skills = matcher.group(3);
		String dungeons = matcher.group(4);

		return EnumChatFormatting.DARK_GREEN + "Bridge Command » " +
				EnumChatFormatting.GOLD + username + "'s Senither Weight: " + senitherWeight + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.GREEN + "Skills: " + skills + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.RED + "Dungeons: " + dungeons;
	}

	@NotNull
	private static String getString2(Matcher matcher) {
		String playerName = matcher.group(1);
		String networth = matcher.group(2);
		String unsoulboundNetworth = matcher.group(3);
		String purse = matcher.group(4);
		String bank = matcher.group(5);
		String museum = matcher.group(6);

		if (museum.equals("N/A")) {
			museum = "0";
		}

		return EnumChatFormatting.DARK_GREEN + "Bridge Command » " +
				EnumChatFormatting.GOLD + playerName + "'s Networth is " + networth + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.RED + "Unsoulbound Networth: " + unsoulboundNetworth + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.YELLOW + "Purse: " + purse + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.YELLOW + "Bank: " + bank + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.GREEN + "Museum: " + museum;
	}

	@NotNull
	private static String getString1(Matcher matcher) {
		String username = matcher.group(1);
		String level = matcher.group(2);
		String senitherWeight = matcher.group(3);
		String skillAverage = matcher.group(4);
		String slayer = matcher.group(5);
		String catacombs = matcher.group(6);
		String classAverage = matcher.group(7);
		String networthValue = matcher.group(8);
		String accessories = matcher.group(9);
		String recombobulatedCount = matcher.group(10);
		String enrichmentCount = matcher.group(11);

		return EnumChatFormatting.DARK_GREEN + "Bridge Command » " +
				EnumChatFormatting.AQUA + username + "'s Level: " + level + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.GOLD + "Senither Weight: " + senitherWeight + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.GREEN + "Skill Average: " + skillAverage + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.GOLD + "Slayer: " + slayer + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.RED + "Catacombs: " + catacombs + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.RED + "Class Average: " + classAverage + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.YELLOW + "Networth: " + networthValue + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.BLUE + "Accessories: " + accessories + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.BLUE + "Recombobulated: " + recombobulatedCount + EnumChatFormatting.WHITE + " | " +
				EnumChatFormatting.BLUE + "Enriched: " + enrichmentCount;
	}
}
