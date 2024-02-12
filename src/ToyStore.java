import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToyStore {
    private final List<Toy> toys;

    public ToyStore() {
        toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void updateToyWeight(int toyId, double weight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(weight);
                return;
            }
        }
        System.out.println("Игрушка с ID " + toyId + " не найдена.");
    }

    public Toy drawToy() {
        double totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }

        double randomNumber = Math.random() * totalWeight;
        double currentWeight = 0;
        for (Toy toy : toys) {
            currentWeight += toy.getWeight();
            if (randomNumber <= currentWeight) {
                toy.decreaseQuantity();
                return toy;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();
        toyStore.addToy(new Toy(1, "Кукла", 1, 30)); // Примеры игрушек
        toyStore.addToy(new Toy(2, "Машинка", 2, 40));
        toyStore.addToy(new Toy(3, "Мяч", 1, 20));

        Toy drawnToy = toyStore.drawToy();
        if (drawnToy != null) {
            System.out.println("Выиграна игрушка: " + drawnToy.getName());
            try {
                FileWriter writer = new FileWriter("prize.txt", true);
                writer.write(drawnToy.getName() + "\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Извините, игрушек больше нет.");
        }
    }
}

