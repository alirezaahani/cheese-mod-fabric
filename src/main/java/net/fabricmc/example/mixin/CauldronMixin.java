package net.fabricmc.example.mixin;

import net.fabricmc.example.blocks.CheeseCauldron;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.fabricmc.example.ExampleMod.CHEESE_CAULDRON;
import static net.fabricmc.example.blocks.CheeseCauldron.TIME;

@Mixin(AbstractCauldronBlock.class)
public class CauldronMixin {
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/block/AbstractCauldronBlock;onUse(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;", cancellable = true)
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> ci) {
        if(player.getStackInHand(hand).getItem() == Items.MILK_BUCKET) {
            player.setStackInHand(hand, Items.BUCKET.getDefaultStack());
            world.setBlockState(pos, CHEESE_CAULDRON.getDefaultState().with(TIME, 1));
        }
        ci.setReturnValue(ActionResult.SUCCESS);
    }
}
