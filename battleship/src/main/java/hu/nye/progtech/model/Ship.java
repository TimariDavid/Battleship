package hu.nye.progtech.model;

import hu.nye.progtech.service.field.Result;

/**
 *  Ebben az osztályban a hajók interakcióit készítem el.
 */
public class Ship {
    private final String name;
    private final int size;
    private int lives;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.lives = size;
    }

    /**
     *  Itt ellenőrzöm, hogy az eltalált hajó elsüllyedt-e, és kiiratom.
     */
    public void hit() {
        if (lives > 0) {
            System.out.printf("%nGood shot! The %s was hit\n", name);
            lives--;
        } else {
            System.out.println("Ship is destroyed\n");
        }
    }

    /**
     * Lekérem a hajó állapotát.
     *
     * @return hajó állapota {@link Result}
     */
    public Result getState() {
        if (lives == 0) {
            return Result.DESTROYED;
        } else if (lives < size) {
            return Result.HIT;
        } else if (lives == size) {
            return Result.SHIP;
        } else {
            return Result.NO_HIT;
        }
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}