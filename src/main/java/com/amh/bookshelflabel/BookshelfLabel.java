package com.amh.bookshelflabel;


import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BookshelfLabel implements ModInitializer {
    public static final String MOD_ID = "bookshelflabel";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		List<String> stringList = new ArrayList<>();

		ServerTickEvents.START_WORLD_TICK.register((server)-> {
			List<ServerPlayerEntity> players = server.getPlayers();


			for (ServerPlayerEntity player: players) {
				HitResult hitResult = MinecraftClient.getInstance().crosshairTarget;
				World world = player.getWorld();

				if(hitResult == null) {
					return;
				}

				if(hitResult.getType() == HitResult.Type.BLOCK) {
					BlockPos blockPos = ((BlockHitResult) hitResult).getBlockPos();
					BlockState blockState = world.getBlockState(blockPos);
					BlockEntity blockEntity = world.getBlockEntity(blockPos);
					stringList.clear();
					if(blockState.getBlock().asItem() == Items.CHISELED_BOOKSHELF) {
						ChiseledBookshelfBlockEntity bookshelfBlockEntity = (ChiseledBookshelfBlockEntity) blockEntity;
						if(bookshelfBlockEntity != null) {
							for (int i = 0; i < ChiseledBookshelfBlockEntity.MAX_BOOKS; i++) {
								ItemStack itemStack = bookshelfBlockEntity.getStack(i);
								Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(itemStack);

								String bookString = "Book " + (i + 1) + ": ";
								StringBuilder enchantmentString = new StringBuilder();
								for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
									Enchantment k = entry.getKey();
									Integer v = entry.getValue();

									enchantmentString.append( k.getName(v).getString() ).append(", ");
								}
								if(enchantmentString.isEmpty() && itemStack.isIn(ItemTags.BOOKSHELF_BOOKS)) {
									enchantmentString.append( itemStack.getName().getString());
								}

								stringList.add(bookString + enchantmentString);
							}
						}
					}
				}

			}
		});

		BookshelfHud bookshelfHud = new BookshelfHud(stringList);
		HudRenderCallback.EVENT.register(bookshelfHud);
	}
}