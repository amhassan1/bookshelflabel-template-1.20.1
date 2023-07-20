//package com.amh.bookshelflabel.mixin;
//
//import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.font.TextRenderer;
//import net.minecraft.client.render.VertexConsumerProvider;
//import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.entity.Entity;
//import net.minecraft.text.Text;
//import org.joml.Matrix4f;
//
//public interface BookshelfLabelRenderer {
//    default void renderLabelIfPresent(ChiseledBookshelfBlockEntity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
////        double d = this.dispatcher.getSquaredDistanceToCamera((Entity)entity);
////        if (d > 4096.0) {
////            return;
////        }
//        float f = 1.2f;
//        int i = 0;
//        matrices.push();
//        matrices.translate(0.0f, f, 0.0f);
//        //matrices.multiply(this.dispatcher.getRotation());
//        matrices.scale(-0.025f, -0.025f, 0.025f);
//        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
//        float g = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25f);
//        int j = (int)(g * 255.0f) << 24;
//        TextRenderer textRenderer = this.getTextRenderer();
//        float h = -textRenderer.getWidth(text) / 2;
//        textRenderer.draw(text, h, (float)i, 0x20FFFFFF, false, matrix4f, vertexConsumers, TextRenderer.TextLayerType.NORMAL, j, light);
//        if (bl) {
//            textRenderer.draw(text, h, (float)i, -1, false, matrix4f, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, light);
//        }
//        matrices.pop();
//    }
//}
