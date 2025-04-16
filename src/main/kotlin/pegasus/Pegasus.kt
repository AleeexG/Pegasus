package com.github.aleeexg.pegasus.pegasus

import net.minecraft.client.Minecraft
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

@Mod(modid = "pegasus", version = "1.0", useMetadata = true)
class Pegasus {

    private var lastX = 0.0
    private var lastY = 0.0
    private var lastZ = 0.0
    private var hasMovedBefore = false

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        println("Pegasus mod initialized!")
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun onClientTick(event: TickEvent.ClientTickEvent) {
        if (event.phase != TickEvent.Phase.END) return

        val mc = Minecraft.getMinecraft()
        val player: EntityPlayerSP = mc.thePlayer ?: return

        val x = player.posX
        val y = player.posY
        val z = player.posZ

        if (!hasMovedBefore || x != lastX || y != lastY || z != lastZ) {
            println("Player moved to: x=$x, y=$y, z=$z")
            lastX = x
            lastY = y
            lastZ = z
            hasMovedBefore = true
        }
    }
}