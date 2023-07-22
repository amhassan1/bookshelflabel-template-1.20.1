package com.amh.bookshelflabel;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.List;


public class BookshelfHud implements HudRenderCallback {

    private List<String> bookList;
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
