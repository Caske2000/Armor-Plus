package net.caske2000.armorplus.blocks;

import net.caske2000.armorplus.ArmorPlus;
import net.caske2000.armorplus.client.ArmorAchievements;
import net.caske2000.armorplus.client.gui.GuiHandler;
import net.caske2000.armorplus.items.CreativeTab;
import net.caske2000.armorplus.tileentities.TileEntityArmorTable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Caske2000 on 8-2-2016.
 */
public class BlockArmorTable extends BlockContainer
{
    public BlockArmorTable()
    {
        super(Material.rock);
        setUnlocalizedName("armorTable_block");
        setCreativeTab(CreativeTab.ARMOR_TAB);
        setHardness(1.0F);
        setStepSound(Block.soundTypeMetal);
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
        TileEntityArmorTable te = (TileEntityArmorTable) world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world, pos, te);
        super.breakBlock(world, pos, blockstate);
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        ((EntityPlayer) placer).triggerAchievement(ArmorAchievements.tinkerer);

        if (stack.hasDisplayName()) {
            ((TileEntityArmorTable) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(ArmorPlus.instance, GuiHandler.ARMOR_TABLE_GUI, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityArmorTable();
    }
}
