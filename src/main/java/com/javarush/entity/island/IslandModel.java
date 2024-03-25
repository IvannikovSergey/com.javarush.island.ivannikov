package com.javarush.entity.island;

import com.javarush.utils.Settings;

public class IslandModel {
  private final Location[][] grid;
  private final int sizeOfGameFieldX = Settings.SIZE_OF_GAME_FIELD_X;
  private final int sizeOfGameFieldY = Settings.SIZE_OF_GAME_FIELD_Y;

  public IslandModel() {
    this.grid = new Location[sizeOfGameFieldX][sizeOfGameFieldY];
  }

  public Location[][] getGrid() {
    return this.grid;
  }

  public Location getLocation(int yPosition, int xPosition) {
    return grid[yPosition][xPosition];
  }
}
