import java.util.ArrayList;
import java.util.Scanner;

public class CheapestInsertion {
    private String[] cities;
    private  int[] weights;
    static Boolean[] isAdded;
    private int[][] distances;
    CheapestInsertion() {
        Cities cities = new Cities();
        this.cities = cities.getCities();
        this.distances = cities.getDistances();
        weights = new int[this.cities.length];
    }

    public void CheapestInsertion(int index) {
        isAdded = new Boolean[81];
        ArrayList<Integer> path = new ArrayList<>();
        for (int i =0;i<81;i++) isAdded[i]=false;
        int start = index;
        int totalDistance=0;
        int nextCity[]=Nearest_Path(index);
        path.add(start);
        path.add(nextCity[0]);
        isAdded[index]=true;
        while(path.size() <= 80){
            path = addCityToPath(path);
        }
        for(int i =0; i<path.size()-1;i++){
            totalDistance += distances[path.get(i)][path.get(i+1)];
        }
        totalDistance += distances[index][path.get(path.size()-1)];
        path.add(index);
        System.out.println("CheapestInsertion " + totalDistance + " " + path);
    }
    private ArrayList<Integer> addCityToPath(ArrayList<Integer> path){
        int min = Integer.MAX_VALUE;
        int index=0;
        int current =Integer.MAX_VALUE;
        int cityIndex=0;
        for(int y =0;y<path.size()-1;y++){
            for(int i =0;i<cities.length;i++){
                if(!isAdded[i]){
                    current = distances[path.get(y)][i] + distances[i][path.get(y+1)] - distances[path.get(y)][path.get(y+1)];
                    if(current<min){
                        min = current;
                        index=i;
                        cityIndex=y;
                    }
                }
            }
        }
        isAdded[index] = true;
        path.add(cityIndex+1,index);
        return path;
    }
    private int[] Nearest_Path(int index2) {
        isAdded[index2] = true;
        int min_path =Integer.MAX_VALUE;
        int nextCityIndex = 0;
        for (int i = 0; i < 81; i++) {
            if (i == index2) continue;
            if (min_path > distances[index2][i]) {
                if (!isAdded[i]) {
                    min_path = distances[index2][i];
                    nextCityIndex = i;
                }
            }
        }
        isAdded[nextCityIndex] = true;

        return new int[]{nextCityIndex, min_path};
    }
    private int[] Add_Start_Node(int start, int end){
        return new int[]{end,distances[start][end]};
    }
}
