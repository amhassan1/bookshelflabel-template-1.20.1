package com.amh.bookshelflabel;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.Map;

public class BookshelfLabelClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        AttackBlockCallback.EVENT.register(((player, world, hand, pos, direction) -> {
            BlockState state = world.getBlockState(pos);
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (!player.isSpectator() && state.getBlock().asItem() == Items.CHISELED_BOOKSHELF) {
                ChiseledBookshelfBlockEntity bookshelfBlockEntity = (ChiseledBookshelfBlockEntity) blockEntity;
                if(bookshelfBlockEntity != null) {
                    for(int i = 0; i < ChiseledBookshelfBlockEntity.MAX_BOOKS; i++) {
                        ItemStack itemStack = bookshelfBlockEntity.getStack(i);
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(itemStack);

                        String bookString = "Book " + (i + 1) + ": ";
                        StringBuilder enchantmentString = new StringBuilder();
                        for(Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                            Enchantment k = entry.getKey();
                            Integer v = entry.getValue();

                            enchantmentString.append(k.getName(v).getString()).append(", ");
                        }
                        Text text = Text.of(bookString + enchantmentString.toString());

                        if(!enchantmentString.isEmpty())
                            player.sendMessage(text);

                    }
                }
            }
            return ActionResult.PASS;
        }));
    }
}
