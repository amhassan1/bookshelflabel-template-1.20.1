package com.amh.bookshelflabel;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookshelfLabel implements ModInitializer {
    public static final String MOD_ID = "bookshelflabel";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		BookshelfHud bookshelfHud = new BookshelfHud();
		ServerTickEvents.START_WORLD_TICK.register(bookshelfHud);
	}
}