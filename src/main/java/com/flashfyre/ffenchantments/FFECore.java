package com.flashfyre.ffenchantments;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(FFECore.MODID)
public class FFECore {
	public static final String MODID = "ffenchantments";
	public static final Logger LOGGER = LogUtils.getLogger();
	
	public FFECore(IEventBus modEventBus, ModContainer modContainer) {
		modEventBus.addListener(this::commonSetup);
	}
	
	private void commonSetup(final FMLCommonSetupEvent event)
    {
		//LOGGER.info("HELLO FROM COMMON SETUP");
    }
}
