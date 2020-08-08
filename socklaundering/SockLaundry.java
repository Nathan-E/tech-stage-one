//package socklaundering;

import java.util.HashMap;
import java.util.Map;

public class SockLaundry {

    //Do not delete or edit this method, you can only modify the block
    public int getMaximumPairOfSocks(int noOfWashes, int[] cleanPile, int[] dirtyPile) {
        //You can edit from here down
        int maxNumofPair = 0;
        Map<Integer,Integer> integerMap = storeCleanPileCount(cleanPile);
        for(int j=0;j<dirtyPile.length;j++){
            if(noOfWashes==0){
                break;
            }

            if(integerMap.containsKey(dirtyPile[j])){

            }

        }
        return maxNumofPair;
    }

    /**
     * You can create various helper methods
     * */

    private Map<Integer,Integer> storeCleanPileCount(int[] cleanPile){

        Map<Integer,Integer> integerMap = new HashMap<>();
        for(int i=0;i<cleanPile.length;i++){
            int sockColor = cleanPile[i];
            if(integerMap.containsKey(sockColor)){
                int initialCount = integerMap.get(sockColor);
                integerMap.put(sockColor,initialCount+1);
            }else{
                integerMap.put(sockColor,1);
            }
        }

        return integerMap;

    }
}
