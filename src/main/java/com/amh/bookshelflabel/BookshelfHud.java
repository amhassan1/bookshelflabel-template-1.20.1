package com.amh.bookshelflabel;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Map;

public class BookshelfHud implements HudRenderCallback {

    private List<String> bookList;
    public static final String MOD_ID = "bookshelflabel";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public BookshelfHud(List<String> bookList) {
        this.bookList = bookList;
    }
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        for (int i = 0; i < bookList.size(); i++) {
            drawContext.drawText(textRenderer, bookList.get(i), 0, textRenderer.fontHeight*i, 14737632, false);
        }
    }
}
