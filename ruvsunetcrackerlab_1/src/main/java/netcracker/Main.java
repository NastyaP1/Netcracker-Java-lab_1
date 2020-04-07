package netcracker;

/**
 * класс Main - точка запуска проекта
 */
public class Main {
    /**
     * процедура запуска проекта
     * @param args
     */
    public static void main(String[] args) throws Exception {
        MainView menu = new MainView();
        menu.showMatrix();
    }
}
