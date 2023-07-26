package com.amh.bookshelflabel;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;


public class BookshelfHud implements ServerTickEvents.StartWorldTick {

    public static HashMap<UUID, List<String>> playerToBooksMap = new HashMap<>();

    @Override
    public void onStartTick(ServerWorld serverWorld) {
        List<ServerPlayerEntity> players = serverWorld.getPlayers();

        for (ServerPlayerEntity player: players) {
            HitResult hitResult = player.raycast(10.0, 0.0f, false);
            World world = player.getWorld();

            if(hitResult == null) {
                return;
            }

            if(hitResult.getType() == HitResult.Type.BLOCK) {

                BlockPos blockPos = ((BlockHitResult) hitResult).getBlockPos();
                BlockState blockState = world.getBlockState(blockPos);
                BlockEntity blockEntity = world.getBlockEntity(blockPos);

                playerToBooksMap.remove(player.getUuid());

                if(blockState.getBlock().asItem() == Items.CHISELED_BOOKSHELF) {
                    ChiseledBookshelfBlockEntity bookshelfBlockEntity = (ChiseledBookshelfBlockEntity) blockEntity;
                    if(bookshelfBlockEntity != null) {
                        List<String> allBooks = new ArrayList<>();
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
                            allBooks.add(bookString + enchantmentString);
                        }
                        playerToBooksMap.put(player.getUuid(), allBooks);
                    }
                }
            }
        }
    }
}
