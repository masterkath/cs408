import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){

        Scanner in = new Scanner(System.in);
        // The name of the file to open.
        String fileName = "input3.txt";

        // This will reference one line at a time
        String line = null;

        String inputs[] = new String[3];

        //$)char n1, a1, b1;

        int n;
        int a;
        int b;

        int ans = 0;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            line = bufferedReader.readLine();
            inputs = line.split(" ");


            n = Integer.parseInt(inputs[0]);
            a = Integer.parseInt(inputs[1]);
            b = Integer.parseInt(inputs[2]);

            int[][] friends = new int[n][2];
            for(int i = 0; i < n; i++){
                line = bufferedReader.readLine();
                String[] l = line.split(" ");
                int y = Integer.parseInt(l[1]);
                friends[i][0] = y;

                //0 NO SWORD
                // 1 SWORD
                if(l[0].equals("S")){
                    friends[i][1] = 1;
                } else {
                    friends[i][1] = 0;
                }
            }

            Arrays.sort(friends,new java.util.Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return Integer.compare(a[0], b[0]);
                }
            });

            System.out.println(Arrays.deepToString(friends));

            int opt = 0;

            for(int i = a; i <= b; i++) {

                if (opt == 1) {
                  //NEED A FASTER SOLUTION
                    // PLZ KILL ME

                } else {

                    int low = 0;
                    int high = n - 1;
                    int closest;
                    int mid = -1;

                    while (low < high) {
                        mid = (low + high) / 2;
                        int d1 = Math.abs(friends[mid][0] - i);
                        int d2 = Math.abs(friends[mid + 1][0] - i);

                        if (friends[mid][0] == i) {
                            break;
                        } else if (friends[mid + 1][0] == i) {
                            mid = mid + 1;
                            break;
                        }


                        if (d2 < d1) {
                            low = mid + 1;
                        } else if (d2 > d1) {
                            high = mid;
                        } else {
                            if (friends[mid][1] == 1) {
                                break;
                            } else if (friends[mid + 1][1] == 1) {
                                mid = mid + 1;
                            }
                            break;
                        }

                        if (low == high) {
                            mid = low;
                            break;
                        }
                    }

                    closest = mid;
                    if (friends[closest][1] == 1) {
                        //System.out.println(i);
                        ans++;
                    }
                }
            }

            System.out.println(ans);
            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }
}
