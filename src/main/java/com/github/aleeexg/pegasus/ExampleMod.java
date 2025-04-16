package com.github.aleeexg.pegasus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "pegasus", version = "1.0", useMetadata = true)
public class ExampleMod {

    private double lastX = 0;
    private double lastY = 0;
    private double lastZ = 0;
    private boolean hasMovedBefore = false;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Pegasus mod initialized!");
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) return;

        EntityPlayerSP player = mc.thePlayer;
        double x = player.posX;
        double y = player.posY;
        double z = player.posZ;

        if (!hasMovedBefore || x != lastX || y != lastY || z != lastZ) {
            System.out.println("Player moved to: x=" + x + ", y=" + y + ", z=" + z);
            lastX = x;
            lastY = y;
            lastZ = z;
            hasMovedBefore = true;
        }
    }
}
