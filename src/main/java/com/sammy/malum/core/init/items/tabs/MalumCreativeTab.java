package com.sammy.malum.core.init.items.tabs;

import com.sammy.malum.MalumMod;
import com.sammy.malum.core.init.items.MalumItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class MalumCreativeTab extends ItemGroup
{
    public static final MalumCreativeTab INSTANCE = new MalumCreativeTab();
    
    public MalumCreativeTab() {
        super(MalumMod.MODID);
    }
    
    @Nonnull
    @Override
    public ItemStack createIcon() {
        return new ItemStack(MalumItems.SPIRIT_ALTAR.get());
    }
}
