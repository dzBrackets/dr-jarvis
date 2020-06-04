package libs;

import javafx.scene.paint.Color;

import java.nio.charset.StandardCharsets;

public class helper {
    static public String colorToRgba(Color color){
        return String.format("rgba(%d, %d, %d, %f)",
                (int) (255 * color.getRed()),
                (int) (255 * color.getGreen()),
                (int) (255 * color.getBlue()),
                color.getOpacity());
    }
    static public String byteString(String str){
        byte[] bt = str.getBytes(StandardCharsets.UTF_8);
        return new String(bt);

    }
    static public String byteStringUTF(String str){
        byte[] bt = str.getBytes(StandardCharsets.UTF_8);
        return new String(bt);

    }
}
