import java.text.DecimalFormat;
import java.util.Scanner;
/**
 * 
 * Application to read songs from keyboard
 * @author Nik Morales
 *
 */
public class PlayList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String title;
		String artist;
		String playTime;
		String fileName;
		int totalPlayTime;
		int min, sec;
		int colonIndex;
		double totalTime, diff1, diff2, diff3;
		String averageTime;


		Song song1, song2, song3;
		Song firstSong, midSong, lastSong;
		
		//song 1 title
		System.out.print("Enter title: ");
		title = scan.nextLine();
		//song 1 artist
		System.out.print("Enter artist: ");
		artist = scan.nextLine();
		//song 1 play time
		System.out.print("Enter play time (mm:ss): ");
		playTime = scan.nextLine();
		colonIndex = playTime.indexOf(':');
		min = Integer.parseInt(playTime.substring(0,colonIndex));
		sec = Integer.parseInt(playTime.substring(colonIndex+1));
		totalPlayTime = (min*60) +sec;
		//song 1 file name
		System.out.print("Enter file name: ");
		fileName = scan.nextLine();
		song1 = new Song(title, artist, totalPlayTime, fileName);
		
		System.out.println("\t");
		//song 2 title
		System.out.print("Enter title: ");
		title = scan.nextLine();
		//song 2 artist
		System.out.print("Enter artist: ");
		artist = scan.nextLine();
		//song 2 play time
		System.out.print("Enter play time (mm:ss): ");
		playTime = scan.nextLine();
		colonIndex = playTime.indexOf(':');
		min = Integer.parseInt(playTime.substring(0,colonIndex));
		sec = Integer.parseInt(playTime.substring(colonIndex+1));
		totalPlayTime = (min*60) +sec;
		//song 2 file name
		System.out.print("Enter file name: ");
		fileName = scan.nextLine();
		song2 = new Song(title, artist, totalPlayTime, fileName);
		
		System.out.println("\t");
		//song 3 title
		System.out.print("Enter title: ");
		title = scan.nextLine();
		//song 3 artist
		System.out.print("Enter artist: ");
		artist = scan.nextLine();
		//song 3 play time
		System.out.print("Enter play time (mm:ss): ");
		playTime = scan.nextLine();
		colonIndex = playTime.indexOf(':');
		min = Integer.parseInt(playTime.substring(0,colonIndex));
		sec = Integer.parseInt(playTime.substring(colonIndex+1));
		totalPlayTime = (min*60) +sec;
		//song 3 file name
		System.out.print("Enter file name: ");
		fileName = scan.nextLine();
		song3 = new Song(title, artist, totalPlayTime, fileName);
		
		//calculates the average play time of the songs
		totalTime = song1.getPlayTime() + song2.getPlayTime() + song3.getPlayTime();
		DecimalFormat df = new DecimalFormat("0.00");
		averageTime = df.format(totalTime/3);
		System.out.println("\t");
		System.out.println("Average playtime: " +averageTime+ " secounds");
		System.out.println("\t");
		
		//closest in play time relative to 240 secs
		diff1 = Math.abs(song1.getPlayTime()-240);
		diff2 = Math.abs(song2.getPlayTime()-240);
		diff3 = Math.abs(song3.getPlayTime()-240);
		
		//determines closest to play time to 240 secs
		if (diff1 < diff2 && diff1 < diff3)
		{
			System.out.println("Song with the play time closest to 240 secs is: " 
			+ song1.getTitle());
		}
		if (diff2 < diff1 && diff2 < diff3)
		{
			System.out.println("Song with the play time closest to 240 secs is: " 
			+ song2.getTitle());
		}
		if (diff3 < diff1 && diff3 < diff2)
		{
			System.out.println("Song with the play time closest to 240 secs is: " 
			+ song3.getTitle());
		}
		
		int song1Length = song1.getPlayTime();
		int song2Length = song2.getPlayTime();
		int song3Length = song3.getPlayTime();
	
		//first song
		if (song1Length < song2Length && song1Length < song3Length) 
		{
			firstSong = song1;
		}
		else if (song2Length < song1Length && song2Length < song3Length)
		{
			firstSong = song2;
		}
		else
		{
			firstSong = song3;
		}
		
		//last song
		if (song1Length > song2Length && song1Length  > song3Length)
		{
			lastSong = song1;
		}
		else if (song2Length > song1Length && song2Length > song3Length)
		{
			lastSong = song2;
		}
		else
		{
			lastSong = song3;
		}
		
		//mid song
		if ( (firstSong == song1 && lastSong == song3) || (firstSong == song3 && lastSong == song1) )
		{
			midSong = song2;
		}
		else if ((firstSong == song2 && lastSong == song3) || (firstSong == song3 && lastSong == song2))
		{
			midSong = song1;
		}
		else
		{
			midSong = song3;
		}
		System.out.println("==============================================================================");
		System.out.println("Title                Artist               File Name                  Play Time");
		System.out.println("==============================================================================");
		System.out.println(firstSong);
		System.out.println(midSong);
		System.out.println(lastSong);
		System.out.println("==============================================================================");
		
		scan.close();
	}

}
