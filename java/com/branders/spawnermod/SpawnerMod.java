package com.branders.spawnermod;

import com.branders.spawnermod.config.Config;
import com.branders.spawnermod.event.SpawnerEventHandler;
import com.branders.spawnermod.item.SpawnerKeyItem;
import com.branders.spawnermod.networking.SpawnerModPacketHandler;

import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * 	Small mod adding more functionality to Mob Spawners (Minecraft Forge 1.14.x)
 * 
 * 	@author Anders <Branders> Blomqvist
 *
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(SpawnerMod.MODID)
public class SpawnerMod
{		
	public static final String MODID = "spawnermod";
	
	/**
	 * 	Register events and config
	 */
    public SpawnerMod() 
    {
    	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.config);
    	Config.loadConfig(Config.config, FMLPaths.CONFIGDIR.get().resolve("spawnermod-config.toml").toString());
    	
    	MinecraftForge.EVENT_BUS.register(this);
    	MinecraftForge.EVENT_BUS.register(new SpawnerEventHandler());
    	
    	// Register new network packet handler used to manage data from client GUI to server
    	SpawnerModPacketHandler.register();
    }
    
    /**
     * 	Event for register spawner wrench and spawner item block
     */
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) 
    {
        registerItems(event.getRegistry());
    }
    
    public static void registerItems(IForgeRegistry<Item> registry)
    {
    	// Register Spawner Wrench
    	registry.register(new SpawnerKeyItem(new Item.Properties().group(ItemGroup.TOOLS).rarity(Rarity.RARE)).setRegistryName(MODID, "spawner_key"));

    	registry.register(new BlockItem(Blocks.SPAWNER, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(Blocks.SPAWNER.getRegistryName()));
    }
}