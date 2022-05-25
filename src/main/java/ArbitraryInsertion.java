import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ArbitraryInsertion {
    private String[] cities;
    private  int[] weights;
    static Boolean[] isAdded;
    private int[][] distances;
    ArbitraryInsertion() {
        Cities cities = new Cities();
        this.cities = cities.getCities();
        this.distances = cities.getDistances();
        weights = new int[this.cities.length];
    }

    public void ArbitrarilyInsertion(int index) {
        isAdded = new Boolean[81];
        ArrayList<Integer> path = new ArrayList<>();
        for (int i =0;i<81;i++) isAdded[i]=false;
        int start = index;
        int totalDistance=0;
        Random r = new Random();
        int current = r.nextInt(cities.length);
        path.add(start);
        path.add(current);
        isAdded[current] = true;
        isAdded[index]=true;
        while(path.size() <= 80){
            current = r.nextInt(cities.length);
            if(!isAdded[current]){
                path = addCityToPath(path,current);
                isAdded[current] = true;
            }

        }
        for(int i =0; i<path.size()-1;i++){
            totalDistance += distances[path.get(i)][path.get(i+1)];
        }
        totalDistance += distances[start][path.get(path.size()-1)];
        path.add(start);
        System.out.println("Arbitrary Insertion " + totalDistance + " " + path);

    }
    private ArrayList<Integer> addCityToPath(ArrayList<Integer> path,int cityIndex){
        int[] values= new int[path.size()-1];
        int min = Integer.MAX_VALUE;
        int index=0;
        for(int i =0;i<values.length;i++){
            values[i] = distances[path.get(i)][cityIndex] + distances[cityIndex][path.get(i+1)] - distances[path.get(i)][path.get(i+1)];
        }
        for(int i =0;i<values.length;i++){
            if(min>values[i]){
                min = values[i];
            }
        }
        for(int i =0;i<values.length;i++){
            if(values[i] == min){
                index = i;
                break;
            }
        }
        path.add(index+1,cityIndex);
        return path;
    }
}
