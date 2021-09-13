package rr.snowhack.snow.mixin.client;

import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rr.snowhack.snow.module.ModuleManager;

@Mixin({BlockLiquid.class})
public class MixinBlockLiquid {
   @Inject(
      method = {"modifyAcceleration"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void modifyAcceleration(World worldIn, BlockPos pos, Entity entityIn, Vec3d motion, CallbackInfoReturnable returnable) {
      if (ModuleManager.isModuleEnabled("Velocity")) {
         returnable.setReturnValue(motion);
         returnable.cancel();
      }

   }
}
