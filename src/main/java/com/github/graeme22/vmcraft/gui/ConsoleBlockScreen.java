package com.github.graeme22.vmcraft.gui;

import java.io.IOException;

import com.github.graeme22.vmcraft.VMCraft;

import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.resources.I18n;

public class ConsoleBlockScreen extends Screen {

	// add radio button for local/remote
	protected TextFieldWidget usernameTxt;
   	protected TextFieldWidget passwordTxt;
   	protected TextFieldWidget hostnameTxt;
   	protected TextFieldWidget portTxt;
   	protected ToggleWidget sshBtn;
   	
   	public ConsoleBlockScreen() {
		super(NarratorChatListener.EMPTY);
	}

   	protected void init() {
   		this.addButton(new CheckboxButton(this.width / 2 - 40, this.height / 2 - 50, 80, 20, I18n.format("gui." + VMCraft.MOD_ID + ".toggle"), false));
   		// connect button
   		this.addButton(new Button(this.width / 2 - 40, this.height / 2 - 25, 80, 20, I18n.format("gui." + VMCraft.MOD_ID + ".connect"), $ -> this.connect()));
   		// quit button
   		this.addButton(new Button(this.width / 2 - 40, this.height / 2, 80, 20, I18n.format("gui.done"), $ -> this.minecraft.displayGuiScreen(null)));
   		super.init();
   	}
	   
   	private void connect() {
   		// launch GUI/CLI
   		try {
   			Runtime.getRuntime().exec("virt-viewer -cf qemu:///system varch");
   		} catch (IOException e) {
   			VMCraft.LOGGER.error("Failed to launch machine.");
			e.printStackTrace();
		}
   	}
	
   	@Override
   	public void render(int mouseX, int mouseY, float partialTicks) {
   		this.renderBackground();
   		super.render(mouseX, mouseY, partialTicks);
   	}
   
   	@Override
   	public boolean isPauseScreen() {
   		return true;
   	}
   	
}
