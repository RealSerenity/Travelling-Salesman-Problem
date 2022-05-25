import java.util.ArrayList;
import java.util.Scanner;

public class NearestNeighbor {
    private String[] cities;
    private  int[] weights;
    static Boolean[] isVisited;
    private int[][] distances;
     NearestNeighbor() {
         Cities cities = new Cities();
         this.cities = cities.getCities();
         this.distances = cities.getDistances();
         weights = new int[this.cities.length];
    }

    public void Nearest_Neighbor(int index) {
        isVisited = new Boolean[81];
        ArrayList<Integer> path = new ArrayList<>();
        for (int i =0;i<81;i++) isVisited[i]=false;
        int start = index;
        int totalDistance=0;
        int nextCity[]=null;
        path.add(start);
        for(int i =0; i< 80; i++){
            nextCity = Nearest_Neighbor_Path(index);
            path.add(nextCity[0]);
            if(nextCity[1] != Integer.MAX_VALUE){
                totalDistance += nextCity[1];
            }
            index = nextCity[0];
        }
        if(path.get(0) != null) {
            nextCity = Nearest_Neighbor_Add_StartNode(nextCity[0],start);
           path.add(nextCity[0]);
           totalDistance+=nextCity[1];
        }
        System.out.println("NearestNeighbor " + totalDistance + " " + path);

    }

    private int[] Nearest_Neighbor_Path(int index2) {
        isVisited[index2] = true;
        int min_path =Integer.MAX_VALUE;
        int nextCityIndex = 0;
        for (int i = 0; i < 81; i++) {
            if (i == index2) continue;
            if (min_path > distances[index2][i]) {
                if (!isVisited[i]) {
                    min_path = distances[index2][i];
                    nextCityIndex = i;
                }
            }
        }
        isVisited[nextCityIndex] = true;

        return new int[]{nextCityIndex, min_path};
    }
    private int[] Nearest_Neighbor_Add_StartNode(int start, int end){
       return new int[]{end,distances[start][end]};
    }
}