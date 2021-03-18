import java.util.ArrayList;

public class Defausse extends Zone {
	@Override
	public boolean addTuile(Tuile t) {
		this.tuiles.add(t);
		return true;
	}

	@Override
	public boolean addTuiles(ArrayList<Tuile> t) {
		int i = 0;
		while (i < t.size()) {
			tuiles.add(t.get(i));
			i++;
		}
		return false;
	}

}
