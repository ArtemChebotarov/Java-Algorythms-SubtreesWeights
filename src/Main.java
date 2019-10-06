import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main
{
    private int verticesCount;
    private static LinkedList<Integer> neighbourhood[];
    private static LinkedList<LinkedList<Integer>> indexedChildren;
    private static int top = 0;
    private static int sum = 0;
    private static int minSum = 0;
    private static int maxSum = 0;

    Main(int verticesCount)
    {
        this.verticesCount = verticesCount;
        neighbourhood = new LinkedList[verticesCount];
        indexedChildren = new LinkedList();

        for (int i = 0; i < verticesCount; ++i)
            neighbourhood[i] = new LinkedList();
    }

    void addNeighbourhood(int v, int w)
    {
        neighbourhood[v-1].add(w-1);
        neighbourhood[w-1].add(v-1);
    }

    Boolean isCyclic(int vertex, Boolean visited[], int parent)
    {
        visited[vertex] = true;
        int i;

        Iterator<Integer> it = neighbourhood[vertex].iterator();
        while (it.hasNext())
        {
            i = it.next();

            if (!visited[i])
            {
                if (isCyclic(i, visited, vertex))
                    return true;
            }

            else if (i != parent)
                return true;
        }
        return false;
    }

    Boolean isTree()
    {
        Boolean visited[] = new Boolean[verticesCount];

        for (int i = 0; i < verticesCount; i++)
            visited[i] = false;

        if (isCyclic(0, visited, -1))
            return false;

        for (int u = 0; u < verticesCount; u++)
            if (!visited[u])
                return false;

        return true;
    }

    static void getSubGraphWeight(int elem) {

        for (int i = 1; i < indexedChildren.get(elem-1).size(); i++) {
            sum += indexedChildren.get(elem-1).get(i);

            getSubGraphWeight(indexedChildren.get(elem-1).get(i));

            if(indexedChildren.get(elem-1) == indexedChildren.get(top-1)) {
                if(sum > maxSum || maxSum == 0)
                    maxSum = sum;

                if(sum < minSum || minSum == 0)
                    minSum = sum;
                sum = 0;
            }
        }
    }

    public static void main(String args[])
    {
        int branches;
        Main graph = null;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("D:\\Documents\\pjatk\\3sem\\asd\\WagiPoddrzew\\DrzewoTest1.txt"), StandardCharsets.UTF_8))){
            String line;
            int line_num = 1;

            Pattern p = Pattern.compile("[\\d]+");

            while ((line = reader.readLine()) != null) {

                if(line_num == 1) {
                    branches = Integer.parseInt(line);
                    graph = new Main(branches);

                }

                if(line_num > 2) {
                    LinkedList<Integer> nums = new LinkedList<>();


                    Matcher m = p.matcher(line);
                    while (m.find()) {
                        nums.add(Integer.parseInt(m.group()));
                    }

                    indexedChildren.add(nums);


                    top += nums.get(0);

                    if(nums.size() > 1) {
                        for(int i = 1; i < nums.size(); i++) {
                            graph.addNeighbourhood(nums.get(0), nums.get(i));
                            top -= nums.get(i);


                        }
                    }
                }

                line_num++;
            }
        } catch (IOException e) {
            // log error
        }

        if (graph.isTree()) {
            System.out.print("JEST ");
            getSubGraphWeight(top);
            System.out.print(maxSum + " " + minSum);

            try(FileWriter writer = new FileWriter("D:\\Documents\\pjatk\\3sem\\asd\\WagiPoddrzew\\DrzewoTest1_wynik.txt", false))
            {
                getSubGraphWeight(top);
                writer.append("JEST " + maxSum + " " + minSum);

                writer.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }

        else
            System.out.print("NIE");

            try(FileWriter writer = new FileWriter("D:\\Documents\\pjatk\\3sem\\asd\\WagiPoddrzew\\DrzewoTest1_wynik.txt", false))
            {
                writer.append("NIE");

                writer.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
    }
}
