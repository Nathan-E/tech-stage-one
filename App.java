import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class App {

    public Integer maxNumberPairs(Integer noOfWashes, Integer[] cleanPile, Integer[] dirtyPile)
    {
        System.out.println(Arrays.toString(cleanPile));
        System.out.println(Arrays.toString(dirtyPile));
        System.out.println(calculateDifference(cleanPile, dirtyPile, noOfWashes).toString());
        //java.util.Arrays.sort(cleanPile);
        //System.out.println(Arrays.toString(cleanPile));
        Integer maxNum = 0;
        for (Integer p: cleanPile) {
            //System.out.println("Element value : " + p);
            maxNum = maxNum + p;
        }
        return maxNum * noOfWashes;
    }

    private ArrayList<Integer> calculateDifference(Integer[] arrA, Integer[] arrB, Integer limitArrB){
        ArrayList<Integer> resultArrList = new ArrayList<Integer>();
        int newArrBSize = arrB.length - limitArrB;
        Integer[] newArrB = new Integer[limitArrB];
        java.util.Arrays.sort(arrA);
        java.util.Arrays.sort(arrB);
        System.arraycopy(arrB,0,newArrB,0,limitArrB);
        System.out.println(Arrays.toString(newArrB));
        Integer firstCountMatchingSockInDirtyPile = 0;
        for (int i = 0; i < newArrB.length; i++) {
            if (java.util.Arrays.binarySearch(arrA,newArrB[i]) >= 0) {
                firstCountMatchingSockInDirtyPile ++;
                resultArrList.add(newArrB[i]);
                //System.out.println(arrB[i]);
                //java.util.Arrays.fill(result,arrA[i]);
            }else System.out.println("Others: "+ newArrB[i]);
        }
        resultArrList.addAll(Arrays.asList(arrA));
        java.util.Arrays.sort(arrA);
        Collections.sort(resultArrList);

        Integer index = resultArrList.get(0);
        Integer count = 1;
        Double maxPairs = 0.0;
        Double previousCount = 0.0;
        for (int i = 0; i < resultArrList.size(); i++) {
        //for (Integer sock : resultArrList) {
            if(!resultArrList.get(i).equals(index)) count = 1;

            Double currentCount = Math.floor((count++)/2);

            //if(currentCount == ) maxPairs =
            if(currentCount != 0.0)
            {
                if(currentCount > 0) previousCount = currentCount -1;
                /*if(resultArrList.get(i).equals(resultArrList.get(i-1))) maxPairs = maxPairs + currentCount;*/
                if(resultArrList.get(i).equals(resultArrList.get(i-1))) maxPairs = maxPairs + currentCount;
                System.out.println("Counts for "+resultArrList.get(i) + ", pair: " +currentCount+", previousCount: "+previousCount);
            }
            index = resultArrList.get(i);
        }

        System.out.println("max:" + maxPairs);
        //System.out.println(firstCountMatchingSockInDirtyPile);
        return resultArrList;
    }

}
