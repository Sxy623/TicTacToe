package client;

import model.*;
import view.*;

public class User {
	
	public static Client client;
	
	public static String userName;
	public static Player player;
	
	public static ChatboardView chatboardView;
	public static ChessboardView chessboardView;
	
	public static void createClient() {
		
		client = new Client(userName);
		
	}
}
