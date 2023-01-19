package kolokviumski.kol2.zad4;


import java.util.*;

class Component {
    String color;
    int weight;

    Set<Component> components;

    public Component(String color, int weight) {
        this.color = color;
        this.weight = weight;
        components = new TreeSet<>(Comparator.comparing(Component::getWeight).thenComparing(Component::getColor));
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    void addComponent(Component component){
        components.add(component);
    }

    public String tString(String spaces) {
        String s = String.format("%s%d:%s\n", spaces, weight, color);
        for (Component component : components) {
            s+=component.tString(spaces + "---" );
        }
        return s;
    }

    @Override
    public String toString() {
        return tString("");
    }

    public void changeColor(int weight, String color) {
        if(this.weight<weight){
            this.color = color;
        }
        for (Component component : components) {
            component.changeColor(weight,color);
        }
    }
}


class Window{
    String name;
    //komponenti
    List<Component> listOfComponents;


    public Window(String name) {
        this.name = name;
        listOfComponents = new ArrayList<>();
        for(int i=0;i<300;i++){
            listOfComponents.add(null);
        }
    }

    void addComponent(int position, Component component) throws InvalidPositionException {

        if(listOfComponents.get(position-1)!=null){
            throw new InvalidPositionException(String.format("Invalid position %d, alredy taken!", position));
        }

        listOfComponents.set(position-1,component);
    }

    void changeColor(int weight, String color){
        for(int i=0;i<listOfComponents.size();i++){
            if(listOfComponents.get(i)!=null) {
                listOfComponents.get(i).changeColor(weight, color);
            }
        }
    }

    void swichComponents(int pos1, int pos2){
        Component tmp = listOfComponents.get(pos1-1);

        listOfComponents.set(pos1-1, listOfComponents.get(pos2-1));
        listOfComponents.set(pos2-1, tmp);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WINDOW ").append(name).append("\n");
        for(int i=0;i<listOfComponents.size();i++){
            if(listOfComponents.get(i)!=null){
                sb.append(i+1).append(":");
                sb.append(listOfComponents.get(i));
            }
        }
        return sb.toString();
    }
}

class InvalidPositionException extends Exception{
    public InvalidPositionException(String mess) {
        super(mess);
    }
}









public class ComponentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Window window = new Window(name);
        Component prev = null;
        while (true) {
            try {
                int what = scanner.nextInt();
                scanner.nextLine();
                if (what == 0) {
                    int position = scanner.nextInt();
                    window.addComponent(position, prev);
                } else if (what == 1) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev = component;
                } else if (what == 2) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                    prev = component;
                } else if (what == 3) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                } else if(what == 4) {
                    break;
                }

            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();
        }

        System.out.println("=== ORIGINAL WINDOW ===");
        System.out.println(window);
        int weight = scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
        window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
        System.out.println(window);
        int pos1 = scanner.nextInt();
        int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
        window.swichComponents(pos1, pos2);
        System.out.println(window);
    }
}

// вашиот код овде
