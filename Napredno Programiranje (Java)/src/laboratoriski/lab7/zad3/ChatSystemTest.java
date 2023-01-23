package laboratoriski.lab7.zad3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.TreeSet;
import java.util.stream.Collectors;


class ChatRoom{
    private String name;
    Set<String> users;

    public ChatRoom(String name){
        this.name = name;
        users = new TreeSet<>();
    }

    public void addUser(String username){
        users.add(username);
    }

    public void removeUser(String username){
        users.remove(username);
    }

    public boolean hasUser(String username){
        return users.contains(username);
    }

    public int numUsers(){
        return users.size();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name).append("\n");

        if(users.size()==0){
            sb.append("EMPTY\n");
            return sb.toString();
        }

        users.forEach(user->{
            sb.append(user).append("\n");
        });

        return sb.toString();
    }
}


class ChatSystem{
    Map<String, ChatRoom> roomsByName;
    Map<String, List<ChatRoom>> roomsByUsers;

    public ChatSystem() {
        roomsByName = new TreeMap<>();
        roomsByUsers = new TreeMap<>();
    }

    public void addRoom(String roomName){
        roomsByName.put(roomName, new ChatRoom(roomName));
    }

    public void removeRoom(String roomName){
        roomsByName.remove(roomName);
    }

    public ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        if(!roomsByName.containsKey(roomName)){
            throw new NoSuchRoomException(roomName);
        }

        return roomsByName.get(roomName);
    }

    public void register(String userName){
        roomsByUsers.putIfAbsent(userName, new ArrayList<>());

        if(roomsByName.size()==0){
            return;
        }

        int minNumber = roomsByName.values().stream()
                .mapToInt(ChatRoom::numUsers)
                .min()
                .orElse(0);
        roomsByName.values().stream()
                .filter(room->room.numUsers()==minNumber)
                .limit(1)
                .forEach(p->{
                    p.addUser(userName);
                    roomsByUsers.get(userName).add(p);
                });
    }

    public void registerAndJoin(String userName, String roomName){
        roomsByName.get(roomName).addUser(userName);
        roomsByUsers.putIfAbsent(userName, new ArrayList<>());
        roomsByUsers.get(userName).add(roomsByName.get(roomName));
    }

    public void joinRoom(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException {
        if(!roomsByUsers.containsKey(userName)){
            throw new  NoSuchUserException(userName);
        }

        if(!roomsByName.containsKey(roomName)){
            throw new NoSuchRoomException(roomName);
        }

        roomsByName.get(roomName).addUser(userName);
        roomsByUsers.get(userName).add(roomsByName.get(roomName));
    }

    public void leaveRoom(String username, String roomName) throws NoSuchUserException, NoSuchRoomException {
        if(!roomsByUsers.containsKey(username)){
            throw new  NoSuchUserException(username);
        }

        if(!roomsByName.containsKey(roomName)){
            throw new NoSuchRoomException(roomName);
        }

        roomsByName.get(roomName).removeUser(username);
        roomsByUsers.get(username).remove(roomsByName.get(roomName));
    }

    public void followFriend(String username, String friend_username) throws NoSuchUserException {
        List<ChatRoom> listOfRooms = roomsByUsers.get(friend_username);

        for(int i=0;i<listOfRooms.size();i++){
            listOfRooms.get(i).addUser(username);
            roomsByUsers.get(username).add(listOfRooms.get(i));
        }


    }


}


class NoSuchRoomException extends Exception {
    public NoSuchRoomException(String roomName) {
        super("No room " + roomName);
    }
}

class NoSuchUserException extends Exception {
    public NoSuchUserException(String userName) {
        super("No user " + userName);
    }
}









public class ChatSystemTest {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) {
            ChatRoom cr = new ChatRoom(jin.next());
            int n = jin.nextInt();
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr.addUser(jin.next());
                if ( k == 1 ) cr.removeUser(jin.next());
                if ( k == 2 ) System.out.println(cr.hasUser(jin.next()));
            }
            System.out.println("");
            System.out.println(cr.toString());
            n = jin.nextInt();
            if ( n == 0 ) return;
            ChatRoom cr2 = new ChatRoom(jin.next());
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr2.addUser(jin.next());
                if ( k == 1 ) cr2.removeUser(jin.next());
                if ( k == 2 ) cr2.hasUser(jin.next());
            }
            System.out.println(cr2.toString());
        }
        if ( k == 1 ) {
            ChatSystem cs = new ChatSystem();
            Method mts[] = cs.getClass().getMethods();
            while ( true ) {
                String cmd = jin.next();
                if ( cmd.equals("stop") ) break;
                if ( cmd.equals("print") ) {
                    try {
                        System.out.println(cs.getRoom(jin.next())+"\n");
                    } catch (NoSuchRoomException e) {
                        System.out.println(e.getMessage());
                    }
                    continue;
                }
                for ( Method m : mts ) {
                    if ( m.getName().equals(cmd) ) {
                        String params[] = new String[m.getParameterTypes().length];
                        for ( int i = 0 ; i < params.length ; ++i ) params[i] = jin.next();
                        m.invoke(cs, params);
                    }
                }
            }
        }
    }

}


