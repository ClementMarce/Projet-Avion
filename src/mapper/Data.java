package mapper;
import java.util.ArrayList;
import java.util.List;


class Data {
    private List<Avion> avions;

    public Data() {
        this.avions = new ArrayList<>();
    }

    public void addAvion(Avion avion) {
        avions.add(avion);
    }

    public void removeAvion(Avion avion) {
        avions.remove(avion);
    }

    public List<Avion> getAvions() {
        return avions;
    }
}
