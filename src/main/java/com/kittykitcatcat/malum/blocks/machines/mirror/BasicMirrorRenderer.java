package com.kittykitcatcat.malum.blocks.machines.mirror;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;
import static net.minecraft.state.properties.AttachFace.*;
import static net.minecraft.state.properties.BlockStateProperties.*;

@OnlyIn(value = Dist.CLIENT)
public class BasicMirrorRenderer extends TileEntityRenderer<BasicMirrorTileEntity>
{

    public BasicMirrorRenderer(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(BasicMirrorTileEntity blockEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int light, int overlay)
    {
        if (this.renderDispatcher.renderInfo != null && blockEntity.getDistanceSq(this.renderDispatcher.renderInfo.getProjectedView().x, this.renderDispatcher.renderInfo.getProjectedView().y, this.renderDispatcher.renderInfo.getProjectedView().z) < 128d)
        {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            ItemStack item = blockEntity.inventory.getStackInSlot(0);
            if (!item.isEmpty())
            {
                BlockState blockState = blockEntity.getBlockState();
                Vec3i direction = blockEntity.getBlockState().get(HORIZONTAL_FACING).getDirectionVec();
                matrixStack.push();
                float directionMultiplier = 0.3f;
                if (blockState.get(FACE) == FLOOR || blockState.get(FACE) == CEILING)
                {
                    directionMultiplier = 0f;
                }
                matrixStack.translate(0.5f - direction.getX() * directionMultiplier, 0.5f, 0.5f - direction.getZ() * directionMultiplier);
                matrixStack.rotate(Vector3f.YP.rotationDegrees(blockEntity.getWorld().getGameTime() * 3));
                matrixStack.scale(0.45f, 0.45f, 0.45f);
                itemRenderer.renderItem(item, ItemCameraTransforms.TransformType.FIXED, light, NO_OVERLAY, matrixStack, iRenderTypeBuffer);
                matrixStack.pop();
            }
        }
    }
}