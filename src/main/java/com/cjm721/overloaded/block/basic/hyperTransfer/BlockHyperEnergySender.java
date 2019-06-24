package com.cjm721.overloaded.block.basic.hyperTransfer;

import com.cjm721.overloaded.block.basic.hyperTransfer.base.AbstractBlockHyperSender;
import com.cjm721.overloaded.block.tile.hyperTransfer.TileHyperEnergySender;
import com.cjm721.overloaded.client.render.dynamic.ImageUtil;
import com.cjm721.overloaded.config.OverloadedConfig;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

import static com.cjm721.overloaded.Overloaded.MODID;

public class BlockHyperEnergySender extends AbstractBlockHyperSender {

  public BlockHyperEnergySender() {
    super(getDefaultProperties());
  }

  @Override
  public void baseInit() {
    setRegistryName("hyper_energy_sender");
    //        setTranslationKey("hyper_energy_sender");

    //        GameRegistry.registerTileEntity(TileHyperEnergySender.class, MODID +
    // ":hyper_energy_sender");
  }

  @Override
  @Nonnull
  public String getType() {
    return "Energy";
  }

  @Override
  @Nonnull
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return new TileHyperEnergySender();
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void registerModel() {
    super.registerModel();
    ImageUtil.registerDynamicTexture(
        new ResourceLocation(MODID, "textures/blocks/hyper_energy_sender.png"),
        OverloadedConfig.INSTANCE.textureResolutions.blockResolution);
  }
}
