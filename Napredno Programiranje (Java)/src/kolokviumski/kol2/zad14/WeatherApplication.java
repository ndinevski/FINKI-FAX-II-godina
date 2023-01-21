package kolokviumski.kol2.zad14;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class WeatherDispatcher {
    private float temperature, humidity, pressure;

    List<Observer> list;


    public WeatherDispatcher() {
        list = new ArrayList<>();
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;



        list.stream()
                .sorted(Comparator.comparing(Observer::getName))
                .forEach(observer -> {
            observer.update(temperature, humidity, pressure);
        });
        System.out.println();
    }


    public void register(Observer observer) {
        if (list.contains(observer)) {
            return;
        }
        list.add(observer);
    }

    public void remove(Observer observer){
        list.remove(observer);
    }


}


interface Observer{
    public void display();
    public void update(float temperature, float humidity, float pressure);
    public String getName();
}

class CurrentConditionsDisplay implements Observer{
    private float temperature, humidity;

    private WeatherDispatcher dispatcher;

    public CurrentConditionsDisplay(WeatherDispatcher dispatcher) {
        this.dispatcher = dispatcher;
        dispatcher.register(this);
    }

    @Override
    public void display() {
        System.out.printf("Temperature: %.1fF\nHumidity: %.1f%%\n", temperature, humidity);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    @Override
    public String getName() {
        return "ConditionsDisplay";
    }

}

class ForecastDisplay implements  Observer{
    private float pressure;
    private float previousPressure;
    private WeatherDispatcher dispatcher;

    public ForecastDisplay(WeatherDispatcher dispatcher){
        this.dispatcher = dispatcher;
        dispatcher.register(this);
    }


    @Override
    public void display() {
        if(pressure > previousPressure){
            System.out.println("Forecast: Improving");
        }else if(pressure==previousPressure){
            System.out.println("Forecast: Same");
        }else{
            System.out.println("Forecast: Cooler");
        }
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        previousPressure = this.pressure;
        this.pressure = pressure;
        display();
    }

    @Override
    public String getName() {
        return "ForecastDisplay";
    }
}



public class WeatherApplication {

    public static void main(String[] args) {
        WeatherDispatcher weatherDispatcher = new WeatherDispatcher();

        CurrentConditionsDisplay currentConditions = new CurrentConditionsDisplay(weatherDispatcher);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherDispatcher);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            weatherDispatcher.setMeasurements(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
            if(parts.length > 3) {
                int operation = Integer.parseInt(parts[3]);
                if(operation==1) {
                    weatherDispatcher.remove(forecastDisplay);
                }
                if(operation==2) {
                    weatherDispatcher.remove(currentConditions);
                }
                if(operation==3) {
                    weatherDispatcher.register(forecastDisplay);
                }
                if(operation==4) {
                    weatherDispatcher.register(currentConditions);
                }

            }
        }
    }
}
