package com.github.graeme22.vmcraft.gui;

import java.io.File;
import java.io.FileWriter;

import com.github.graeme22.vmcraft.VMCraft;

import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.resources.I18n;

public class ConsoleBlockScreen extends Screen {

	protected SuggestionTextFieldWidget machineTxt, usernameTxt, passwordTxt, hostnameTxt, portTxt;
   	protected CheckboxButton sshBtn;
   	protected Button typeButton;
   	
   	// whether or not we're using the SPICE protocol.
   	// if we aren't, we're using VNC.
   	private boolean isSpice = false;
   	
   	public ConsoleBlockScreen() {
		super(NarratorChatListener.EMPTY);
	}

   	protected void init() {
   		// host name field
   		this.hostnameTxt = this.addButton(new SuggestionTextFieldWidget(font, this.width / 2 - 80 - 5, this.height / 2 - 50, 120, 20, "hostname"));
   		this.hostnameTxt.setSuggestion(I18n.format("gui." + VMCraft.MOD_ID + ".host"));
   		// port field
   		this.portTxt = this.addButton(new SuggestionTextFieldWidget(font, this.width / 2 + 5 + 40, this.height / 2 - 50, 40, 20, "port").setNumeric());
   		this.portTxt.setSuggestion(I18n.format("gui." + VMCraft.MOD_ID + ".port"));
   		// user name field
   		this.usernameTxt = this.addButton(new SuggestionTextFieldWidget(font, this.width / 2 - 80 - 5, this.height / 2 - 20, 80, 20, "username"));
   		this.usernameTxt.setSuggestion(I18n.format("gui." + VMCraft.MOD_ID + ".user"));
   		// password field
   		this.passwordTxt = this.addButton(new SuggestionTextFieldWidget(font, this.width / 2 + 5, this.height / 2 - 20, 80, 20, "password").setPassword());
   		this.passwordTxt.setSuggestion(I18n.format("gui." + VMCraft.MOD_ID + ".password"));
   		// protocol button
   		this.typeButton = this.addButton(new Button(this.width / 2 - 80 - 5, this.height / 2 + 8, 52, 20, "VNC", $ -> this.toggle()));
   		// connect button
   		this.addButton(new Button(this.width / 2 - 27, this.height / 2 + 8, 52, 20, I18n.format("gui." + VMCraft.MOD_ID + ".connect"), $ -> this.connect()));
   		// quit button
   		this.addButton(new Button(this.width / 2 + 26 + 5, this.height / 2 + 8, 52, 20, I18n.format("gui." + VMCraft.MOD_ID + ".done"), $ -> this.minecraft.displayGuiScreen(null)));
   		super.init();
   	}
   	
   	/**
   	 * toggles between VNC and SPICE.
   	 */
   	private void toggle() {
   		this.isSpice = !this.isSpice;
   		if(this.isSpice)
   			this.typeButton.setMessage("SPICE");
   		else
   			this.typeButton.setMessage("VNC");
   	}
	   
   	private void connect() {
   		// launch GUI/CLI
   		try {
   			File f = File.createTempFile("tmp", "vv");
   			FileWriter out = new FileWriter(f.getAbsolutePath());
   			
   			// write connection info to .vv file
   			out.write("[virt-viewer]\n");
   			out.write(String.format("type=%s\n", (this.isSpice ? "spice" : "vnc")));
   			// only write these portions if they contain something
   			if(this.hostnameTxt.getText() != "")
   				out.write(String.format("host=%s\n", this.hostnameTxt.getText()));
   			if(this.portTxt.getText() != "")
   				out.write(String.format("port=%s\n", this.portTxt.getText()));
   			if(this.usernameTxt.getText() != "")
   				out.write(String.format("username=%s\n", this.usernameTxt.getText()));
   			if(this.passwordTxt.getText() != "")
   				out.write(String.format("password=%s\n", this.passwordTxt.getText()));
   			out.write("fullscreen=1\n");
   			out.write("delete-this-file=1\n");
   			out.close();
   			
   			String command = String.format("remote-viewer %s", f.getAbsolutePath());
   			Runtime.getRuntime().exec(command);
   		}
   		// fail gracefully
   		catch (Exception e) {
   			VMCraft.LOGGER.error("Failed to connect to virtual machine.");
			e.printStackTrace();
		}
   	}
	
   	@Override
   	public void render(int mouseX, int mouseY, float partialTicks) {
   		this.renderBackground();
   		super.render(mouseX, mouseY, partialTicks);
   	}
   
   	/**
   	 * opening the menu will pause the game in single-player games
   	 */
   	@Override
   	public boolean isPauseScreen() {
   		return true;
   	}
   	
}
