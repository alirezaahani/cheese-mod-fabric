package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.blocks.CheeseCauldron;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CaveVinesHeadBlock;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {

	public static final String MOD_ID = "cheese_mod";
	public static Item CHEESE_ITEM;
	public static Block CHEESE_CAULDRON;

	@Override
	public void onInitialize() {
		CHEESE_ITEM = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cheese"), new Item(new FabricItemSettings().group(ItemGroup.FOOD))); // TODO: Add proper cheese item class and functionality
		CHEESE_CAULDRON = Registry.register(Registry.BLOCK, new Identifier(MOD_ID,"cheese_cauldron"), new CheeseCauldron(FabricBlockSettings.of(Material.METAL).nonOpaque().requiresTool().strength(2.0F))); // TODO: Add block to minecraft:minable/pickaxe
	}
}
