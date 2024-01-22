import java.util.LinkedList;
import java.util.Queue;
public class Checker {
    public boolean IsCorrect(int[][] board) {
        int n= board.length;
        int[][]tempboard = new int[n][n];
        for(int i=0; i<n; ++i)
        {
            for(int j=0; j<n; ++j)
            {
                tempboard[i][j]=board[i][j];
            }
        }
        int sumofwhite=0, sumofblack=0;
        // sprawdzenie czy jest odpowiednia ilosc zamalowanych pol
        for(int i=0; i<n; ++i)
        {
            for(int j=0; j<n; ++j)
            {
                if(tempboard[i][j] == -1)
                    ++sumofblack;
                else
                    sumofwhite+=tempboard[i][j];
            }
        }
        if(sumofblack !=(n * n )-sumofwhite ) return false;
        // sprawdzenie czy nie ma czarnych kwadratów
        for(int i=0; i<n-1; ++i)
        {
            for(int j=0; j<n-1; ++j)
            {
                if(tempboard[i][j] == -1 && tempboard[i+1][j] ==-1 && tempboard[i][j+1]==-1 && tempboard[i+1][j+1]==-1)
                    return false;
            }
        }
        //sprawdzanie czy czarna linia jest spójna
        boolean flag=false;
        for(int i=0; i<n; ++i)
        {
            if(flag) break;
            for(int j=0; j<n; ++j)
            {
                if (flag) break;
                if(tempboard[i][j]==-1)
                {
                    flag = true;
                    Queue<Punkt> q1 =new LinkedList<>();
                    boolean[][] odw1 = new boolean[n][n];
                    q1.add(new Punkt(i, j));
                    int wyn=0;
                    odw1[i][j]=true;
                    while(!q1.isEmpty())
                    {
                        ++wyn;
                        Punkt p=q1.remove();
                        odw1[p.first][p.second]=true;
                        if(p.first>0)
                        {
                            if(tempboard[p.first-1][p.second] == -1 && !odw1[p.first - 1][p.second]){
                                q1.add(new Punkt(p.first-1, p.second));
                                odw1[p.first-1][p.second]=true;
                            }

                        }
                        if(p.second>0)
                        {
                            if(tempboard[p.first][p.second-1] == -1 && !odw1[p.first][p.second-1]){
                                q1.add(new Punkt(p.first, p.second-1));
                                odw1[p.first][p.second-1] =true;
                            }

                        }
                        if(p.first<n-1)
                        {
                            if(tempboard[p.first+1][p.second] == -1 && !odw1[p.first+1][p.second]) {
                                q1.add(new Punkt(p.first + 1, p.second));
                                odw1[p.first+1][p.second]=true;
                            }
                        }
                        if(p.second<n-1)
                        {
                            if(tempboard[p.first][p.second+1] == -1 && !odw1[p.first][p.second+1]) {
                                odw1[p.first][p.second+1]=true;
                                q1.add(new Punkt(p.first, p.second + 1));
                            }
                        }

                    }
                    if(wyn!= sumofblack) return false;
                }
            }
        }
        // sprawdzanie czy zgadzają się wielkości wysp
        Queue<Punkt> q =new LinkedList<>();
        boolean[][] odw = new boolean[n][n];
        for(int i=0; i<n; ++i)
        {
            for(int j=0; j<n; ++j)
            {
                if(tempboard[i][j]>0)
                {
                    int req=tempboard[i][j];
                    int wyn=0;
                    q.add(new Punkt(i, j));

                    while(!q.isEmpty())
                    {
                        ++wyn;
                        Punkt p=q.remove();
                        odw[p.first][p.second]=true;
                        tempboard[p.first][p.second]=-2;
                        if(p.first>0)
                        {
                            if(tempboard[p.first-1][p.second] == 0 && !odw[p.first - 1][p.second]){
                                q.add(new Punkt(p.first-1, p.second));
                                odw[p.first-1][p.second]=true;
                            }

                        }
                        if(p.second>0)
                        {
                            if(tempboard[p.first][p.second-1] == 0 && !odw[p.first][p.second-1]){
                                q.add(new Punkt(p.first, p.second-1));
                                odw[p.first][p.second-1] =true;
                            }

                        }
                        if(p.first<n-1)
                        {
                            if(tempboard[p.first+1][p.second] == 0 && !odw[p.first+1][p.second]) {
                                q.add(new Punkt(p.first + 1, p.second));
                                odw[p.first+1][p.second]=true;
                            }
                        }
                        if(p.second<n-1)
                        {
                            if(tempboard[p.first][p.second+1] == 0 && !odw[p.first][p.second+1]) {
                                odw[p.first][p.second+1]=true;
                                q.add(new Punkt(p.first, p.second + 1));
                            }
                        }

                    }
                    if(req != wyn) return false;
                }

            }
        }
        return true;
    }

    private static class Punkt {
        int first;
        int second;
        public Punkt(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
