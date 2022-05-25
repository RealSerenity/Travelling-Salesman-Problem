import java.util.ArrayList;
import java.util.Scanner;

public class NearestInsertion {
    private String[] cities;
    private  int[] weights;
    static Boolean[] isAdded;
    private int[][] distances;
    NearestInsertion() {
        Cities cities = new Cities();
        this.cities = cities.getCities();
        this.distances = cities.getDistances();
        weights = new int[this.cities.length];
    }
    public void NearestInsertion(int index){

        isAdded = new Boolean[81];
        ArrayList<Integer> path = new ArrayList<>();
        for (int i =0;i<81;i++) isAdded[i]=false;
        int newCity[] = for_loop(index);
        path.add(index);
        path.add(newCity[0]);
        addCity(index);
        addCity(newCity[0]);
        int[] currentCity;
       while(path.size() <= 80){
            newCity = for_loop(path.get(0));
            for(int i=1;i<path.size();i++){
                currentCity = for_loop(path.get(i));
                if(newCity[1] > currentCity[1]){
                    newCity = currentCity;
                }
            }
            path = addCityToPath(path,newCity[0]);
            addCity(newCity[0]);
        }
        int total =0;
        for(int i =0; i<path.size()-1;i++){
            total += distances[path.get(i)][path.get(i+1)];
        }
        total += distances[index][path.get(path.size()-1)];
        path.add(index);
        System.out.print("NearestInsertion ");
        System.out.print(total+" ");
        System.out.print(path+" \n");
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

    private int[] for_loop(int index){
        int cityIndex=0;
        int min = Integer.MAX_VALUE;
        for(int i =0; i<81;i++){
            if (i == index) continue;
            if(distances[index][i]<min){
                if (!isAdded[i]) {
                    min = distances[index][i];
                    cityIndex = i;
                }
            }
        }
        return new int[]{cityIndex,min};

    }
    private void addCity(int index){
        isAdded[index] = true;
    }
}
