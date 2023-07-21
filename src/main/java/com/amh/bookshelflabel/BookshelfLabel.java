package com.amh.bookshelflabel;


import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BookshelfLabel implements ModInitializer {
    public static final String MOD_ID = "bookshelflabel";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ClientTickEvents.START_CLIENT_TICK.register((client)-> {
			HitResult hitResult = client.crosshairTarget;
			ClientWorld clientWorld = client.world;
			if(hitResult == null) {
				return;
			}

			if(clientWorld == null) {
				return;
			}


			if(hitResult.getType() == HitResult.Type.BLOCK) {
				BlockPos blockPos = ((BlockHitResult)hitResult).getBlockPos();
				BlockState blockState = client.world.getBlockState(blockPos);

				if(blockState.getBlock().asItem() == Items.CHISELED_BOOKSHELF) {
					LOGGER.info("I SEE BOOKSHELF");

				}
			}
		});
	}
}