package com.nespresso.heating;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * This class is not an implementation of an HeatingManager interface or abstract class.
 * Thus, its name should just be HeatingManager or implement / extends the missing interface / class.
 * <p>
 * We will choose to rename the class, because adding another class or interface with only one function
 * to override will probably be overkill.
 */
public class HeatingManager {
    /*
     * The socket was previously created when the main function of the class was called.
     * Instead, we will create it once at the initialization of the class.
     */
    private final Socket socket;

    public HeatingManager() {
        Socket socket1;
        try {
            socket1 = new Socket("heater.home", 9999);
        } catch (IOException e) {
            socket1 = null;
            System.err.println("Socket cannot be initialized!");
        }
        socket = socket1;
    }

    /**
     * Send information about the current heat.
     * @param temperature the current temperature.
     * @param canBeActivated define if the heating system can be activated.
     */
    public void sendHeatingInformation(int temperature, boolean canBeActivated) {
        String message;
        /*
         * The whole if condition wasn't optimized before.
         * It can be simplified like that.
         */
        if (temperature < Constants.THRESHOLD && canBeActivated) {
            message = "on";
        } else {
            message = "off";
        }
        try {
            OutputStream os = socket.getOutputStream();
            os.write(message.getBytes());
            os.flush();
            os.close();
            socket.close();
        } catch (IOException ignored) {
            System.err.println("Cannot write on the socket!");
        }
    }
}
