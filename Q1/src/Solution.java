import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());
        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> { try {
            return bufferedReader.readLine().replaceAll("\\s+$", ""); } catch (IOException ex) {
            throw new RuntimeException(ex); }
        })
                .map(String::trim) .map(Integer::parseInt) .collect(toList());
        Result.customSort(arr);
        bufferedReader.close();
    }



}
