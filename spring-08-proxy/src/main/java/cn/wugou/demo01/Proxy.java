package cn.wugou.demo01;

public class Proxy implements Rent{
    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    public void rent() {
        seeHouse();
        host.rent();
        hetong();
    }

    //看房
    public void seeHouse(){
        System.out.println("中介带你去看房");
    }

    //签合同
    public void hetong(){
        System.out.println("签租赁合同");
    }

}
