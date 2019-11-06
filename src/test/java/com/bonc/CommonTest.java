package com.bonc;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public class CommonTest {
	@Test
	public void testBrowser() throws Exception {
		System.out.println("lkk");
		Desktop desktop = Desktop.getDesktop();
        if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
            URI uri = new URI("http://localhost:8080/test");
//        	URI uri = new URI("www.baidu.com");
            desktop.browse(uri);
            System.out.println("ltl");
        }
	}
}
