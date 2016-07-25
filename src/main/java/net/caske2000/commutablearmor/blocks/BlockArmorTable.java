package net.caske2000.commutablearmor.blocks;

import net.caske2000.commutablearmor.CommutableArmor;
import net.caske2000.commutablearmor.achievements.ArmorAchievements;
import net.caske2000.commutablearmor.handler.GuiHandler;
import net.caske2000.commutablearmor.items.CreativeTab;
import net.caske2000.commutablearmor.tileentities.TileEntityArmorTable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Caske2000 on 8-2-2016.
 */
class BlockArmorTable extends BlockContainer {
    public BlockArmorTable() {
        super(Material.ROCK);
        setUnlocalizedName("armorTable_block");
        setRegistryName("armorTable_block");
        setCreativeTab(CreativeTab.ARMOR_TAB);
        setHardness(1.0F);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
        TileEntityArmorTable te = (TileEntityArmorTable) world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world, pos, te);
        super.breakBlock(world, pos, blockstate);
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        ((EntityPlayer) placer).addStat(ArmorAchievements.tinkerer);

        if (stack.hasDisplayName()) {
            ((TileEntityArmorTable) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(CommutableArmor.instance, GuiHandler.ARMOR_TABLE_GUI, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityArmorTable();
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
