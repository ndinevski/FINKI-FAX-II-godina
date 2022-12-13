package laboratoriski.lab7.zad4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RoutingHashJava {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        Map<String, Router> map = new HashMap<>();


        for(int i=0;i<N;i++){
            String routerIp = bf.readLine();
            String [] accessRange = bf.readLine().split(",");
            ArrayList<String> ranges = new ArrayList<>();
            for(int j=0;j<accessRange.length;j++){
                ranges.add(accessRange[j]);
            }
            Router router = new Router(routerIp, ranges);
            map.put(routerIp, router);
        }

        int M = Integer.parseInt(bf.readLine());
        for(int i=0;i<M;i++){
            String routerIp = bf.readLine();
            String [] parts = bf.readLine().split("\\.");
            String ip = "";
            ip = parts[0]+parts[1]+parts[2];
            if(map.containsKey(routerIp)){
                Router router = map.get(routerIp);
                if(router.isInRange(ip)){
                    System.out.println("postoi");
                }else{
                    System.out.println("ne postoi");
                }
            }else{
                System.out.println("ne postoi");
            }
        }
    }
}

class Router{
    String routerIp;
    ArrayList<String> accessRange;

    public Router(String routerIp, ArrayList<String> accessRange) {
        this.routerIp = routerIp;
        this.accessRange = accessRange;
    }

    public String getRouterIp() {
        return routerIp;
    }

    public ArrayList<String> getAccessRange() {
        return accessRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Router router = (Router) o;
        return Objects.equals(routerIp, router.routerIp) && Objects.equals(accessRange, router.accessRange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routerIp, accessRange);
    }

    public boolean isInRange(String ip) {
        for(int i=0;i<accessRange.size();i++){
            String [] parts = accessRange.get(i).split("\\.");
            String ipAddress = "";
            ipAddress = parts[0]+parts[1]+parts[2];
            if(ipAddress.equals(ip)){
                return true;
            }
        }
        return false;
    }
}
