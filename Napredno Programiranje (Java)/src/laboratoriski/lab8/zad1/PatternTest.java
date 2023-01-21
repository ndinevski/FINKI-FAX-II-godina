package laboratoriski.lab8.zad1;

import java.util.ArrayList;
import java.util.List;

class Song{
    private String title, artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title=" + title +
                ", artist=" + artist +
                '}';
    }
}


class MP3Player{
    List<Song> songs;
    State stoppedState;
    State playingState;
    State currentState;
    Song currentSong;

    public MP3Player(List<Song> songs) {
        stoppedState = new StoppedState(this);
        playingState = new PlayingState(this);

        this.songs = songs;
        currentState = stoppedState;
        currentSong = songs.get(0);
    }

    public void setCurrentState(State state) {
        this.currentState = state;
    }

    public State getStoppedState() {
        return stoppedState;
    }

    public State getPlayingState() {
        return playingState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void reset(){
        currentState = stoppedState;
        currentSong = songs.get(0);
    }

    public void forward(){
        if(songs.indexOf(currentSong)==songs.size()-1){
            currentSong = songs.get(0);
            return;
        }
        currentSong = songs.get(songs.indexOf(currentSong)+1);
    }

    public void rewind(){
        if(songs.indexOf(currentSong)==0){
            currentSong = songs.get(songs.size()-1);
            return;
        }
        currentSong = songs.get(songs.indexOf(currentSong)-1);
    }

    public void pressPlay() {
        currentState.pressPlay(songs.indexOf(currentSong));
    }

    public void pressStop() {
        currentState.pressStop(songs.indexOf(currentSong));
    }

    public void pressFWD() {
        currentState.pressFWD();
    }

    public void pressREW() {
        currentState.pressREW();
    }

    public void printCurrentSong() {
        System.out.println(currentSong);
    }

    @Override
    public String toString() {
        return "MP3Player{" +
                "currentSong = " + songs.indexOf(currentSong) +
                ", songList = " + songs+
                '}';
    }
}

interface State{
    public void pressPlay(int song);
    public void pressStop(int song);
    public void pressFWD();
    public void pressREW();
}

class StoppedState implements State{
    MP3Player player;
    int flag=1;

    public StoppedState(MP3Player player) {
        this.player = player;
    }

    @Override
    public void pressPlay(int song) {
        System.out.println("Song "+ song+" is playing");
        player.setCurrentState(player.getPlayingState());
        flag=1;
    }

    @Override
    public void pressStop(int song) {
        if(flag==0){
            System.out.println("Songs are already stopped");
            return;
        }
        player.reset();
        System.out.println("Songs are stopped");
        flag=0;
    }

    @Override
    public void pressFWD() {
        player.forward();
        System.out.println("Forward...");
        flag=1;
    }

    @Override
    public void pressREW() {
        player.rewind();
        System.out.println("Reward...");
        flag=1;
    }
}

class PlayingState implements State{
    MP3Player player;

    public PlayingState(MP3Player player) {
        this.player = player;
    }

    @Override
    public void pressPlay(int song) {
        System.out.println("Song is already playing");
    }

    @Override
    public void pressStop(int song) {
        System.out.println("Song "+ song+" is paused");
        player.setCurrentState(player.getStoppedState());
    }

    @Override
    public void pressFWD() {
        player.forward();
        player.setCurrentState(player.getStoppedState());
        System.out.println("Forward...");
    }

    @Override
    public void pressREW() {
        player.rewind();
        player.setCurrentState(player.getStoppedState());
        System.out.println("Reward...");
    }
}









public class PatternTest {
    public static void main(String args[]) {
        List<Song> listSongs = new ArrayList<Song>();
        listSongs.add(new Song("first-title", "first-artist"));
        listSongs.add(new Song("second-title", "second-artist"));
        listSongs.add(new Song("third-title", "third-artist"));
        listSongs.add(new Song("fourth-title", "fourth-artist"));
        listSongs.add(new Song("fifth-title", "fifth-artist"));
        MP3Player player = new MP3Player(listSongs);


        System.out.println(player.toString());
        System.out.println("First test");


        player.pressPlay();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Second test");


        player.pressStop();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Third test");


        player.pressFWD();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
    }
}

//Vasiot kod ovde