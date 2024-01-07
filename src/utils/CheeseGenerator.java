package utils;

import entity.Cheese;
import graphics.Sprite;
import utils.pathfinder.Grid;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CheeseGenerator {
    private List<Cheese> cheeseList;

    public CheeseGenerator() {
        cheeseList = new ArrayList<Cheese>();
    }

    public static List<Cheese> generateCheese(int numberOfCheese) {
        List<Cheese> cheeseList = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < numberOfCheese; i++) {
            int randomX, randomY;

            do {
                randomX = random.nextInt(Grid.getGridWidth());
                randomY = random.nextInt(Grid.getGridHeight());
            } while (!Grid.getWalkableTiles()[randomX][randomY]);

            float cheesePosX = randomX * 32;
            float cheesePosY = randomY * 32;
            Cheese cheese = new Cheese(new Sprite("item/cheese.png"), new Vector2f(cheesePosX, cheesePosY), 32);
            cheeseList.add(cheese);
        }

        return cheeseList;
    }

    public static void renderCheese(List<Cheese> cheeseList, Graphics2D g2D) {
        Iterator<Cheese> iterator = cheeseList.iterator();
        while (iterator.hasNext()) {
            Cheese cheese = iterator.next();
            if (!cheese.isEaten()) {
                cheese.render(g2D);
            } else {
                iterator.remove();
            }
        }
    }

    public List<Cheese> getCheeseList() {
        return cheeseList;
    }
}
