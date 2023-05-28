package org.tbuddies;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map.Entry;

import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import org.tbuddies.helpers.DateHelper;


public class Main {
    private static boolean run = true;
    public static void main(String[] args) {

        GlobalMouseHook mouseHook = new GlobalMouseHook();

        System.out.println("Global mouse hook successfully started, press [middle] mouse button to shutdown. Connected mice:");

        for (Entry<Long,String> mouse:GlobalMouseHook.listMice().entrySet()) {
            System.out.format("%d: %s\n", mouse.getKey(), mouse.getValue());
            System.out.print(LocalTime.of(10, 0, 0));
        }

        mouseHook.addMouseListener(new GlobalMouseAdapter() {

            @Override
            public void mousePressed(GlobalMouseEvent event)  {
                System.out.println(event);
                if ((event.getButtons() & GlobalMouseEvent.BUTTON_LEFT) != GlobalMouseEvent.BUTTON_NO
                        && (event.getButtons() & GlobalMouseEvent.BUTTON_RIGHT) != GlobalMouseEvent.BUTTON_NO) {
                    System.out.println("Both mouse buttons are currently pressed!");
                }
                if (event.getButton()==GlobalMouseEvent.BUTTON_MIDDLE) {
                    run = false;
                }
            }

            @Override
            public void mouseReleased(GlobalMouseEvent event)  {
                System.out.println(event);
            }

            @Override
            public void mouseMoved(GlobalMouseEvent event) {
                System.out.println("Mouse Moved at " + DateHelper.getUTCTicks());
                System.out.println(event);
            }

            @Override
            public void mouseWheel(GlobalMouseEvent event) {
                System.out.println("Mouse Wheel at " + DateHelper.getUTCTicks());
                System.out.println(event);
            }
        });

        try {
            while(run) {
                Thread.sleep(128);
            }
        } catch(InterruptedException e) {
            //Do nothing
        } finally {
            mouseHook.shutdownHook();
        }
    }
}