package me.NinetyNine.totd.utils;

import org.bukkit.event.Listener;

public class TOTDBooleanHandler implements Listener {

	private static boolean openBook = true;
	
	public static void setOpenBook(boolean b) {
		openBook = b;
	}
	
	public static boolean getOpenBook() {
		return openBook;
	}
}