package ru.sidey383.icgpaint.tools.stamp;

public class StarPolygonStampMode implements StampMode {
    @Override
    public int[][] getPoints(int x, int y, double angle, int n, int size) {
        int[][] points = new int[n*2][2];
        double r = (double) size / 2;
        double R = (double) size;
        for (int i = 0; i < n*2; i++) {
            double a = angle + (360.0*i/(2*n));
            if (i % 2 == 0) {
                points[i][0] = x + (int) (Math.sin(Math.PI*a/180) * r);
                points[i][1] = y + (int) (Math.cos(Math.PI*a/180) * r);
            } else {
                points[i][0] = x + (int) (Math.sin(Math.PI*a/180) * R);
                points[i][1] = y + (int) (Math.cos(Math.PI*a/180) * R);
            }
        }
        return points;
    }
}
