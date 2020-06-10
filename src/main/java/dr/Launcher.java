package dr;

import com.sun.javafx.application.LauncherImpl;

public class Launcher {
    public static void main(String[]args){

        LauncherImpl.launchApplication(Main.class, preLoader.class, args);

    }
}
