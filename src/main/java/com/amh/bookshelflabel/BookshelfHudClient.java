package com.amh.bookshelflabel;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.List;
import java.util.UUID;

public class BookshelfHudClient extends BookshelfHud implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        UUID uuid = MinecraftClient.getInstance().getSession().getUuidOrNull();

        if(playerToBooksMap.containsKey(uuid)){
            List<String> books = playerToBooksMap.get(uuid);
            for(int i = 0; i < books.size(); i++) {
                drawContext.drawText(textRenderer, books.get(i), 1, textRenderer.fontHeight*i, 0xE0E0E0, false);
            }
        }
    }
}
