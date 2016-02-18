
public class BikeStoreTest {

	public static void main(String[] args) {
		for(int i = 0; i < 20; i++) {
		BikeStore.addBike("RED", 20, 10000);
		}
		System.out.println(BikeStore.getAllBikes());
	}
}
