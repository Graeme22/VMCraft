package com.github.graeme22.vmcraft.gui;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import com.github.graeme22.vmcraft.VMCraft;

import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.resources.I18n;

public class ConsoleBlockScreen extends Screen {

	// add radio button for local/remote
	protected SuggestionTextFieldWidget machineTxt, usernameTxt, passwordTxt, hostnameTxt, portTxt;
   	protected CheckboxButton sshBtn;
   	
   	public ConsoleBlockScreen() {
		super(NarratorChatListener.EMPTY);
	}

   	protected void init() {
   		// local/remote toggle
   		this.sshBtn = this.addButton(new CheckboxButton(this.width / 2 - 80 - 5, this.height / 2 - 80, 80, 20, I18n.format("gui." + VMCraft.MOD_ID + ".toggle"), false));
   		// VM name field
   		this.machineTxt = this.addButton(new SuggestionTextFieldWidget(font, this.width / 2 + 5, this.height / 2 - 80, 80, 20, "machine"));
   		this.machineTxt.setSuggestion("VM name");
   		
   		// host name field
   		this.hostnameTxt = this.addButton(new SuggestionTextFieldWidget(font, this.width / 2 - 80 - 5, this.height / 2 - 50, 120, 20, "host"));
   		this.hostnameTxt.setSuggestion("hostname");
   		// port field
   		this.portTxt = this.addButton(new SuggestionTextFieldWidget(font, this.width / 2 + 5 + 40, this.height / 2 - 50, 40, 20, "port"));
   		this.portTxt.setSuggestion("port");
   		this.portTxt.setNumeric();
   		// user name field
   		this.usernameTxt = this.addButton(new SuggestionTextFieldWidget(font, this.width / 2 - 80 - 5, this.height / 2 - 20, 80, 20, "user"));
   		this.usernameTxt.setSuggestion("username");
   		// password field
   		this.passwordTxt = this.addButton(new SuggestionTextFieldWidget(font, this.width / 2 + 5, this.height / 2 - 20, 80, 20, "password"));
   		this.passwordTxt.setSuggestion("password");
   		this.passwordTxt.setPassword();
   		// connect button
   		this.addButton(new Button(this.width / 2 - 80 - 5, this.height / 2 + 10, 80, 20, I18n.format("gui." + VMCraft.MOD_ID + ".connect"), $ -> this.connect()));
   		// quit button
   		this.addButton(new Button(this.width / 2 + 5, this.height / 2 + 10, 80, 20, I18n.format("gui.done"), $ -> this.minecraft.displayGuiScreen(null)));
   		super.init();
   	}
	   
   	private void connect() {
   		// launch GUI/CLI
   		try {
   			File f = File.createTempFile("tmp", "vv");
   			f.deleteOnExit();
   			FileWriter out = new FileWriter(f.getAbsolutePath());
   			
   			// write connection info to file
   			out.write("[virt-viewer]\n");
   			out.write("type=vnc\n");
   			out.write(String.format("host=%s\n", this.hostnameTxt.getText()));
   			out.write(String.format("port=%s\n", this.portTxt.getText()));
   			out.write(String.format("password=%s\n", this.passwordTxt.getText()));
   			//out.write(String.format("", null));
   			out.write("title=VMCraft Client\n");
   			out.write("fullscreen=0\n");
   			out.close();
   			
   			String command = String.format("remote-viewer %s", f.getAbsolutePath());
   			Runtime.getRuntime().exec(command);
   		} catch (IOException e) {
   			VMCraft.LOGGER.error("Failed to connect to virtual machine.");
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
