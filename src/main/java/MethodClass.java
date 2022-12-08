import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MethodClass {


    //zakladamy ze zawsze wejsciem metody bedzie zbior producentow


    public static void main(String[] args) {
        List<List<String>> lists = new ArrayList<>();

        List<String> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();

        listA.add("text A1");
        listA.add("text A2");
        listB.add("text B1");
        listB.add("text B2");

        lists.add(listA);
        lists.add(listB);

        lists.stream() //utworzymy strumien list
                //elementy tego strumienia to listy
                .forEach(System.out::println);

        lists.stream()
                .flatMap(l -> l.stream())
                //po zastosowaniu powyzszej flatMap'y elementami strumienia sa Stringi
                .forEach(System.out::println);
    }

    //1
    public List<Model> getAllModels(Set<Manufacturer> manufacturers) {
        //najpierw tworzymy strumien, teraz w strumieniu mamy poszczegolnych producentow
        return manufacturers.stream()
                //flatMap "splaszcza" strumien, w tym przypadku:
                //wyrazenie lambda przyjmuje pojedynczego producenta a nastepnie zwraca strumien modeli
                //nastepnie strumienie modeli sa laczone w jeden strumien za pomoca metody flatMap()
                .flatMap(m -> m.models.stream()) //flatMap laczy strumienie w jeden strumien
                .collect(Collectors.toList()); //składamy modele do listy, ktora jest zwracana z metody
    }

    //2
    public List<Car> getAllCars(Set<Manufacturer> manufacturers) {
        return manufacturers.stream()
                .flatMap(m -> m.models.stream()) //mamy teraz w strumieniu modele
                .flatMap(m -> m.cars.stream()) //teraz dostalismy az do samochodow
                .collect(Collectors.toList());
    }

    //3
    public List<String> getAllManufacturerNames(Set<Manufacturer> manufacturers){
        return manufacturers.stream()
                .map(m -> m.name)
                .collect(Collectors.toList());
    }
}

