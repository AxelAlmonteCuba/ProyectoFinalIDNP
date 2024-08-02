package com.example.proyectofinalplataformas.Entitys;

public class Room {
    private String name;
    private float[][] coordinates;

    public Room(String name, float[][] coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public float[][] getCoordinates() {
        return coordinates;
    }

    public boolean containsPoint(float x, float y) {
        int intersectCount = 0;
        for (int i = 0; i < coordinates.length - 1; i++) {
            if (rayCastIntersect(x, y, coordinates[i], coordinates[i + 1])) {
                intersectCount++;
            }
        }
        if (rayCastIntersect(x, y, coordinates[coordinates.length - 1], coordinates[0])) {
            intersectCount++;
        }
        return (intersectCount % 2) == 1; // odd = inside, even = outside
    }

    private boolean rayCastIntersect(float x, float y, float[] vertA, float[] vertB) {
        float aY = vertA[1];
        float bY = vertB[1];
        float aX = vertA[0];
        float bX = vertB[0];
        return ((aY > y) != (bY > y)) && (x < (bX - aX) * (y - aY) / (bY - aY) + aX);
    }
}

