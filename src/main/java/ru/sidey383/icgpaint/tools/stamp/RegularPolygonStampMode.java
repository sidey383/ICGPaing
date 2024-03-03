package ru.sidey383.icgpaint.tools.stamp;

public class RegularPolygonStampMode implements StampMode {
    @Override
    public int[][] getPoints(int x, int y, double angle, int n, int size) {
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            double a = angle + (360.0*i/n);
            points[i][0] = x + (int) (Math.sin(Math.PI*a/180) * size);
            points[i][1] = y - (int) (Math.cos(Math.PI*a/180) * size);
        }
        return  points;
    }
}
