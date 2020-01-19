package sweet.shop;

import sweet.shop.cake.Cake;
import sweet.shop.cake.CakeType;
import sweet.shop.client.Client;

import java.util.*;

public class SweetShop {

    private String name;
    private  String address;
    private String mobileNumber;
    ArrayList<Supplier> suppliers;
    HashMap<String, HashMap<CakeType, TreeSet<Cake>>> cakes;
    HashMap<String, ArrayList<Cake>> soldCakes;
    ArrayList<Client> clients;
    private double money;

    public SweetShop(String name, String address, String mobileNumber, ArrayList<Supplier> suppliers) {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.suppliers = suppliers;
        this.cakes = new HashMap<>();
        this.soldCakes = new HashMap<>();
        this.money = 0.0;
        this.clients = new ArrayList<>();
    }

    public void addCake(Cake cake) {
        if (!cakes.containsKey(cake.getKind())) {
            cakes.put(cake.getKind(), new HashMap<>());
        }

        if (!cakes.get(cake.getKind()).containsKey(cake.getType())) {
            cakes.get(cake.getKind()).put(cake.getType(), new TreeSet<>());
        }

        cakes.get(cake.getKind()).get(cake.getType()).add(cake);
    }

    public boolean isCakeAvailable(Cake cake) {
        if (!cakes.containsKey(cake.getKind())) {
            return false;
        }

        if (!cakes.get(cake.getKind()).containsKey(cake.getType())) {
            return false;
        }

        if (cakes.get(cake.getKind()).get(cake.getType()).size() == 0) {
            return false;
        }

        return true;
    }

    public Supplier getRandomSupplier() {
        Random r = new Random();
        return this.suppliers.get(r.nextInt(suppliers.size()));
    }

    public void receiveMoney(double amount) {
        if (amount > 0) {
            System.out.println(this.name + " receives money for order: " + amount);
            this.money += amount;
        }
    }

    public void printAvailableCakes() {
        for (String cakeKind : this.cakes.keySet()) {
            System.out.println("====" + cakeKind + "===");
            for (CakeType type : this.cakes.get(cakeKind).keySet()) {
                System.out.println("====" + type + "===");
                for (Cake cake : this.cakes.get(cakeKind).get(type)) {
                    System.out.println(cake);
                }
            }
        }
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }

    public void sellCake(Cake cake) {
        if (!isCakeAvailable(cake)) {
            return;
        }

        System.out.println("Selling cake : " + cake);
        this.cakes.get(cake.getKind()).get(cake.getType()).remove(cake);

        if (!soldCakes.containsKey(cake.getKind())) {
            soldCakes.put(cake.getKind(), new ArrayList<>());
        }

        soldCakes.get(cake.getKind()).add(cake);
    }

    public void printMostSoldKindCake() {
        System.out.println("\nMost sold kind of  cake:");
        ArrayList<Map.Entry<String, ArrayList<Cake>>> list = new ArrayList<>(soldCakes.entrySet());
        list.sort((e1, e2) -> e2.getValue().size() - e1.getValue().size());

        System.out.println(list.get(0).getKey());
    }

    public double getMoney() {
        return this.money;
    }

    public void printClientWhoSpendMostMoney() {
        System.out.println("\nClient who spend most money for cakes:");
        clients.sort((c1, c2) -> Double.compare(c2.getSpendMoneyForCakes(), c1.getSpendMoneyForCakes()));

        System.out.println(clients.get(0));
    }

    public void printSupplierWithMostOrders() {
        System.out.println("\nSupplier with most orders:");
        ArrayList<Supplier> suppliersByNumberOrders = new ArrayList<>(suppliers);
        suppliersByNumberOrders.sort((s1, s2) -> s2.getNumberOrders() - s1.getNumberOrders());

        System.out.println(suppliersByNumberOrders.get(0));

    }

    public void printSuppliersInfoSortedByTips() {
        System.out.println("\n=======Suppliers By Tips========");
        TreeSet<Supplier> suppliersByTips = new TreeSet<>((s1, s2) -> {
            if (s1.getMoney() - s2.getMoney() == 0) {
                return 1;
            }
            return Double.compare(s2.getMoney(), s1.getMoney());
        });

        suppliersByTips.addAll(suppliers);

        for (Supplier supplier : suppliersByTips) {
            System.out.println(supplier);
        }
    }
}
