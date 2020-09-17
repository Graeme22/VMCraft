# VMCraft
Enterprise virtualization enabling mod for Minecraft

## Setup
1. Install JDK 8: `sudo apt-get install openjdk-8-jdk`
2. Install Eclipse from http://eclipse.org
3. Clone the repository: `git clone https://github.com/Graeme22/VMCraft.git`
4. Navigate to the folder and run `./gradlew genEclipseRuns`
5. Run `./gradlew eclipse` to setup the workspace.
6. In eclipse, do File > Import > Existing Gradle Project, then follow the steps in the wizard.
7. Open Run > Run Configurations... > runClient > Environment. Edit the MOD_CLASSES field and replace the 
