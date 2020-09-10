//package socklaundering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SockLaundry {

    static int possibleNumOfSocks;

    public SockLaundry() {
        possibleNumOfSocks = 0;
    }


    //Do not delete or edit this method, you can only modify the block
    public int getMaximumPairOfSocks(int noOfWashes, int[] cleanPile, int[] dirtyPile) {
        //You can edit from here down
        List<Integer>  i1 = new ArrayList<>();
        int washesSoFar = 0;
        Map<Integer,Integer> cleanPairCounts = possibleSocksFromCleanPile(cleanPile);


        for (int i = 0; i < dirtyPile.length; i++) {
            if (washesSoFar < noOfWashes) {
                if (cleanPairCounts.containsKey(dirtyPile[i])) {
                    cleanPairCounts.replace(dirtyPile[i], cleanPairCounts.get(dirtyPile[i]) + 1);
                    int pairCount = cleanPairCounts.get(dirtyPile[i]);
                    if (pairCount % 2 == 0) {
                        if(i1.indexOf(dirtyPile[i]) != -1){
                            washesSoFar = washesSoFar + 2;
                            i1.remove(i1.indexOf(dirtyPile[i]));
                        }else{
                            washesSoFar++;
                        }
                        possibleNumOfSocks++;
                    }else{
                        i1.add(dirtyPile[i]);
                    }
                } else {
                    cleanPairCounts.put(dirtyPile[i], 1);
                    i1.add(dirtyPile[i]);
                }
            } else {
                break;
            }
        }
        return possibleNumOfSocks;
    }

    /**
     * You can create various helper methods
     * */

    Map<Integer,Integer> possibleSocksFromCleanPile(int[] cleanPile){

        Map<Integer,Integer> integerMap = new HashMap<>();
        for(int i=0;i<cleanPile.length;i++){
            int sockColor = cleanPile[i];
            if(integerMap.containsKey(sockColor)){
                int initialCount = integerMap.get(sockColor);
                integerMap.put(sockColor,initialCount+1);

            }else{
                integerMap.put(sockColor,1);
            }

            int pairCount = integerMap.get(cleanPile[i]);
            if (pairCount % 2 == 0) {
                possibleNumOfSocks++;
            }
        }

        return integerMap;

    }


}
