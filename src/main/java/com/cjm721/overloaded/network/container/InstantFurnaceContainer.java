package com.cjm721.overloaded.network.container;

import com.cjm721.overloaded.block.ModBlocks;
import com.cjm721.overloaded.storage.energy.ForgeEnergyDataUpdateWrapper;
import com.cjm721.overloaded.tile.functional.TileInstantFurnace;
import com.cjm721.overloaded.util.ContainerUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;

import javax.annotation.Nonnull;

import static net.minecraftforge.energy.CapabilityEnergy.ENERGY;

public class InstantFurnaceContainer extends Container {

  private final PlayerInventory playerInventory;
  private final TileInstantFurnace instanceFurnace;
  private final IntReferenceHolder power;
  private final IntReferenceHolder maxPower;

  public InstantFurnaceContainer(int id, PlayerInventory playerInventory) {
    this(id, playerInventory, new TileInstantFurnace());
  }

  public InstantFurnaceContainer(
      int id, PlayerInventory playerInventory, TileInstantFurnace instanceFurnace) {
    super(ModContainers.INSTANT_FURNACE, id);
    this.playerInventory = playerInventory;
    this.instanceFurnace = instanceFurnace;
    this.power =
        new IntReferenceHolder() {

          @Override
          public int get() {
            return getPowerFromTE();
          }

          @Override
          public void set(int amount) {
            InstantFurnaceContainer.this.instanceFurnace
                .getCapability(ENERGY)
                .ifPresent(e -> ((ForgeEnergyDataUpdateWrapper) e).setEnergy(amount));
          }
        };
    maxPower = IntReferenceHolder.single();

    int slotCount = 0;
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        this.addSlot(new Slot(instanceFurnace, slotCount++, 8 + j * 18, 8 + i * 18));
      }
    }

    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        this.addSlot(
            new FurnaceResultSlot(
                playerInventory.player, instanceFurnace, slotCount++, 116 + j * 18, 8 + i * 18));
      }
    }

    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 9; ++j) {
        this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
      }
    }

    for (int k = 0; k < 9; ++k) {
      this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
    }

    this.trackInt(power);
    this.trackInt(maxPower).set(getMaxPowerFromTE());
  }

  @Override
  @Nonnull
  public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
    return ContainerUtil.transferStackInSlot(playerIn, index, this);
  }

  @Override
  public boolean canInteractWith(PlayerEntity playerIn) {
    return isWithinUsableDistance(
        IWorldPosCallable.of(instanceFurnace.getWorld(), instanceFurnace.getPos()),
        playerIn,
        ModBlocks.instantFurnace);
  }

  public int getPower() {
    return power.get();
  }

  public int getMaxPower() {
    return maxPower.get();
  }

  private int getPowerFromTE() {
    return instanceFurnace.getCapability(ENERGY).map(e -> e.getEnergyStored()).orElse(0);
  }

  private int getMaxPowerFromTE() {
    return instanceFurnace.getCapability(ENERGY).map(e -> e.getMaxEnergyStored()).orElse(1);
  }
}