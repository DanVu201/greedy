import java.util.Arrays;

public class Kruskal {

    public static void main(String[] args) {
        int V = 5;
        int E = 10;
        new Dothi(V, E);

        Dothi.canh[0] = new Dothi.Canh(0,1,13);
        Dothi.canh[1] = new Dothi.Canh(0,2,22);
        Dothi.canh[2] = new Dothi.Canh(0,3,24);
        Dothi.canh[3] = new Dothi.Canh(0,4,13);
        Dothi.canh[4] = new Dothi.Canh(1,2,14);
        Dothi.canh[5] = new Dothi.Canh(1,3,22);
        Dothi.canh[6] = new Dothi.Canh(1,4,19);
        Dothi.canh[7] = new Dothi.Canh(2,3,13);
        Dothi.canh[8] = new Dothi.Canh(3,4,19);
        Dothi.canh[9] = new Dothi.Canh(3,4,13);
        // Function call
        long startTime = System.currentTimeMillis();
        Dothi.KruskalMST();
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime-startTime));
    }


    public static class Dothi {
        static int Dinh;
        int Canh;
        static Canh[] canh;

        Dothi(int v, int e) {
            Dinh = v;
            Canh = e;
            canh = new Canh[Canh];
        }

        static class Canh implements Comparable<Canh> {
            int diem_bat_dau;
            int diem_ket_thuc;
            int trong_so;

            Canh(int bd, int kt, int ts){
                diem_bat_dau = bd;
                diem_ket_thuc = kt;
                trong_so = ts;
            }

            public int compareTo(Canh compareEdge) {
                return this.trong_so - compareEdge.trong_so;
            }
        }

        static class subset {
            int parent;
            int rank;
            subset (int p, int r) {
                parent = p;
                rank = r;
            }
        }

        public static void KruskalMST() {
            Canh[] result = new Canh[Dinh];
            int e = 0;
            for (int i = 0; i < Dinh; ++i)
                result[i] = new Canh(0,0,0);
            Arrays.sort(canh);
            subset[] subsets = new subset[Dinh];

            for (int v = 0; v < Dinh; ++v) {
                subsets[v] = new subset(v, 0);
            }

            int i = 0;
            while (e < Dinh - 1) {
                Canh canh_tiep_theo = canh[i++];

                int x = find(subsets, canh_tiep_theo.diem_bat_dau);
                int y = find(subsets, canh_tiep_theo.diem_ket_thuc);

                if (x != y) {
                    result[e++] = canh_tiep_theo;
                    Union(subsets, x, y);
                }
            }
            System.out.println("Xay dung cay theo cac canh sau");
            int chiPhi = 0;
            for (i = 0; i < e; ++i) {
                System.out.println((result[i].diem_bat_dau + 1) + " -- "
                        + (result[i].diem_ket_thuc + 1)
                        + " == " + result[i].trong_so);
                chiPhi += result[i].trong_so;
            }
            System.out.println("Chi phi toi thieu " + chiPhi);
        }
        static int find(subset[] subsets, int i) {
            if (subsets[i].parent != i)
                subsets[i].parent = find(subsets, subsets[i].parent);
            return subsets[i].parent;
        }

        static void Union(subset[] subsets, int x, int y) {

            int xroot = find(subsets, x);
            int yroot = find(subsets, y);

            if (subsets[xroot].rank < subsets[yroot].rank)
                subsets[xroot].parent = yroot;
            else if (subsets[xroot].rank > subsets[yroot].rank)
                subsets[yroot].parent = xroot;
            else {
                subsets[yroot].parent = xroot;
                subsets[xroot].rank++;
            }
        }
    }
}