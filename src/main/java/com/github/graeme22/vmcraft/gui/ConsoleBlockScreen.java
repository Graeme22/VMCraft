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
	protected TextFieldWidget machineTxt;
	protected TextFieldWidget usernameTxt;
   	protected TextFieldWidget passwordTxt;
   	protected TextFieldWidget hostnameTxt;
   	protected TextFieldWidget portTxt;
   	protected CheckboxButton sshBtn;
   	
   	public ConsoleBlockScreen() {
		super(NarratorChatListener.EMPTY);
	}

   	protected void init() {
   		// local/remote toggle
   		this.sshBtn = this.addButton(new CheckboxButton(this.width / 2 - 80 - 5, this.height / 2 - 80, 80, 20, I18n.format("gui." + VMCraft.MOD_ID + ".toggle"), false));
   		// VM name field
   		this.machineTxt = this.addButton(new TextFieldWidget(font, this.width / 2 + 5, this.height / 2 - 80, 80, 20, "VM"));
   		this.machineTxt.setText("VM");
   		// host name field
   		this.hostnameTxt = this.addButton(new TextFieldWidget(font, this.width / 2 - 80 - 5, this.height / 2 - 50, 80, 20, "hostname"));
   		this.hostnameTxt.setText("hostname");
   		// port field
   		this.portTxt = this.addButton(new TextFieldWidget(font, this.width / 2 + 5, this.height / 2 - 50, 80, 20, "port"));
   		this.portTxt.setText("port");
   		// user name field
   		this.usernameTxt = this.addButton(new TextFieldWidget(font, this.width / 2 - 80 - 5, this.height / 2 - 20, 80, 20, "username"));
   		this.usernameTxt.setText("username");
   		// password field
   		this.passwordTxt = this.addButton(new TextFieldWidget(font, this.width / 2 + 5, this.height / 2 - 20, 80, 20, "password"));
   		this.passwordTxt.setText("password");
   		// connect button
   		this.addButton(new Button(this.width / 2 - 80 - 5, this.height / 2 + 10, 80, 20, I18n.format("gui." + VMCraft.MOD_ID + ".connect"), $ -> this.connect()));
   		// quit button
   		this.addButton(new Button(this.width / 2 + 5, this.height / 2 + 10, 80, 20, I18n.format("gui.done"), $ -> this.minecraft.displayGuiScreen(null)));
   		super.init();
   	}
	   
   	private void connect() {
   		// launch GUI/CLI
   		try {
   			String command = String.format("virt-viewer -cf qemu%s:///system %s", (sshBtn.isChecked() ? "+ssh" : ""), this.machineTxt.getText());
   			Runtime.getRuntime().exec(command);
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
