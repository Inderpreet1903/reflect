package jp.co.reflect.tests.appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

public class AppiumSetup {
    private static AppiumDriverLocalService service;

    public static void serviceBuilder() {

        AppiumServiceBuilder builder;

        builder = new AppiumServiceBuilder();
        builder.withIPAddress("0.0.0.0");
        // Use any port, in case the default 4723 is already taken (maybe by another Appium server)
        builder.usingPort(Integer.parseInt(System.getProperty("appium.port")));
        // Tell serviceBuilder where node is installed. Or set this path in an environment variable named NODE_PATH
        builder.usingDriverExecutable(new File("/usr/local/bin/node"));
        // Tell serviceBuilder where Appium is installed. Or set this path in an environment variable named APPIUM_PATH
        builder.withAppiumJS(new File("/usr/local/bin/appium"));
        // The XCUITest driver requires that a path to the Carthage binary is in the PATH variable. I have this set for my shell, but the Java process does not see it. It can be inserted here.
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        final AppiumServiceBuilder appiumServiceBuilder = builder.withEnvironment(environment);

        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        service = AppiumDriverLocalService.buildService(builder);
    }

    public static AppiumDriverLocalService getAppiumService() {
        return service;
    }

    public static void stopService() {
        service.stop();
    }

    public static boolean checkAppiumRunning() {

        boolean serverIsRunning = false;

        ServerSocket socket;

        try {
            socket = new ServerSocket(Integer.valueOf(System.getProperty("appium.port")));
            socket.close();
        } catch (IOException e) {

            serverIsRunning = true;
        } finally {
            socket = null;
        }

        return serverIsRunning;
    }


}
