import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaptopStore {
    private List<Laptop> laptops;

    public LaptopStore() {
        laptops = new ArrayList<>();
        // Пример добавления некоторых ноутбуков в магазин
        laptops.add(new Laptop("Apple", "MacBook Pro", 16, 512, "macOS"));
        laptops.add(new Laptop("Dell", "XPS 15", 32, 1000, "Windows"));
        laptops.add(new Laptop("HP", "Spectre x360", 16, 512, "Windows"));
        laptops.add(new Laptop("Lenovo", "ThinkPad X1 Carbon", 16, 256, "Windows"));
        laptops.add(new Laptop("Microsoft", "Surface Laptop 4", 8, 256, "Windows"));
    }

    public void filterLaptops(final Map<String, String> filters) {
        List<Laptop> filteredLaptops = new ArrayList<>();

        for (Laptop laptop : laptops) {
            boolean meetsCriteria = true;

            for (Map.Entry<String, String> entry : filters.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                switch (key) {
                    case "ram":
                        if (laptop.getRam() < Integer.parseInt(value)) {
                            meetsCriteria = false;
                        }
                        break;
                    case "storage":
                        if (laptop.getStorage() < Integer.parseInt(value)) {
                            meetsCriteria = false;
                        }
                        break;
                    case "os":
                        if (!laptop.getOs().equalsIgnoreCase(value)) {
                            meetsCriteria = false;
                        }
                        break;
                    default:
                        // Если задан неизвестный критерий фильтрации, игнорируем его
                        break;
                }
            }

            if (meetsCriteria) {
                filteredLaptops.add(laptop);
            }
        }

        System.out.println("Filtered Laptops:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();

        // Создаем фильтры
        final Map<String, String> filters = new HashMap<>();
        
        // Выбор критериев по цифрам
        System.out.println("Выберите критерий для фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Память");
        System.out.println("3 - Операционная система");
        // Добавьте другие критерии по мере необходимости

        // Считываем выбор пользователя
        int choice = 0; // Изначально задаем значение, чтобы войти в цикл
        while (choice < 1 || choice > 3) {
            System.out.print("Введите номер критерия: ");
            choice = Integer.parseInt(System.console().readLine().trim());
        }

        // Соответствие цифровых критериев текстовым
        String criteriaKey;
        switch (choice) {
            case 1:
                criteriaKey = "ram"; // ОЗУ
                break;
            case 2:
                criteriaKey = "storage"; // Память
                break;
            case 3:
                criteriaKey = "os"; // Операционная система
                break;
            default:
                criteriaKey = ""; // Добавьте другие критерии по мере необходимости
        }

        // Введите минимальные значения для выбранного критерия
        System.out.print("Введите минимальное значение для выбранного критерия: ");
        String value = System.console().readLine().trim();

        // Добавляем критерий фильтрации в карту
        filters.put(criteriaKey, value);

        // Применяем фильтры и выводим результат
        store.filterLaptops(filters);
    }
}
