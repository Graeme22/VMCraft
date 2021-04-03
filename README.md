# VMCraft
Enterprise virtualization enabling mod for Minecraft

## Intallation
1. Install [virt-viewer](http://virt-manager.org/download/)
2. Add the `bin` folder of your virt-viewer install to your PATH environment variable.
3. Place the jarfile in your Minecraft Forge `mods` folder.

## Usage
TODO

## Setup (Developers)
1. Install JDK 8, e.g. `sudo apt-get install openjdk-8-jdk`
2. Install Eclipse from http://eclipse.org
3. Clone the repository: `git clone https://github.com/Graeme22/VMCraft.git`
4. Navigate to the folder and run `./gradlew genEclipseRuns`
5. Run `./gradlew eclipse` to setup the workspace.
6. In eclipse, do File > Import > Existing Gradle Project, then follow the steps in the wizard.
7. Open Run > Run Configurations... > runClient > Environment. Edit the MOD_CLASSES field and replace all instances of "examplemod" with "vmcraft".
8. Click apply. You should now be able to run the project by clicking the green run button in eclipse.
