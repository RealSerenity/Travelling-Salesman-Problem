import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
public class Main {
    public static void main(String[] args) throws CmdLineException{
        Options options = new Options();
        CmdLineParser parser = new CmdLineParser(options);
        parser.parseArgument(args);
        
       ArbitraryInsertion arbitraryInsertion = new ArbitraryInsertion();
       arbitraryInsertion.ArbitrarilyInsertion(options.num);
       
       CheapestInsertion cheapestInsertion = new CheapestInsertion();
       cheapestInsertion.CheapestInsertion(options.num);

       FarthestInsertion farthestInsertion = new FarthestInsertion();
       farthestInsertion.FarthestInsertion(options.num);
        
       NearestInsertion nearestInsertion = new NearestInsertion();
       nearestInsertion.NearestInsertion(options.num);

       NearestNeighbor nearestNeighbor = new NearestNeighbor();
       nearestNeighbor.Nearest_Neighbor(options.num);
    }
}
