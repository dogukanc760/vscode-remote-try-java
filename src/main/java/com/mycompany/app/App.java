/*----------------------------------------------------------------------------------------
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *---------------------------------------------------------------------------------------*/

package com.mycompany.app;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
public class App {
    public static void main(String[] args) {
        try {
            String serverUrl = "http://44.218.14.105:3008";
            Socket socket = IO.socket(serverUrl);
            
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("Connected to server");
                }
            }).on("tick-data", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    // Handle tick data here
                    System.out.println("Received tick data: " + args[0]);
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("Disconnected from server");
                }
            });
            
            socket.connect();
            
            // Wait for user interaction to keep the program running
            System.in.read();
            
            socket.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
