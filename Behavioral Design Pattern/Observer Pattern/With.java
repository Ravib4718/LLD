import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String message); // The method to receive updates
}

//Define the subject class
class Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    // Method for observers to subscribe
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Method for observers to unsubscribe
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notify all observers of the state change
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }

    // Change state and notify observers
    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }
}

//Define the concrete Observer class

class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received update: " + message);
    }
}

// main method
public class ObserverPatternDemo {
    public static void main(String args[]){
      Subject subject = new Subject();
      Observer observer1= new ConcreteObserver("Observer 1");
      Observer observer1= new ConcreteObserver("Observer 2");
      subject.addObserver(observer1);
      subject.addObserver(observer2);

      System.out.println("change the state of subject");
      subject.setState("State A");

      subject.removeObserver(observer1);
      System.out.println(" again change the state of subject");
      subject.setState("State B");

    }
}
