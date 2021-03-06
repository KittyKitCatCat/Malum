package com.sammy.malum.core.init.items.tabs;

import com.sammy.malum.MalumMod;
import com.sammy.malum.core.init.items.MalumItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class MalumSplinterTab extends ItemGroup
{
    public static final MalumSplinterTab INSTANCE = new MalumSplinterTab();
    
    public MalumSplinterTab() {
        super(MalumMod.MODID + "_spirits");
    }
    
    @Nonnull
    @Override
    public ItemStack createIcon() {
        return new ItemStack(MalumItems.ARCANE_SPIRIT.get());
    }
}