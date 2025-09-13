//Time Complexity: O(V + E)
//Space Complexity: O(V + E)
// Did this code successfully run on Leetcode :Yes
//Approach: this is a bi-directional graph problem since we need to know the parent-child and child-parent relationship
//We will use an adjacency list to keep a track of the dependencies on each course, an in-order array to keep a track of how many courses does a course depends on
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        final List<List<Integer>> graph = new ArrayList<>();
        int[] in_degree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] edge: prerequisites) {
            int dependent = edge[0], independent = edge[1];
            in_degree[dependent]++;
            graph.get(independent).add(dependent);
        }

        final Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in_degree[i] == 0) queue.offer(i);
        }

        int taken = 0;
        while (!queue.isEmpty()) {
            taken++;

            Integer course = queue.poll();
            for (Integer neighbor : graph.get(course)) {
                in_degree[neighbor]--;
                if (in_degree[neighbor] == 0) queue.offer(neighbor);
            }
        }

        return taken == numCourses;
    }

    public static void main(String[] args) {
        final CourseSchedule courseSchedule = new CourseSchedule();
        int[][] prerequisites = new int[][] {
                {1, 0}, {2, 0}, {3, 1}, {3, 2}, {4, 3}, {5, 2}, {5, 3}, {5, 4}
        };
        System.out.println(courseSchedule.canFinish(6, prerequisites)); //true
    }
}
