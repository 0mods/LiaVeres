package com.algorithmlx.api.util.tools.helper

import net.minecraft.nbt.NbtUtils
import net.minecraft.world.item.ItemStack

object StackHelper {
    fun withSize(stack: ItemStack, size: Int, container: Boolean): ItemStack {
        var shadowed = stack

        if (size <= 0)
            return if (container && shadowed.hasContainerItem()) shadowed.containerItem
            else ItemStack.EMPTY

        shadowed = shadowed.copy()
        shadowed.count = size;

        return shadowed
    }

    fun grow(stack: ItemStack, amount: Int): ItemStack {
        return withSize(stack, stack.count + amount, false)
    }

    fun shrink(stack: ItemStack, amount: Int, container: Boolean): ItemStack {
        if (stack.isEmpty) return ItemStack.EMPTY
        return withSize(stack, stack.count - amount, container)
    }

    fun areItemsEqual(stack1: ItemStack, stack2: ItemStack): Boolean {
        return if (stack1.isEmpty && stack2.isEmpty) true else !stack1.isEmpty && ItemStack.isSame(stack1, stack2)
    }

    fun areStacksEqual(stack1: ItemStack, stack2: ItemStack): Boolean {
        return areItemsEqual(stack1, stack2) && ItemStack.isSameItemSameTags(stack1, stack2)
    }

    fun canCombineStacks(stack1: ItemStack, stack2: ItemStack): Boolean {
        return if (!stack1.isEmpty && stack2.isEmpty) true else areStacksEqual(
            stack1,
            stack2
        ) && stack1.count + stack2.count <= stack1.maxStackSize
    }

    fun combineStacks(stack1: ItemStack, stack2: ItemStack): ItemStack {
        return if (stack1.isEmpty) stack2.copy() else grow(stack1, stack2.count)
    }

    fun compareTags(stack1: ItemStack, stack2: ItemStack): Boolean {
        if (!stack1.hasTag()) return true
        if (stack1.hasTag() && !stack2.hasTag()) return false
        val stack1Keys = TagHelper.getTagCompound(stack1)!!.allKeys
        val stack2Keys = TagHelper.getTagCompound(stack2)!!.allKeys
        for (key in stack1Keys) {
            if (!stack2Keys.contains(key)) return false
            if (!NbtUtils.compareNbt(TagHelper.getTag(stack1, key), TagHelper.getTag(stack2, key), true)) return false
        }
        return true
    }
}