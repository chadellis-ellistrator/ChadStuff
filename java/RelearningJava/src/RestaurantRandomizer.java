import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RestaurantRandomizer {

    private List<String> restaurants = getWeightedRestaurantNames();
    private Random random = new Random();

    public String getChoice() {
        int rand = this.random.nextInt(100);
        return restaurants.get(rand % restaurants.size());
    }

    public static void main(String[] args) {
        RestaurantRandomizer rr = new RestaurantRandomizer();
        for (int i = 0; i < 100; i++) {
            System.out.println(rr.getChoice());
        }
    }

    List<Restaurant> getRestaurants() {
        return List.of(
            new Restaurant("Maudies", 2),
            new Restaurant("County Line", 3),
            new Restaurant("5280", 34),
            new Restaurant("Pinthouse", 4),
            new Restaurant("Hop Doddy", 5)
        );
    }

    List<String> getWeightedRestaurantNames() {
        List<String> names = new ArrayList<String>();
        for (Restaurant r: getRestaurants()) {
            for (int w = 0; w < r.getWeight(); w++) {
                names.add(r.getName());
            }
        }
        return names;
    }   
}

class Restaurant {
    private String name;
    private int weight;

    public Restaurant(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public static void main(String[] args) {

    }
}
