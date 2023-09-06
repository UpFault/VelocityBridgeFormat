package com.github.upfault.velocitybridgeformat;

import com.github.upfault.velocitybridgeformat.cmds.TestCommand;
import com.github.upfault.velocitybridgeformat.event.ChatMessageListener;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "velocitybridgeformat", useMetadata = true)
public class VelocityBridgeFormat {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ChatMessageListener chatMessageListener = new ChatMessageListener();
        MinecraftForge.EVENT_BUS.register(chatMessageListener);

        FMLLog.info("VelocityBridgeFormat: ChatMessageListener registered successfully.");

        TestCommand testCommand = new TestCommand();
        ClientCommandHandler.instance.registerCommand(testCommand);

        FMLLog.info("VelocityBridgeFormat: TestCommand registered successfully.");
    }
}