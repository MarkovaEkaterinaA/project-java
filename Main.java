import java.util.*;

public class Main {
    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> filterOptions = new HashMap<>();
        filterOptions.put("1", Arrays.asList("8", "16", "32", "64")); // ОЗУ
        filterOptions.put("2", Arrays.asList("256", "512", "1000", "2000")); // Объем ЖД
        filterOptions.put("3", Arrays.asList("Windows", "macOS", "Linux")); // Операционная система
        filterOptions.put("4", Arrays.asList("черный", "белый", "серый", "синий")); // Цвет

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        String choice = scanner.nextLine();

        if (!filterOptions.containsKey(choice)) {
            System.out.println("Неверный выбор. Завершение программы.");
            scanner.close();
            return;
        }

        List<String> options = filterOptions.get(choice);
        System.out.println("Выберите одно из доступных значений:");

        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        int selectionIndex = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (selectionIndex < 1 || selectionIndex > options.size()) {
            System.out.println("Неверный выбор. Завершение программы.");
            scanner.close();
            return;
        }

        String selectedValue = options.get(selectionIndex - 1);
        Map<String, String> filters = new HashMap<>();

        switch (choice) {
            case "1":
                filters.put("ram", selectedValue);
                break;
            case "2":
                filters.put("storage", selectedValue);
                break;
            case "3":
                filters.put("os", selectedValue);
                break;
            case "4":
                filters.put("color", selectedValue);
                break;
            default:
                System.out.println("Неверный выбор. Завершение программы.");
                scanner.close();
                return;
        }

        store.filterLaptops(filters);
        scanner.close();
    }
}
