package acm;

import java.util.*;

public class Main {

    static class node {

        ArrayList<Integer> adjListNode = new ArrayList<>();
        int start = -1;
        int finish = -1;

        public node() {
        }
    }
    static ArrayList<ArrayList<Integer>> adjList;
    static ArrayList<Boolean> visited;
    static int timer = 0;
    static ArrayList<Integer> topoSort = new ArrayList<>();
    static ArrayList<node> adj = new ArrayList<>();
    static int cycleCount = 0;
    static int forwardCount = 0;
    static int crossCount = 0;

    public static void dfs(int node) {
        visited.set(node, Boolean.TRUE);
        for (int i = 0; i < adjList.get(node).size(); i++) {
            if (visited.get(adjList.get(node).get(i)) != true) {
                dfs(adjList.get(node).get(i));
            }
        }
        topoSort.add(node);

    }

    public static void dfs_EdgeClassification(int n) {
        adj.get(n).start = timer++;
        for (int i = 0; i < adj.get(n).adjListNode.size(); i++) {
            if (adj.get(adj.get(n).adjListNode.get(i)).start == -1) {
                dfs_EdgeClassification(adj.get(n).adjListNode.get(i));
            } else if (adj.get(adj.get(n).adjListNode.get(i)).finish == -1) {
                cycleCount++;
            } else if (adj.get(n).start < adj.get(adj.get(n).adjListNode.get(i)).finish) {
                forwardCount++;
            } else {
                crossCount++;
            }
        }
        adj.get(n).finish = timer++;
    }

    public static ArrayList<Integer> bfs_Path(int source, int destination) {
        ArrayList<Integer> par = new ArrayList<>();
        ArrayList<Integer> len = new ArrayList<>();
        for (int i = 0; i < adjList.size(); i++) {
            len.add(Integer.MAX_VALUE);
            par.add(-1);
        }
        Queue<Integer> queue = new java.util.LinkedList<>();
        queue.add(source);
        len.set(source, 0);
        int dep = 0;
        int cur = source;
        int sz = 1;
        boolean ok = true;
        for(;ok&&!queue.isEmpty();++dep,sz=queue.size())
        {
            while(ok&&sz!=0)
            {
                sz--;
                cur=queue.poll();
                for(int i = 0 ; i < adjList.get(cur).size() ; i++)
                {
                    if(len.get(adjList.get(cur).get(i))==Integer.MAX_VALUE)//not visited
                    {
                        queue.add(adjList.get(cur).get(i));
                        par.set(adjList.get(cur).get(i), cur);
                        len.set(adjList.get(cur).get(i), dep+1);
                        
                        if(adjList.get(cur).get(i)==destination)
                        {
                            ok=false;
                            break;
                            
                        }
                    }
                        
                }
                
            }
        }
        ArrayList<Integer> path = new ArrayList<>();
        while(destination!=-1)
        {
            path.add(destination);
            destination = par.get(destination);
               
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String args[]) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int n, e;
        n = sc.nextInt();
        e = sc.nextInt();
        adjList = new ArrayList<>();
        visited = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            adjList.get(start).add(end);
        }
        ArrayList<Integer>path = bfs_Path(0, 5);
        for(int i = 0 ; i < path.size() ; i ++)
            System.out.println(path.get(i));
    }

}
